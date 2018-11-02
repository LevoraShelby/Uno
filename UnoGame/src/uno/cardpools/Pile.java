package uno.cardpools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uno.Card;

/**
 * Represents of a finite, mutable set of cards.
 * @author trevor
 */
public abstract class Pile {
	protected final List<Card> cards;

	public Pile(List<Card> cards) {
		this.cards = new ArrayList<Card>(cards);
	}
	public Pile(Card[] cards) {
		this(Arrays.asList(cards));
	}

	/**
	 * @return all Cards.
	 */
	public final Card[] pile() {
		return cards.toArray(new Card[cards.size()]);
	}
}