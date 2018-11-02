package uno.cardpools;

import java.util.Collections;
import java.util.List;

import uno.Card;

/**
 * RestockingDeck represents a deck of cards that restocks itself when empty.
 * @author trevor
 */
public class RestockingDeck extends DeckPile {
	protected final CardCollector restocker;

	public RestockingDeck(Card[] cards, CardCollector restocker) {
		super(cards);
		Collections.shuffle(this.cards);
		this.restocker = restocker;
	}
	public RestockingDeck(List<Card> cards, CardCollector restocker) {
		super(cards);
		Collections.shuffle(cards);
		this.restocker = restocker;
	}

	/**
	 * This method draws the top card of the Deck. If the deck is empty, it
	 * restocks itself.
	 * @return top card
	 */
	@Override
	public Card draw() {
		Card topCard = cards.remove(cards.size() - 1);

		if( isEmpty() ) { restock(); }

		return topCard;
	}

	/**
	 * This method restocks cards and is called when the deck is left empty
	 * after a draw.
	 */
	protected void restock() {
		for(Card card : restocker.collectCards()) {
			cards.add(card);
		}
		Collections.shuffle(cards);
	}
}
