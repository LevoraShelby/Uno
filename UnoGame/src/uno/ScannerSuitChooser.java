package uno;

import java.util.Scanner;

/**
 * @author trevor
 */
public class ScannerSuitChooser implements SuitChooser {
	Scanner in;

	public ScannerSuitChooser() {
		in = new Scanner(System.in);
	}

	public int chooseSuit() {
		int chosenSuit = in.nextInt();
		boolean isSuitChosen = chosenSuit >= 1 && chosenSuit <= 4;
		if(isSuitChosen || chosenSuit == -1) {
			return chosenSuit;
		} else {
			throw new RuntimeException("Illegal color chosen.");
		}
	}
}
