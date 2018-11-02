package uno.cardpools;

import uno.Card;

/**
 * CollectableCards represents a finite source of Cards that can be collected from.
 * @author trevor
 */
public interface CollectableCards {
	/**
	 * Returns the collectable cards.
	 * @return collectable cards.
	 */
	public Card[] collectables();

	/**
	 * Removes the collectable cards from CollectableCards.
	 */
	public void collect();
}
