package uno;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uno.cardpools.CardSystem;
import uno.cardpools.Player;
import uno.turns.PlayerTurns;

/**
 * A classic game of Uno.
 * @author trevor
 */
public final class UnoGame {
	private CardSystem cards;
	private PlayerTurns turns;
	private OutputStream[] playerSuitChoosers;

	public UnoGame(int numPlayers) {
		turns = new PlayerTurns(numPlayers, numPlayers - 1);

		Card[] gameCards = buildCards();
		shuffle(gameCards);

		//Forces first Card in cards to be non-wild.
		for(int cardNum = 0; gameCards[cardNum].isWild(); cardNum++) {
			if(!gameCards[cardNum].isWild()) {
				Card newTopCard = gameCards[cardNum];
				Card oldTopCard = gameCards[0];
				gameCards[0] = newTopCard;
				gameCards[cardNum] = oldTopCard;
			}
		}

		//Creates Players for game.
		Player[] players = new Player[numPlayers];
		try {
			for(int playerNum = 0; playerNum < numPlayers; playerNum++) {
				PipedOutputStream writer = new PipedOutputStream();
				PipedInputStream wildSuitStream = new PipedInputStream(writer);
				writer.connect(wildSuitStream);
				players[playerNum] = new Player(wildSuitStream);
				playerSuitChoosers[playerNum] = writer;
			}
		} catch (IOException e) { e.printStackTrace(); }

		//Initializes cards with Cards and Players.
		Card[] deckCards = Arrays.copyOfRange(gameCards, 1, gameCards.length);
		Card topCard = gameCards[0];
		cards = new CardSystem(players, deckCards, topCard);
		executeCard(topCard);
	}


	/**
	 * Plays the Card associated with the cardNum of the current Player.
	 * @param cardNum
	 */
	public void playCard(int cardNum) {
		Card playedCard = cards.playerCards(turns.currentTurnIndex())[cardNum];
		if(!matchesTopCard(playedCard)) {
			throw new RuntimeException(
				"Card does not match with the game's top card."
			);
		}
		cards.playCard(turns.currentTurnIndex(), cardNum);
		executeCard(playedCard);
	}

	
	private void executeCard(Card playedCard) {
		if(playedCard.rank() >= 0 && playedCard.rank() <= 9) {
			turns.endTurn();
		}
		else if(playedCard.rank() == 10) {
			turns.skip();
		}
		else if(playedCard.rank() == 11) {
		//Create a subclass of PlayerTurns for two players?
			if(turns.numPlayers() > 2) {
				turns.reverse();
				turns.endTurn();
			} else if(turns.numPlayers() == 2) {
				turns.skip();
			}
		}
		else if(playedCard.rank() == 12) {
			cards.drawCards(turns.relativeTurnIndex(1), 2);
			turns.skip();
		}
		else if(playedCard.rank() == 14) {
			cards.drawCards(turns.relativeTurnIndex(1), 4);
			turns.skip();
		}	
	}


	private boolean matchesTopCard(Card cardToMatch) {
		Card topCard = cards.topCard();
		boolean matchesRank = topCard.rank() == cardToMatch.rank();
		boolean matchesSuit = topCard.suit() == cardToMatch.suit();
		return matchesRank || matchesSuit;
	}


	/**
	 * The current player draws a Card and ends their turn.
	 */
	public void pass() {
		cards.drawCard(turns.currentTurnIndex());
		turns.endTurn();
	}


	/**
	 * @return the index of the current Player.
	 */
	public int currentPlayer() {
		return turns.currentTurnIndex();
	}


	/**
	 * @returns an array holding arrays of Cards for each player.
	 */
	public Card[][] playerCards() {
		return cards.playersCards();
	}
	/**
	 * @return an array of Cards for the game's deck.
	 */
	public Card[] deckCards() {
		return cards.deckCards();
	}
	/**
	 * @return an array of Cards for the game's discard pile.
	 */
	public Card[] discardCards() {
		return cards.discardCards();
	}


	/**
	 * Builds a standard Uno deck.
	 * en.wikipedia.org/wiki/Uno_(card_game)#/media/File:UNO_cards_deck.svg
	 */
	private static Card[] buildCards() {
		Card[] cards = new Card[108];

		int cardIndex = 0;
		/**
		 * Builds two sets of Cards 1-9, skip, reverse, and draw +2 for each
		 * colors, as well as one set of zero cards, standard wild cards, and
		 * draw +4 wild cards also for each color.
		 */
		for(int suit = 1; suit <= 4; suit++) {
			//Builds a zero card for the current color.
			cards[cardIndex] = new Card(suit, 0, false);
			cardIndex++;

			/*
			 * Builds a set of the Cards 1-9, skip, reverse, and draw +2 for
			 * the current color.
			 * The ranks 10, 11, and 12 are reserved for skip, reverse, and
			 * draw +2 respectively.
			 */
			for(int rank = 1; rank <= 12; rank++) {
				cards[cardIndex] = new Card(suit, rank, false);
				cardIndex++;
				cards[cardIndex] = new Card(suit, rank, false);
				cardIndex++;
			}

			//Builds a standard wild card.
			cards[cardIndex] = new Card(0, 13, true);
			cardIndex++;
			//Builds a draw +4 wild card.
			cards[cardIndex] = new Card(0, 14, true);
			cardIndex++;
		}
		return cards;
	}


	private static void shuffle(Card[] cards) {
		List<Card> shuffledCards = new ArrayList<Card>(cards.length);
		for(Card card : cards) {
			shuffledCards.add(card);
		}
		Collections.shuffle(shuffledCards);
		shuffledCards.toArray(cards);
	}


	public static void main(String[] args) {
		Card[] cards = {new Card(0, 1, false), new Card(0, 2, false), new Card(0, 3, false)};
		for(Card card : cards) {
			System.out.print(card + " ");
		}
		System.out.println();
		shuffle(cards);
		for(Card card : cards) {
			System.out.print(card + " ");
		}
	}
}
