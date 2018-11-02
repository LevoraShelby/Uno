package uno.turns;

/**
 * Keeps track of turn order for a game of Uno.
 * @author trevor
 */
public class PlayerTurns extends Turns
implements Skippable, Reversible {
	protected final int numPlayers;
	protected int turnIndex;
	protected boolean reversed;


	public PlayerTurns(int numPlayers) {
		this.numPlayers = numPlayers;
		turnIndex = 0;
		reversed = false;
	}


	/**
	 * Sets turnIndex to the index of the next turnTaker.
	 */
	public void endTurn() {
		moveTurns(1);
	}


	/**
	 * Ends the turn then skips a number of times equal to numSkips to the next
	 * turn. Ensures no side-effects from consecutive end turns.
	 * @param numSkips
	 */
	public final void skip(int numSkips) {
		endTurn();
		moveTurns(numSkips);
	}
	public void skip() {
		skip(1);
	}


	protected final void moveTurns(int numTurns) {
		turnIndex = relativeTurnIndex(numTurns);
	}


	/**
	 * Reverses the direction the turnIndex goes.
	 */
	public void reverse() {
		reversed = !reversed;
	}


	/**
	 * Returns the index of the current turn.
	 * @return current turn index.
	 */
	public int currentTurnIndex() {
		return turnIndex;
	}


	/**
	 * Returns the index of the turn that is numTurns away from the current
	 * turn index.
	 * @return relative turn index.
	 */
	public int relativeTurnIndex(int numTurns) {
		int unwrappedIndex;
		if(!reversed) {
			unwrappedIndex = turnIndex + numTurns;
		}
		else {
			unwrappedIndex = turnIndex - numTurns;
		}
		return ( ( unwrappedIndex % numPlayers ) + numPlayers ) % numPlayers;
	}


	/**
	 * @return number of Players.
	 */
	public int numPlayers() {
		return numPlayers;
	}
}
