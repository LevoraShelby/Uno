package uno.cardpools;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import uno.Card;
import uno.ScannerSuitChooser;

/**
 * Represents a system of Cards in a standard Uno game.
 * @author trevor
 */
public class CardSystem {
	protected final Player[] players;
	protected final DeckPile deck;
	protected final DiscardPile discard;

	public CardSystem(Player[] players, Card[] deckCards, Card topCard) {
		this.players = players.clone();

		CollectableDiscard tempDiscard = new CollectableDiscard(topCard);
		CardCollector restocker = new SingleSourceCollector(tempDiscard);
		deck = new RestockingDeck(deckCards, restocker);

		discard = tempDiscard;
	}

	/**
	 * Plays a Card from a Player in players to deck.
	 * @param playerNum
	 * @param cardNum
	 */
	public void playCard(int playerNum, int cardNum) {
		players[playerNum].playCard(cardNum, discard);
	}

	/**
	 * Draws a number of Card from deck to a Player in players.
	 */
	public void drawCards(int playerNum, int numCards) {
		for(int i = 0; i < numCards; i++) {
			players[playerNum].drawFrom(deck);
		}
	}
	public void drawCard(int playerNum) {
		drawCards(playerNum, 1);
	}

	/**
	 * Checks if the Deck is empty.
	 * @return true if deck is empty.
	 */
	public boolean isDeckEmpty() {
		return deck.isEmpty();
	}

	/**
	 * Returns number of players.
	 * @return number of players.
	 */
	public int numPlayers() {
		return players.length;
	}

	/**
	 * Returns the cards in a Player's hand.
	 * @param playerNum
	 * @return the cards in a Player's hand.
	 */
	public Card[] playerCards(int playerNum) {
		return players[playerNum].cards();
	}

	/**
	 * Returns cards of each Player's hand
	 * @return cards of each Player's hand
	 */
	public Card[][] playersCards() {
		Card[][] hands = new Card[players.length][];
		Player player;
		for(int handNum = 0; handNum < players.length; handNum++) {
			player = players[handNum];
			hands[handNum] = player.cards();
		}
		return hands;
	}

	public Card[] deckCards() {
		return deck.pile();
	}
	public Card[] discardCards() {
		return discard.pile();
	}
	/**
	 * Returns top card of the discard pile.
	 * @return discard's top card.
	 */
	public Card topCard() {
		return discard.topCard();
	}

	public static void main(String[] args) {
		//Makes card pool
		Card[] cards = new Card[16];
		for(int suit = 1; suit <= 4; suit++) {
			for(int rank = 0; rank <= 3; rank++) {
				Card card = new Card(suit, rank, false);
				cards[(suit - 1) * 4 + rank] = card;
			}
		}
		//Build players
		Player[] players = new Player[3];
		for(int numPlayer = 0; numPlayer < players.length; numPlayer++) {
			players[numPlayer] = new Player(new ScannerSuitChooser());
		}

		//Makes cardSystem
		Card[] deckCards = Arrays.copyOfRange(cards, 0, cards.length - 1);
		Card topCard = cards[cards.length - 1];
		CardSystem cardSystem = new CardSystem(players, deckCards, topCard);
		Scanner in = new Scanner(System.in);

		for(int numPlayer = 0; numPlayer < players.length; numPlayer++) { 
			cardSystem.drawCards(numPlayer, 4);
		}

		PrintStream out = System.out;
		Card[] hand;
		for(int numPlayer = 0; numPlayer < players.length; numPlayer++) {
			cardSystem.drawCard(numPlayer);
			out.println("hand: ");
			hand = cardSystem.playerCards(numPlayer);
			for(int cardNum = 0; cardNum < hand.length; cardNum++) {
				out.print("\t" + String.valueOf(cardNum + 1) + ". ");
				out.println(hand[cardNum]);
			}
			out.println("top card: " + cardSystem.topCard());
			out.print("select card: ");
			cardSystem.playCard(numPlayer, in.nextInt() - 1);
		}

		in.close();
	}
}
