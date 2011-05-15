package org.antvillage.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;

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
		if (count == null) {
			throw new RuntimeException("Card " + card + " is not present in supply.");
		}
		
		if (count == 0) {
			throw new RuntimeException("The " + card + " is empty.");
		}
		
		count --;
		if (count == 0) {
			this.emptyStacks ++;
		}

		stacks.put(card, count );
	}

	public int countCard(Card card) {
		Integer count = stacks.get(card);
		
		if (count == null) {
			throw new RuntimeException("Card " + card + " is not present in supply.");
		}
		
		return count;
	}
	
	public void init(int number_of_players, List<Card> kingdomCards) {
		createStack(Cards.COPPER, 60);
		createStack(Cards.SILVER, 40);
		createStack(Cards.GOLD, 30);

		int numberOfCurses = (number_of_players - 1) * 10;
		createStack(Cards.CURSE, numberOfCurses);

		int numberOfVictoryCards = VICTORY_CARDS_FOR_PLAYERS[number_of_players];
		createStack(Cards.ESTATE, numberOfVictoryCards);
		createStack(Cards.DUCHY, numberOfVictoryCards);
		createStack(Cards.PROVINCE, numberOfVictoryCards);

		for (Card kingdomCard : kingdomCards) {
			int stackSize = 10;
			if (kingdomCard.isVictory()) {
				stackSize = numberOfVictoryCards;
			}
			createStack(kingdomCard, stackSize);
		}
	}

	private void createStack(Card card, int count) {
		stacks.put(card, count);
	}

	public boolean isGameFinished() {
		if (emptyStacks > 2) {
			return true;
		}
		int provincesLeft = stacks.get(Cards.PROVINCE);
		return provincesLeft == 0;
	}

}
