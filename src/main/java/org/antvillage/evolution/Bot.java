package org.antvillage.evolution;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.game.Phase;
import org.antvillage.game.Player;

public class Bot extends Player {
	CardValues cardValues = new CardValues();
	
	private List<Gene> genes = new LinkedList<Gene>();

	public Bot() {
	}
	
	public void addGene(Gene gene) {
		genes.add(gene);
	}
	
	
	public void takeTurn() {
		gameTurn.endPhase(Phase.ACTION);
		
		playAllTreasures();

		gameTurn.endPhase(Phase.MONEY);		

		cardValues.initFromSupply(supply);
		
		for (Gene gene: genes) {
			gene.calculateBuyValues(cardValues, this);
		}
		
		Card desiredCard = cardValues.getDesiredCard();
		
		gameTurn.buyCard(desiredCard);
		
		gameTurn.endPhase(Phase.BUY);
	}
	
	private void playAllTreasures() {
		List<Card> treasureCards = new LinkedList<Card>();

		for (Card card: playArea.hand) {
			if (card.isTreasure()) {
				treasureCards.add(card);
			}
		}
		
		for (Card card: treasureCards) {
			gameTurn.playTreasure(card);
		}
	}

}
