package se.erikalexandersson.sixnimmt;

import java.util.Optional;

public class SimplePlayer extends Player {

	public SimplePlayer(String name) {
		super(name);
	}

	/**
	 * SimplePlayer chooses the card that is closest to another in a row, but
	 * not one in a row with 5 cards.
	 */
	@Override
	public int chooseCard(Board board) {
		int lowestDiff = Integer.MAX_VALUE;
		int chosenCard = -1;
		for (int i = 0; i < board.numRows; i++) {
			if (board.getNumCardsInRow(i) == board.maxCardsInRow) {
				continue;
			}
			int card = board.getLastCardInRow(i);
			Optional<Integer> closestCard = currentCards.stream().filter(c -> c > card).sorted().findFirst();
			if (closestCard.isPresent()) {
				int diff = closestCard.get() - card;
				if (diff < lowestDiff) {
					lowestDiff = diff;
					chosenCard = closestCard.get();
				}
			}
		}
		// We have no card to play, pick a random
		if (lowestDiff == Integer.MAX_VALUE) {
			return Util.getRandomFromSet(currentCards);
		}
		return chosenCard;
	}

	/**
	 * SimplePlayer picks the row with lowest score.
	 */
	@Override
	public int chooseRow(Board board) {
		return board.getLowestScoringRow();
	}

}
