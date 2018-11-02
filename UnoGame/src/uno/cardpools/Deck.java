package uno.cardpools;

import uno.Card;

/**
 * Represents a deck of Cards.
 * @author trevor
 */
public interface Deck {
	/**
	 * @Contract If an already existing Card is returned, it must be removed
	 * from whatever holds it in order to be passed.
	 * @exception DrawException if no Card can be returned.
	 * @return the drawn Card.
	 */
	public Card draw();

	/**
	 * @return true if the Deck won't be able to execute the draw method, and
	 * false if it will.
	 */
	public boolean isEmpty();
}
