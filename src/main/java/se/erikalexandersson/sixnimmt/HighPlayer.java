package se.erikalexandersson.sixnimmt;

public class HighPlayer extends Player {

	public HighPlayer(int id) {
		super(id);
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
