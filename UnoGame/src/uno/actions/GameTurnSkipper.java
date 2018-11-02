package uno.actions;

import uno.turns.Skippable;

/**
 * Skips turns with a Skippable.
 * @author trevor
 */
public class GameTurnSkipper implements TurnSkipper {
	protected Skippable turns;

	public GameTurnSkipper(Skippable turns) {
		this.turns = turns;
	}

	public void skip() {
		turns.skip();
	}
}
