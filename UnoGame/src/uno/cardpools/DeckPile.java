package uno.cardpools;

import java.util.List;

import uno.Card;

/**
 * A finite deck of Cards.
 * @author trevor
 */
public class DeckPile extends Pile implements Deck {
	public DeckPile(Card[] cards) {
		super(cards);
	}
	public DeckPile(List<Card> cards) {
		super(cards);
	}

	/**
	 * Removes the top Card from the deck and returns it.
	 * @return topCard
	 */
	public Card draw() {
		Card topCard = cards.remove(cards.size() - 1);
		return topCard;
	}

	/**
	 * This method returns true if the deck is out of cards.
	 * @return isEmpty
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
}
