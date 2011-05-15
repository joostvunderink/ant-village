package org.antvillage.evolution;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.game.Phase;
import org.antvillage.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot extends Player {
	private static final Logger logger = LoggerFactory.getLogger(Bot.class);

	public CardValues cardValues = new CardValues();
	
	public List<Gene> genes = new LinkedList<Gene>();

	public void addGene(Gene gene) {
		genes.add(gene);
	}
	
	public void takeTurn() {
		gameTurn.endPhase(Phase.ACTION);
		
		playAllTreasures();

		gameTurn.endPhase(Phase.MONEY);		

		cardValues.initFromSupply(this);
		
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

	public Bot clone() {
		Bot bot = new Bot();
		
		bot.name = this.name;
		
		for (Gene gene: genes) {
			Gene newGene = gene.clone();
			bot.addGene(newGene);
		}
		
		return bot;
	}

	public void describe() {
		logger.debug("{}: ", name);
		
		for (Gene gene: genes) {
			gene.describe();
		}
		
	}
}
