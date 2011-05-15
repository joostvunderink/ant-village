package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class EstateGene extends Gene {
	
	public EstateGene() {
		addParameter("value");
		addParameter("provincesLeft");
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.ESTATE.cost &&
				player.supply.countCard(Cards.PROVINCE) < getParameter("provincesLeft")) {
			currentValues.changeValue(Cards.ESTATE, getParameter("value"));
		}
	}

}
