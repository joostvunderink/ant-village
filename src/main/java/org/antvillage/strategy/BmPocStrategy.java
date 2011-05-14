package org.antvillage.strategy;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class BmPocStrategy extends Player {
	
	// has ref to gameTurn
	// has ref to playArea
	// has ref to supply
	
	public void takeTurn() {
		/*
		 * tell gameTurn to end the action phase (not needed because we never have an Action card)
		 * play all money in hand
		 * tell gameTurn to end the money phase (not needed once last Treasure is played)
		 * buy the right card
		 * end of turn
		 */
		
		playAllTreasures();
		
		boolean timeToBuyDuchy = isItTimeToBuyDuchy();
		boolean timeToBuyEstate = isItTimeToBuyEstate();
		
		if (gameTurn.money() >= 8) {
			gameTurn.buyCard(Cards.PROVINCE);
		} else if (gameTurn.money() >= 5 && timeToBuyDuchy) {
			gameTurn.buyCard(Cards.DUCHY);
		} else if (gameTurn.money() >= 2 && timeToBuyEstate) {
			gameTurn.buyCard(Cards.ESTATE);
		} else if (gameTurn.money() >= 6) {
			gameTurn.buyCard(Cards.GOLD);
		} else if (gameTurn.money() >= 3) {
			gameTurn.buyCard(Cards.SILVER);
		} else {
			gameTurn.endPhase(Phases.BUY);
		}
	}

	public boolean isItTimeToBuyDuchy() {
		int numProvincesInSupply = supply.countCard(Cards.PROVINCE);
		int numPlayers = gameTurn.getNumberOfPlayers();
		
		if (numPlayers <= 2 && numProvincesInSupply <= 5) {
			return true;
		}
		
		if (numPlayers >= 3 && numPlayers <= 4 && numProvincesInSupply <= 8) {
			return true;
		}
		
		return false;
	}
	
	public boolean isItTimeToBuyEstate() {
		int numProvincesInSupply = supply.countCard(Cards.PROVINCE);
		
		if (numPlayers <= 2 && numProvincesInSupply <= 2) {
			return true;
		}
		
		if (numPlayers >= 3 && numPlayers <= 4 && numProvincesInSupply <= 3) {
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
			playArea.playTreasure(card);
		}
	}
}
