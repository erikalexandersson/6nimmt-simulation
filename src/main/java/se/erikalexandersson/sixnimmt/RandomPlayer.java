package se.erikalexandersson.sixnimmt;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer extends Player {

	public RandomPlayer(String name) {
		super(name);
	}

	@Override
	public int chooseCard(Board board) {
		return Util.getRandomFromSet(currentCards);
	}

	@Override
	public int chooseRow(Board board) {
		return ThreadLocalRandom.current().nextInt(board.cards.length);
	}
	
}
