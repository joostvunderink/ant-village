package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class DuchyGene extends Gene {
	
	public DuchyGene() {
		addParameter("value");
		addParameter("provincesLeft");
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.DUCHY.cost &&
				player.supply.countCard(Cards.PROVINCE) < getParameter("provincesLeft")) {
			currentValues.changeValue(Cards.DUCHY, getParameter("value"));
		}
	}
	
}
