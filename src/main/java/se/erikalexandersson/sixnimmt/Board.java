package se.erikalexandersson.sixnimmt;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public int[][] cards;
	public int numRows;
	public int maxCardsInRow;

	public Board() {
		numRows = 4;
		maxCardsInRow = 5;
		cards = new int[4][];
		cards[0] = new int[] { 0, 0, 0, 0, 0 };
		cards[1] = new int[] { 0, 0, 0, 0, 0 };
		cards[2] = new int[] { 0, 0, 0, 0, 0 };
		cards[3] = new int[] { 0, 0, 0, 0, 0 };
	}

	public int getLowestCard() {
		int lowest = 105;
		for (int i = 0; i < 4; i++) {
			int card = getLastCardInRow(i);
			if (card > 0 && card < lowest) {
				lowest = card;
			}
		}
		return lowest;
	}

	public int getNumCardsInRow(int row) {
		int numCards = 0;
		for (int i = 0; i < 5; i++) {
			int card = cards[row][i];
			if (card == 0) {
				break;
			} else {
				numCards++;
			}
		}
		return numCards;
	}

	public int getRowForCard(int playedCard) {
		int closestIndex = 0;
		int currentMinDiff = 105;
		for (int i = 0; i < 4; i++) {
			int card = getLastCardInRow(i);
			int diff = playedCard - card;
			if (diff > 0 && diff < currentMinDiff) {
				currentMinDiff = diff;
				closestIndex = i;
			}
		}
		return closestIndex;
	}

	public int getLastCardInRow(int row) {
		for (int j = 4; j >= 0; j--) {
			int card = cards[row][j];
			if (card == 0) {
				continue;
			} else {
				return card;
			}
		}
		return 0;
	}

	public List<Integer> replaceRowWithCard(int row, int playedCard) {
		List<Integer> scoredCards = new ArrayList<>();
		for (int j = 0; j < 4; j++) {
			int card = cards[row][j];
			if (card > 0) {
				scoredCards.add(card);
			} else {
				break;
			}
		}
		cards[row] = new int[] { 0, 0, 0, 0, 0 };
		cards[row][0] = playedCard;
		return scoredCards;
	}

	public int getScoreForRow(int row) {
		List<Integer> scoredCards = new ArrayList<>();
		for (int j = 0; j < 4; j++) {
			int card = cards[row][j];
			if (card > 0) {
				scoredCards.add(card);
			} else {
				break;
			}
		}
		return Card.getScore(scoredCards);
	}

	public Integer getLowestScoringRow() {
		int min = Integer.MAX_VALUE;
		int minRow = 0;
		for (int i = 0; i < 4; i++) {
			int rowScore = getScoreForRow(i);
			if (rowScore < min) {
				min = rowScore;
				minRow = i;
			}
		}
		return minRow;
	}

	public void addCardToRow(int row, int playedCard) {
		for (int j = 4; j >= 0; j--) {
			if (cards[row][j - 1] > 0) {
				cards[row][j] = playedCard;
				break;
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				int card = cards[i][j];
				if (card > 0) {
					System.out.print(String.format("%3d ", card));
				}
			}
			System.out.println();
		}
	}
}
