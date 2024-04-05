package se.erikalexandersson.sixnimmt;

import java.util.List;

public class Card {

	public static Integer getScore(List<Integer> scoredCards) {
		int score = 0;
		for (Integer card : scoredCards) {
			score++;
			if (card % 5 == 0) {
				score += 1;
			}
			if (card % 10 == 0) {
				score += 1;
			}
			if (card % 11 == 0) {
				score += 4;
			}
			if (card % 55 == 0) {
				score += 1;
			}
		}
		return score;
	}

}
