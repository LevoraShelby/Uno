package uno.cardpools;

import uno.Card;

public class CollectableDiscard extends DiscardPile implements CollectableCards {
	public CollectableDiscard(Card topCard) {
		super(topCard);
	}

	/**
	 * @returns all but topCard.
	 */
	public Card[] collectables() {
		Card[] collectables = new Card[cards.size() - 1];
		for(int i = 0; i < cards.size() - 1; i++) {
			collectables[i] = cards.get(i);
		}
		return collectables;
	}

	public void collect() {
		int cardsSize = cards.size();
		for(int i = 0; i <= cardsSize - 2; i++) {
			cards.remove(0);
		}
	}
}

//TODO: Make more readable or at least document.