package uno.cardpools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uno.Card;

/**
 * A Player represents the player of a game involving Cards where Cards are
 * played to a Discard.
 * @author trevor
 */
public class Player {
	protected final List<Card> hand;
	protected InputStream colorChooser;
	protected boolean lastCardPlayed;

	public Player(List<Card> cards, InputStream colorChooser) {
		hand = new ArrayList<Card>(cards);
		this.colorChooser = colorChooser;
		lastCardPlayed = false;
	}
	public Player(InputStream colorChooser) {
		this(new ArrayList<Card>(), colorChooser);
	}
	public Player(Card[] cards, InputStream colorChooser) {
		this(Arrays.asList(cards), colorChooser);
	}
	public Player(Player player) {
		hand = player.hand;
		colorChooser = player.colorChooser;
		lastCardPlayed = false;
	}

	public Card[] cards() {
		return hand.toArray(new Card[hand.size()]);
	}

	/**
	 * Player removes the <code>Card</code> in its hand at the index of
	 * cardNum, and then plays an <code>Card</code> to rules. If the
	 * <code>Card</code> isn't wild, it just plays it to rules. If it is
	 * wild, if plays a replicate <code>Card</code> with wildCC's choice of
	 * suit to rules.
	 * @param cardNum
	 * @param discard
	 */
	public void playCard(int cardNum, Discard discard) {
		Card card = hand.get(cardNum);
		//Player removes the card from its hand and plays it to discard.
		if(!card.isWild()) {
			hand.remove(cardNum);
			discard.discard(card);
			lastCardPlayed = true;
		}
		/**
		 * Player removes the original Card from their hand and creates a new
		 * Card with a color of colorChooser's choice, a rank of the original
		 * Card's rank, and a wildness. It then plays the new Card on discard.
		 * If the colorChooser returns an unusable result, nothing is done and
		 * lastCardPlayed is set to false instead of true.
		 */
		else if (card.isWild()) {
			try {
				int suit = colorChooser.read();
				if(suit >= 1 && suit <= 4) { //can return -1 if color isn't chosen.
					hand.remove(cardNum);
					card = new Card(suit, card.rank(), true);
					discard.discard(card);
					lastCardPlayed = true;
				} else {
					lastCardPlayed = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return true if the last call of the playCard() method actually played
	 * a Card.
	 */
	public boolean wasLastCardPlayed() {
		return lastCardPlayed;
	}

	/**
	 * Draws an <code>Card</code> from source and adds this <code>Card</code>
	 * to its hand.
	 * 
	 * Note: This method should only be called if source has already been checked
	 * for emptiness. It is not the Player's responsibility to check for
	 * this and it works under the assumption that its method has been called
	 * with source not being empty.
	 * @param source
	 */
	public void drawFrom(Deck source) {
		hand.add(source.draw());
	}
}
