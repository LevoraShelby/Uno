package uno.cardpools;

import uno.Card;

public class SingleSourceCollector implements CardCollector {
	protected final CollectableCards source;

	public SingleSourceCollector(CollectableCards source) {
		this.source = source;
	}

	public Card[] collectCards() {
		Card[] cards = source.collectables();
		source.collect();
		return cards;
	}
}
