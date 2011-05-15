package org.antvillage.strategy;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.Player;
import org.antvillage.game.Phase;

public class BmPocStrategy extends Player {
	
	public void takeTurn() {
		gameTurn.endPhase(Phase.ACTION);
		
		playAllTreasures();

		gameTurn.endPhase(Phase.MONEY);		
		
		boolean timeToBuyDuchy = isItTimeToBuyDuchy();
		boolean timeToBuyEstate = isItTimeToBuyEstate();
		
		if (gameTurn.money >= 8 && supply.countCard(Cards.PROVINCE) > 0) {
			gameTurn.buyCard(Cards.PROVINCE);
		} else if (gameTurn.money >= 5 && timeToBuyDuchy && supply.countCard(Cards.DUCHY) > 0) {
			gameTurn.buyCard(Cards.DUCHY);
		} else if (gameTurn.money >= 2 && timeToBuyEstate && supply.countCard(Cards.ESTATE) > 0) {
			gameTurn.buyCard(Cards.ESTATE);
		} else if (gameTurn.money >= 6 && supply.countCard(Cards.GOLD) > 0) {
			gameTurn.buyCard(Cards.GOLD);
		} else if (gameTurn.money >= 3 && supply.countCard(Cards.SILVER) > 0) {
			gameTurn.buyCard(Cards.SILVER);
		} else {
			gameTurn.endPhase(Phase.BUY);
		}
	}

	public boolean isItTimeToBuyDuchy() {
		int numProvincesInSupply = supply.countCard(Cards.PROVINCE);
		
		if (playerCount <= 2 && numProvincesInSupply <= 5) {
			return true;
		}
		
		if (playerCount >= 3 && playerCount <= 4 && numProvincesInSupply <= 8) {
			return true;
		}
		
		return false;
	}
	
	public boolean isItTimeToBuyEstate() {
		int numProvincesInSupply = supply.countCard(Cards.PROVINCE);
		
		if (playerCount <= 2 && numProvincesInSupply <= 2) {
			return true;
		}
		
		if (playerCount >= 3 && playerCount <= 4 && numProvincesInSupply <= 3) {
			return true;
		}
		
		return false;
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
