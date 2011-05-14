package org.antvillage.game;

import org.antvillage.cards.Card;

/**
 * This class provides the functionality for operating a game turn from the
 * perspective of a player.
 * 
 * @author Verik
 */
public class GameTurn {

	public Supply supply;
	private PlayArea activePlayArea;

	public int money;
	public int actions;
	public int buys;
	private TurnPhase phase;

	public void takeTurn(Player player) {
		activePlayArea = player.playArea;

		money = 0;
		actions = 1;
		buys = 1;

		phase = TurnPhase.ACTION;

		player.takeTurn();

		// cleanup and draw cards
	}

	public void playTreasure(Card card) {
		checkPhase(TurnPhase.BUY);
		money += card.getMoneyValue(activePlayArea);
	}

	private void checkPhase(TurnPhase requiredPhase) {
		if (phase != requiredPhase) {
			throw new RuntimeException("Tried to take action in wrong phase. Now in " + phase + ", but needs to be in: " + requiredPhase);
		}

	}

}
