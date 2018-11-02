package uno;

/**
 * Represents an Card. The suits are the colors, where a suit of one is red,
 * a suit of two is yellow, a suit of three is green, a suit of four is blue,
 * and a suit of five is for unplayed wild cards. The ranks 0 through 9 are for
 * their respectively numbered cards, the rank 10 is for skip cards, the rank
 * 11 is for reverse cards, the rank 12 is for draw two cards, the rank 13 is
 * for standard wild cards, and the rank 14 is for draw four standard cards.
 * UnoCards that have a wildness of true are wild cards, which in a game of
 * Uno, means that when players play them, they get to choose one of the
 * non-wild suits.
 * @author trevor
 */
public class Card {
	public static final String[] SUITS = {
		"", "Red", "Yellow", "Green", "Blue"
	};
	public static final String[] RANKS = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse",
		"Draw Two", "Card", "Draw Four"
	};
	protected final int suit;
	protected final int rank;
	protected final boolean wildness;

	public Card(int suit, int rank, boolean wildness) {
		this.suit = suit;
		this.rank = rank;
		this.wildness = wildness;
	}
	public Card(Card card) {
		suit = card.suit;
		rank = card.rank;
		wildness = card.wildness;
	}

	public int suit() {
		return suit;
	}
	public int rank() {
		return rank;
	}
	public boolean isWild() {
		return wildness;
	}

	public String toString() {
		String cardReadout = "";

		if(wildness) {
			cardReadout += "Wild";
		}
		cardReadout += SUITS[suit] + " " + RANKS[rank];

		return cardReadout;
	}
}
