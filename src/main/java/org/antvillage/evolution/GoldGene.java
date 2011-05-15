package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class GoldGene extends Gene {
	
	public GoldGene() {
		addParameter("value");
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.GOLD.cost) {
			currentValues.changeValue(Cards.GOLD, getParameter("value"));
		}
	}

}
