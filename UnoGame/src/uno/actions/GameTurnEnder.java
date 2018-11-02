package uno.actions;

import uno.turns.Turns;

/**
 * Ends turns with Turns.
 * @author trevor
 */
public class GameTurnEnder implements TurnEnder {
	protected Turns turns;

	public GameTurnEnder(Turns turns) {
		this.turns = turns;
	}

	public void endTurn() {
		turns.endTurn();
	}
}
