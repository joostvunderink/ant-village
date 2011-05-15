package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class EstateGene extends Gene {
	public final int PARAMETER_PROVINCES_LEFT_MAX = 8;
	
	public EstateGene() {
		addParameter("value");
		addParameter("provincesLeft");
	}
	
	public void randomizeParameters() {
		super.randomizeParameters();
		float randomValue = (float)(PARAMETER_PROVINCES_LEFT_MAX * Math.random() + 1.0f);
		setParameter("provincesLeft", randomValue);
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.ESTATE.cost &&
				player.supply.countCard(Cards.PROVINCE) < getParameter("provincesLeft")) {
			currentValues.changeValue(Cards.ESTATE, getParameter("value"));
		}
	}

}
