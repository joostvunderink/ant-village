package org.antvillage.evolution;

import java.util.HashMap;
import java.util.Map;

import org.antvillage.cards.Card;
import org.antvillage.game.Player;

/**
 * CardValues is a collection of cards and their (float) values.
 * A CardValues object can be initialised from a Supply object; all cards of the Supply
 * object will be put in the collection, with a score of 0. Empty stacks are skipped,
 * to make sure the genes don't accidentally choose to buy a card which is no longer
 * available.
 * 
 * @author Joost Vunderink
 *
 */
public class CardValues {
	private Map<Card, Float> cardValues = new HashMap<Card, Float>();
	
	public void initFromSupply(Player player) {
		cardValues.clear();
		for (Card card: player.supply.stacks.keySet()) {
			if (player.supply.countCard(card) > 0) {
				
				int cardCost = card.getCost(player.playArea);
				if ( player.gameTurn.money >= cardCost) {
					cardValues.put(card, (float)0);
				}
			}
		}
	}

	public void put(Card card, Float value) {
		cardValues.put(card, value);
	}

	public Float get(Card card) {
		return cardValues.get(card);
	}
	
	public void clear() {
		for (Card card: cardValues.keySet()) {
			cardValues.put(card, (float)0);
		}
	}
	
	public void changeValue(Card card, Float value) {
		if (cardValues.get(card) == null) {
			// trying to assign a value to a card that is not available for purchase. Ignoring.
			return;
		}
		
		cardValues.put(card, cardValues.get(card) + value);
	}
	
	public Card getDesiredCard() {
		Float maxScore = -10000.0f;
		
		Card desiredCard = null;
		
		for (Card card: cardValues.keySet()) {
			if (cardValues.get(card) > maxScore) {
				maxScore = cardValues.get(card);
				desiredCard = card;
			}
		}
		
		if (desiredCard == null) {
			throw new RuntimeException("Could not find a desired card");
		}
		
		return desiredCard;
	}
}
