package uno.cardpools;

import uno.Card;

/**
 * A place to get rid of Cards in the of a game.
 * @author trevor
 */
public interface Discard {
	/**
	 * Called when getting rid of a Card in the play of a game.
	 * @param card
	 */
	void discard(Card card);
}