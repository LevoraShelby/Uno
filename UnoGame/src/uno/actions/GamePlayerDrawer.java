package uno.actions;

import uno.cardpools.CardSystem;
import uno.turns.Turns;

/**
 * Draws cards relative to current Player.
 * @author trevor
 */
public class GamePlayerDrawer implements PlayerDrawer {
	protected Turns turns;
	protected CardSystem cardSystem;

	public GamePlayerDrawer(Turns turns, CardSystem cardSystem) {
		this.turns = turns;
		this.cardSystem = cardSystem;
	}

	/**
	 * Draws Card for Player who is turnsAway turns away.
	 */
	public void drawCardAt(int turnsAway, int numCards) {
		int playerNum = turns.relativeTurnIndex(turnsAway);
		cardSystem.drawCards(playerNum, numCards);
	}

	/**
	 * Draws Card for Player who would normally have the next turn.
	 */
	public void drawForNext(int numCards) {
		drawCardAt(1, numCards);
	}
}
