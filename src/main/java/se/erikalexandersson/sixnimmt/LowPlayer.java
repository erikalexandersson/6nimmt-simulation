package se.erikalexandersson.sixnimmt;

public class LowPlayer extends Player {

	public LowPlayer(String name) {
		super(name);
	}

	@Override
	public int chooseCard(Board board) {
		return currentCards.stream().min(Integer::compare).get();
	}

	@Override
	public int chooseRow(Board board) {
		return board.getLowestScoringRow();
	}

}
