package se.erikalexandersson.sixnimmt;

public class HighPlayer extends Player {

	public HighPlayer(String name) {
		super(name);
	}

	@Override
	public int chooseCard(Board board) {
		return currentCards.stream().max(Integer::compare).get();
	}

	@Override
	public int chooseRow(Board board) {
		return board.getLowestScoringRow();
	}

}
