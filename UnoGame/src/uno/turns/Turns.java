package uno.turns;

/**
 * Turns should be thought of an iterator that can predict an iteration without
 * fail (with exceptions to a state change) and doesn't end.
 * @author trevor
 */
public abstract class Turns {
	/**
	 * Ends the current turn.
	 */
	public abstract void endTurn();

	/**
	 * @return current turn index.
	 */
	public abstract int currentTurnIndex();

	/**
	 * Returns the turns index of the turn-taker who is turnsAway turns away.
	 * @param turnsAway
	 * @return relative turn index.
	 */
	public abstract int relativeTurnIndex(int turnsAway);
}
