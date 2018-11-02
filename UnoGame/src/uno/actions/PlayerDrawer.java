package uno.actions;

/**
 * This interface draws Cards.
 * @author trevor
 */
public interface PlayerDrawer {
	/**
	 * This method has the Player at playerIndex draw Cards.
	 */
	public void drawCardAt(int playerIndex, int numCards);

	public void drawForNext(int numCards);
}
