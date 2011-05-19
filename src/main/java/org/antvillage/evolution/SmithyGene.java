package org.antvillage.evolution;

import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class SmithyGene extends Gene {
	public final int PARAMETER_SMITHY_MAX = 3;
	
	public final String P_BUY_VALUE = "buy value";
	public final String P_PLAY_VALUE = "play value";
	public final String P_MAX_COUNT = "max number";
	
	public SmithyGene() {
		addParameter(P_BUY_VALUE);
		addParameter(P_MAX_COUNT);
		addParameter(P_PLAY_VALUE);
	}
	
	public void randomizeParameters() {
		super.randomizeParameters();
		float randomValue = (float)(PARAMETER_SMITHY_MAX * Math.random() + 1.0f);
		setParameter(P_MAX_COUNT, randomValue);
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		if (player.gameTurn.money >= Cards.SMITHY.cost &&
				player.playArea.countCardInDeck(Cards.SMITHY) <= getParameter(P_MAX_COUNT) ) {
			currentValues.changeValue(Cards.SMITHY, getParameter(P_BUY_VALUE));
		}
	}

	public void calculateActionValues(CardValues currentValues, Player player) {
		if (player.playArea.hand.contains(Cards.SMITHY)) {
			currentValues.changeValue(Cards.SMITHY, getParameter(P_PLAY_VALUE));
		}
	}

}
