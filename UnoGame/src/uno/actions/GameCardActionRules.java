package uno.actions;

import uno.Card;

/**
 * Rules of Card action for an Uno game.
 * @author trevor
 */
public class GameCardActionRules implements CardActionRules {
	protected PlayerDrawer playerDrawer;
	protected TurnEnder turnEnder;
	protected TurnReverser turnReverser;
	protected TurnSkipper turnSkipper;

	public GameCardActionRules(ActionsFactory actions) {
		this.playerDrawer = actions.playerDrawer();
		this.turnEnder = actions.turnEnder();
		this.turnReverser = actions.turnReverser();
		this.turnSkipper = actions.turnSkipper();
	}

	/**
	 * Executes action related to card.
	 * @param card
	 */
	public void doCardAction(Card card) {
		if(
			card.rank() >= 0 && card.rank() <= 9 ||
			card.rank() == 13 /**Non-Special Card*/
		) {
			turnEnder.endTurn();
		} else if(card.rank() == 10 /**Skip Card*/) {
			turnSkipper.skip();
			turnEnder.endTurn();
		} else if(card.rank() == 11 /**Reverse Card*/) {
			turnReverser.reverseTurns();
			turnEnder.endTurn();
		} else if(card.rank() == 12 /**Draw Two*/) {
			playerDrawer.drawForNext(2);
			turnSkipper.skip();
			turnEnder.endTurn();
		} else if(card.rank() == 14 /**Draw Four*/) {
			playerDrawer.drawForNext(4);
			turnSkipper.skip();
			turnEnder.endTurn();
		}
	}
}
