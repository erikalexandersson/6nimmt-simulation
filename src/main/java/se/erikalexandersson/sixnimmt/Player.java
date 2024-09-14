package se.erikalexandersson.sixnimmt;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {

	protected int id;
	protected Set<Integer> memory;
	protected Set<Integer> currentCards;
	protected int maxHandSize;

	public Player(int id) {
		this.id = id;
		this.memory = new HashSet<>();
		this.currentCards = new HashSet<>();
		this.maxHandSize = 10;
	}

	public abstract int chooseCard(Board board);

	public abstract int chooseRow(Board board);

	public void dealCards(Collection<? extends Integer> cards) {
		currentCards.addAll(cards);
	}

	public void dealCard(Integer card) {
		currentCards.add(card);
	}

	public int getCardFromPlayer(Board board) {
		int card = chooseCard(board);
		currentCards.remove(card);
		return card;
	}

	public int getMaxHandSize() {
		return maxHandSize;
	}

	public void setMaxHandSize(int maxHandSize) {
		this.maxHandSize = maxHandSize;
	}

	public String getName() {
		return this.getClass().getSimpleName() + " " + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
