package se.erikalexandersson.sixnimmt;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {

	protected String name;
	protected Set<Integer> memory;
	protected Set<Integer> currentCards;

	public Player(String name) {
		this.name = name;
		this.memory = new HashSet<>();
		this.currentCards = new HashSet<>();
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

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
