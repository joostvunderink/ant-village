package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class SilverGene extends Gene {
	
	public SilverGene() {
		addParameter("value");
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.SILVER.cost) {
			currentValues.changeValue(Cards.SILVER, getParameter("value"));
		}
	}

}
