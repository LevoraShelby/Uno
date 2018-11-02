package uno.actions;

/**
 * Generates actions for a game of Uno.
 * @author trevor
 */
public interface ActionsFactory {
	public PlayerDrawer playerDrawer();

	public TurnEnder turnEnder();

	public TurnReverser turnReverser();

	public TurnSkipper turnSkipper();
}
