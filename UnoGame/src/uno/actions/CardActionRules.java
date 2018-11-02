package uno.actions;

import uno.Card;

/**
 * @author trevor
 * Note: PlayingRules doesn't necessarily restrict its implementation to
 * games of Uno, but instead restricts its implementation to games that play
 * with Uno cards.
 */
public interface CardActionRules {
	/**
	 * @Contract Executes all the actions that playing card would in the game
	 * PlayingRules is implemented into.
	 * @param card
	 */
	public void doCardAction(Card card);
}
