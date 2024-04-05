package se.erikalexandersson.sixnimmt;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

	private Map<Player, Integer> playerScore;
	private LinkedList<Integer> deck;

	public Game() {
		this.playerScore = new HashMap<>();
	}

	public void addPlayer(Player player) {
		playerScore.put(player, 0);
	}

	public void play() {

		// Play rounds until the game ends
		int highestScore = 0;
		do {

			deck = Stream.iterate(1, n -> n + 1).limit(104).collect(Collectors.toCollection(LinkedList::new));
			Collections.shuffle(deck);

			// Deal 10 cards to players
			for (Player player : playerScore.keySet()) {
				for (int i = 0; i < 10; i++) {
					player.dealCard(deck.pop());
				}
			}

			// Setup board with 4 initial cards
			Board board = new Board();
			board.cards[0][0] = deck.pop();
			board.cards[1][0] = deck.pop();
			board.cards[2][0] = deck.pop();
			board.cards[3][0] = deck.pop();

			// Continue until all cards are played
			for (int i = 0; i < 10; i++) {

				// Ask each player for a card
				Map<Player, Integer> playedCards = new HashMap<>();
				for (Player player : playerScore.keySet()) {
					playedCards.put(player, player.chooseCard(board));
				}

				int lowest = board.getLowestCard();

				// Sort cards by order
				playedCards.entrySet().stream().sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
						.forEach(e -> {

							// Play cards
							Player player = e.getKey();
							int playedCard = e.getValue();

							// If first card is lower than all the other top
							// cards on board, ask player to take a pile
							if (playedCard < lowest) {

								// Add cards to players score
								// Clear pile and add players card
								int row = player.chooseRow(board);
								List<Integer> scoredCards = board.replaceRowWithCard(row, playedCard);
								playerScore.put(player, playerScore.get(player) + Card.getScore(scoredCards));
							} else {
								// Otherwise, add cards to piles
								int row = board.getRowForCard(playedCard);
								if (board.getNumCardsInRow(row) < 5) {
									// Add card to pile
									board.addCardToRow(row, playedCard);
								} else {
									// If pile is full, clear pile and add
									// players card
									List<Integer> scoredCards = board.replaceRowWithCard(row, playedCard);
									playerScore.put(player, playerScore.get(player) + Card.getScore(scoredCards));
								}
							}
						});
			}

			// If any player has over 66 in score, end the game and give the win
			// to player with lowest score
			highestScore = playerScore.values().stream().max(Integer::compare).get();
		} while (highestScore < 67);
	}

	public List<Player> getWinners() {
		// Find lowest score
		int lowestScore = playerScore.values().stream().min(Integer::compare).get();

		// Return all with lowest score
		return playerScore.entrySet().stream().filter(e -> e.getValue().intValue() == lowestScore).map(e -> e.getKey())
				.collect(Collectors.toList());
	}
}
