package uno;

public interface SuitChooser {
	/**
	 * @Contract Returns a number between 1 and 4 that represents a suit. Can
	 * also return -1 if no color is chosen.
	 * @return a suit.
	 */
	public int chooseSuit();
}
