package uno.actions;

import uno.turns.Reversible;

/**
 * Reverses turns with a Reversible.
 * @author trevor
 */
public class GameTurnReverser implements TurnReverser {
	protected Reversible turns;

	public GameTurnReverser(Reversible turns) {
		this.turns = turns;
	}

	public void reverseTurns() {
		turns.reverse();
	}
}
