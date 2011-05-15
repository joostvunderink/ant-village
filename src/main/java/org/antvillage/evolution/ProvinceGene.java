package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class ProvinceGene extends Gene {
	
	public ProvinceGene() {
		addParameter("value");
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.PROVINCE.cost) {
			currentValues.changeValue(Cards.PROVINCE, getParameter("value"));
		}
	}
}
