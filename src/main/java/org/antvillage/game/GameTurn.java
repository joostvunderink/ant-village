package org.antvillage.game;

import org.antvillage.cards.Card;

/**
 * This class provides the functionality for operating a game turn from the
 * perspective of a player.
 * 
 * @author Verik
 */
public class GameTurn {

	private static final Recorder RECORDER = Recorder.getRecorder();

	public Supply supply;
	private PlayArea activePlayArea;

	public int money;
	public int actions;
	public int buys;
	public Phase phase;

	public void takeTurn(Player player) {
		activePlayArea = player.playArea;
		money = 0;
		actions = 1;
		buys = 1;
		phase = Phase.ACTION;

		player.takeTurn();
		
		activePlayArea.cleanUp();
		activePlayArea.drawHand();
	}

	public void playTreasure(Card card) {
		checkPhase(Phase.MONEY);
		activePlayArea.play(card);
		money += card.getMoneyValue(activePlayArea);
	}

	private void checkPhase(Phase requiredPhase) {
		if (phase != requiredPhase) {
			throw new RuntimeException("Tried to take action in wrong phase. Now in " + phase + ", but needs to be in: " + requiredPhase);
		}
	}

	public void endPhase(Phase phaseToEnd) {
		checkPhase(phaseToEnd);
		if (phaseToEnd == Phase.MONEY) {
			RECORDER.info("money: {}, buys: {}", money, buys);
		}
		
		phase = phaseToEnd.next;
	}

	public void buyCard(Card card) {
		checkPhase(Phase.BUY);
		if (buys < 1) {
			throw new RuntimeException("Tried to buy with no buys left.");
		}
		int cardCost = card.getCost(activePlayArea);
		
		if (cardCost > money) {
			throw new RuntimeException("Tried to buy " + card + " costing " + cardCost + " while having only " + money + " money left.");
		}
		
		supply.takeCard(card);
		activePlayArea.discardPile.add(card);
		
		buys --;
		money -= cardCost;
	}
}
