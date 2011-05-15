package org.antvillage.game;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.junit.Before;
import org.junit.Test;

public class GameTurnTest {
	
	public GameTurn gameTurn;
	Player player;
	PlayArea playArea;

	@Before
	public void setUp() throws Exception {
		gameTurn = new GameTurn();
		player = new Player();
		playArea = new PlayArea();
		player.playArea = playArea;
		gameTurn.takeTurn(player);
		Supply supply = new Supply();
		List<Card> kingdomCards = new LinkedList<Card>();
		kingdomCards.add(Cards.VILLAGE);
		supply.init(2, kingdomCards);
		gameTurn.supply = supply;
	}

	@Test
	public void testTakeTurn() {
		assertEquals(1, gameTurn.actions);
		assertEquals(1, gameTurn.buys);
		assertEquals(0, gameTurn.money);
		assertEquals(Phase.ACTION, gameTurn.phase);
	}

	@Test
	public void testPlayTreasure() {
		player.playArea.hand.add(Cards.SILVER);
		gameTurn.endPhase(Phase.ACTION);
		assertEquals(0, gameTurn.money);
		
		assertTrue(player.playArea.playedPile.isEmpty());
		assertEquals(1, player.playArea.hand.size());

		
		gameTurn.playTreasure(Cards.SILVER);
		
		assertEquals(2, gameTurn.money);

		assertTrue(player.playArea.hand.isEmpty());
		assertEquals(1, player.playArea.playedPile.size());
	}

	@Test
	public void testEndPhase() {
		assertEquals(Phase.ACTION, gameTurn.phase);
		
		gameTurn.endPhase(Phase.ACTION);
		assertEquals(Phase.MONEY, gameTurn.phase);
		
		gameTurn.endPhase(Phase.MONEY);
		assertEquals(Phase.BUY, gameTurn.phase);
		
		gameTurn.endPhase(Phase.BUY);
		assertEquals(Phase.CLEANUP, gameTurn.phase);
		
		gameTurn.endPhase(Phase.CLEANUP);
		assertEquals(Phase.DRAW, gameTurn.phase);
		
		try {
			gameTurn.endPhase(Phase.BUY);
			fail("Failed to detect faulty end of game phase");
		}
		catch (RuntimeException expected) {
			// successfully detected the fault
		}
	}

	@Test
	public void testBuyCard() {
		try {
			gameTurn.buyCard(Cards.VILLAGE);
			fail("Failed to detect faulty phase");
		}
		catch (RuntimeException expected) {
			// successfully detected the fault
		}
		
		gameTurn.endPhase(Phase.ACTION);
		playArea.hand.add(Cards.GOLD);
		gameTurn.playTreasure(Cards.GOLD);
		
		gameTurn.endPhase(Phase.MONEY);

		assertEquals(0, playArea.discardPile.size());
		assertEquals(1, gameTurn.buys);
		assertEquals(3, gameTurn.money);

		gameTurn.buyCard(Cards.VILLAGE);

		assertEquals(1, playArea.discardPile.size());
		assertEquals(0, gameTurn.buys);
		assertEquals(0, gameTurn.money);
		
		try {
			gameTurn.buyCard(Cards.VILLAGE);
			fail("Failed to detect not enough buys");
		}
		catch (RuntimeException expected) {
			// successfully detected the fault
		}

		gameTurn.buys = 1;

		try {
			gameTurn.buyCard(Cards.VILLAGE);
			fail("Failed to detect not enough money");
		}
		catch (RuntimeException expected) {
			// successfully detected the fault
		}
	}

}
