package org.antvillage.game;

import org.antvillage.cards.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides the functionality for operating a game turn from the
 * perspective of a player.
 * 
 * @author Verik
 */
public class GameTurn {

	private static final Logger RECORDER = LoggerFactory.getLogger(Recorder.class);

	public Supply supply;
	private PlayArea activePlayArea;

	public int money;
	public int actions;
	public int buys;
	public TurnPhase phase;

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
		checkPhase(TurnPhase.MONEY);
		activePlayArea.play(card);
		money += card.getMoneyValue(activePlayArea);
	}

	private void checkPhase(TurnPhase requiredPhase) {
		if (phase != requiredPhase) {
			throw new RuntimeException("Tried to take action in wrong phase. Now in " + phase + ", but needs to be in: " + requiredPhase);
		}
	}

	public void endPhase(TurnPhase phaseToEnd) {
		checkPhase(phaseToEnd);
		if (phaseToEnd == TurnPhase.MONEY) {
			RECORDER.info("money: {}, buys: {}", money, buys);
		}
		
		phase = phaseToEnd.next();
	}

	public void buyCard(Card card) {
		checkPhase(TurnPhase.BUY);
		return;
	}
}
