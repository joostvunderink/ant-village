package org.antvillage.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antvillage.cards.Card;

/**
 * This class defines the supply that is used in a single game.
 * 
 * @author Verik
 */
public class Supply {

	private static final int[] VICTORY_CARDS_FOR_PLAYERS = { 0, 8, 8, 12, 12 };

	public Map<Card, Integer> stacks = new HashMap<Card, Integer>();
	public int emptyStacks = 0;

	public Supply() {
		super();
	}

	public void takeCard(Card card) {
		Integer count = stacks.get(card);
		if (count == null || count == 0) {
			throw new RuntimeException("Card " + card + " not present in supply.");
		}
		
		count --;
		if ( count == 0) {
			this.emptyStacks ++;
		}

		stacks.put(card, count );
	}

	public void init(int number_of_players, List<Card> kingdomCards) {
		createStack(Card.COPPER, 60);
		createStack(Card.SILVER, 40);
		createStack(Card.GOLD, 30);

		int numberOfCurses = (number_of_players - 1) * 10;
		createStack(Card.CURSE, numberOfCurses);

		int numberOfVictoryCards = VICTORY_CARDS_FOR_PLAYERS[number_of_players];
		createStack(Card.ESTATE, numberOfVictoryCards);
		createStack(Card.DUCHY, numberOfVictoryCards);
		createStack(Card.PROVINCE, numberOfVictoryCards);

		for (Card kingdomCard : kingdomCards) {
			int stackSize = 10;
			if (kingdomCard.implementation.isVictory()) {
				stackSize = numberOfVictoryCards;
			}
			createStack(kingdomCard, stackSize);
		}
	}

	private void createStack(Card card, int count) {
		stacks.put(card, count);
	}

}
