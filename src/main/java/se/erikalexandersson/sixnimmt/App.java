package se.erikalexandersson.sixnimmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class App {

	public static void main(String[] args) {
		Map<Player, Integer> playerWins = new HashMap<Player, Integer>();
		Map<Player, List<Integer>> playerScores = new HashMap<Player, List<Integer>>();

		// Create players
		Player player1 = new SimplePlayer(1);
		Player player2 = new SimplePlayer(2);
		Player player3 = new SimplePlayer(3);
		Player player4 = new SimplePlayer(4);
		Player player5 = new SimplePlayer(5);
		Player player6 = new SimplePlayer(6);
		player6.setMaxHandSize(15);

		playerWins.put(player1, 0);
		playerWins.put(player2, 0);
		playerWins.put(player3, 0);
		playerWins.put(player4, 0);
		playerWins.put(player5, 0);
		playerWins.put(player6, 0);

		int draws = 0;
		int totalGames = 100000;

		// Play games
		for (int i = 0; i < totalGames; i++) {
			Game game = new Game();
			game.addPlayer(player1);
			game.addPlayer(player2);
			game.addPlayer(player3);
			game.addPlayer(player4);
			game.addPlayer(player5);
			game.addPlayer(player6);

			game.play();

			for (Entry<Player, Integer> entry : game.getPlayerScore().entrySet()) {
				Player p = entry.getKey();
				int score = entry.getValue();

				if (playerScores.get(p) == null) {
					playerScores.put(p, new ArrayList<Integer>());
				}
				playerScores.get(p).add(score);
			}

			List<Player> winners = game.getWinners();
			if (winners.size() > 1) {
				draws++;
			} else {
				playerWins.put(winners.get(0), playerWins.get(winners.get(0)) + 1);
			}

			if (i > 0 && i % 5000 == 0) {
				System.out.println("Played " + i + " games so far ...");
			}
		}

		System.out.println("-------------------------------");
		System.out.println(String.format("%20s%12s%11s%9s%10s", "Name", "Num wins", "% of total", "mean", "std.dev."));
		playerWins.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(e -> {
			double[] scores = playerScores.get(e.getKey()).stream().mapToDouble(Number::doubleValue).toArray();
			double meanScore = mean(scores);
			double stdDevScore = calculateStandardDeviation(scores);
			System.out.println(String.format("%20s%12d%10.2f%%%9.1f%10.2f", e.getKey().getName(), e.getValue(),
					100.0 * ((float) e.getValue() / (float) totalGames), (float) meanScore, (float) stdDevScore));
		});
		System.out.println(
				String.format("%20s%12d%10.2f%%", "DRAW", draws, 100.0 * ((float) draws / (float) totalGames)));
	}

	public static double mean(double[] array) {
		// get the sum of array
		double sum = 0.0;
		for (double i : array) {
			sum += i;
		}

		// get the mean of array
		int length = array.length;
		double mean = sum / length;

		return mean;
	}

	public static double calculateStandardDeviation(double[] array) {
		// get the sum of array
		double sum = 0.0;
		for (double i : array) {
			sum += i;
		}

		// get the mean of array
		int length = array.length;
		double mean = sum / length;

		// calculate the standard deviation
		double standardDeviation = 0.0;
		for (double num : array) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}
}
