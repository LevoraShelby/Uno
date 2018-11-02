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
 * 
 * @Contract The passed InputStream isn't to be read by clients. They should
 * only write to its stream.
 * 
 * @author trevor
 */
public class Player {
	protected final List<Card> hand;
	protected final InputStream wildSuitStream;

	public Player(List<Card> cards, InputStream wildSuitStream) {
		hand = new ArrayList<Card>(cards);
		this.wildSuitStream = wildSuitStream;
	}
	public Player(InputStream wildSuitStream) {
		this(new ArrayList<Card>(), wildSuitStream);
	}
	public Player(Card[] cards, InputStream wildSuitStream) {
		this(Arrays.asList(cards), wildSuitStream);
	}
	public Player(Player player) {
		hand = player.hand;
		wildSuitStream = player.wildSuitStream;
	}

	public Card[] cards() {
		return hand.toArray(new Card[hand.size()]);
	}

	/**
	 * Player removes the Card in its hand at the index of cardNum, and then
	 * plays a Card to discard. If the Card is wild the Player plays a
	 * replicate Card with the suit matching that of the wildSuitStream's next
	 * byte.
	 * 
	 * @param cardNum
	 * @param discard
	 */
	public void playCard(int cardNum, Discard discard) {
		Card card = hand.get(cardNum);
		//Player removes the card from its hand and plays it to discard.
		if(!card.isWild()) {
			hand.remove(cardNum);
			discard.discard(card);
		}
		/**
		 * Player removes the original Card from their hand and creates a new
		 * Card with a color of wildSuitStream's choice, the rank of the
		 * original Card's rank, and wildness. It then plays the new Card on
		 * discard.
		 */
		else if (card.isWild()) {
			try {
				int suit = wildSuitStream.read();

				if(suit == -1) {
					throw new RuntimeException(
							"Player was not given a suit to play."
					);
				}

				hand.remove(cardNum);
				card = new Card(wildSuitStream.read(), card.rank(), true);
				discard.discard(card);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Draws an Card from source and adds this Card to its hand.
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
