package se.erikalexandersson.sixnimmt;

import java.util.HashMap;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		Map<Player, Integer> playerWins = new HashMap<Player, Integer>();

		// Create players
		Player player1 = new RandomPlayer("Player 1");
		Player player2 = new RandomPlayer("Player 2");
		Player player3 = new RandomPlayer("Player 3");
		Player player4 = new RandomPlayer("Player 4");
		Player player5 = new RandomPlayer("Player 5");
		Player player6 = new RandomPlayer("Player 6");

		playerWins.put(player1, 0);
		playerWins.put(player2, 0);
		playerWins.put(player3, 0);
		playerWins.put(player4, 0);
		playerWins.put(player5, 0);
		playerWins.put(player6, 0);

		// Play games
		for (int i = 0; i < 1000000; i++) {
			Game game = new Game();
			game.addPlayer(player1);
			game.addPlayer(player2);
			game.addPlayer(player3);
			game.addPlayer(player4);
			game.addPlayer(player5);
			game.addPlayer(player6);

			game.play();

			for (Player player : game.getWinners()) {
				playerWins.put(player, playerWins.get(player) + 1);
			}

			if (i > 0 && i % 5000 == 0) {
				System.out.println("Played " + i + " games so far ...");
			}
		}

		System.out.println("-------------------------------");
		System.out.println(String.format("%12s%12s%12s", "Name", "Num wins", "% of wins"));
		int totalWins = playerWins.values().stream().mapToInt(Integer::valueOf).sum();
		playerWins.entrySet().stream().sorted((e1, e2) -> e1.getKey().getName().compareTo(e2.getKey().getName()))
				.forEach(e -> {
					System.out.println(String.format("%12s%12d%12.2f%%", e.getKey().getName(), e.getValue(),
							100.0 * ((float) e.getValue() / (float) totalWins)));
				});
	}
}
