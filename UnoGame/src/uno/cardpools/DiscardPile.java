package uno.cardpools;

import java.util.ArrayList;

import uno.Card;

/**
 * Represents a finite discard pile of Cards.
 * @author trevor
 */
public class DiscardPile extends Pile implements Discard {
	/**
	 * Creates discard pile with topCard on top.
	 * @param topCard
	 */
	public DiscardPile(Card topCard) {
		super(new ArrayList<Card>());
		discard(topCard);
	}

	/**
	 * Places card on top of the discard pile.
	 * @param card
	 */
	public void discard(Card card) {
		cards.add(card);
	}

	/**
	 * @return top card of the discard pile.
	 */
	public Card topCard() {
		return cards.get(cards.size() - 1);
	}
}
