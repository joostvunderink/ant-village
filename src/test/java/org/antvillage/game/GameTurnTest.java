package org.antvillage.game;

import static org.junit.Assert.*;

import org.antvillage.cards.Cards;
import org.junit.Before;
import org.junit.Test;

public class GameTurnTest {
	
	public GameTurn gameTurn;
	Player player;

	@Before
	public void setUp() throws Exception {
		gameTurn = new GameTurn();
		player = new Player();
		player.playArea = new PlayArea();
	}

	@Test
	public void testTakeTurn() {
		gameTurn.takeTurn(player);
		
		assertEquals(1, gameTurn.actions);
		assertEquals(1, gameTurn.buys);
		assertEquals(0, gameTurn.money);
		assertEquals(TurnPhase.ACTION, gameTurn.phase);
	}

	@Test
	public void testPlayTreasure() {
		gameTurn.takeTurn(player);
		player.playArea.hand.add(Cards.SILVER);
		gameTurn.endPhase(TurnPhase.ACTION);
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
		gameTurn.takeTurn(player);
		assertEquals(TurnPhase.ACTION, gameTurn.phase);
		
		gameTurn.endPhase(TurnPhase.ACTION);
		assertEquals(TurnPhase.MONEY, gameTurn.phase);
		
		gameTurn.endPhase(TurnPhase.MONEY);
		assertEquals(TurnPhase.BUY, gameTurn.phase);
		
		gameTurn.endPhase(TurnPhase.BUY);
		assertEquals(TurnPhase.CLEANUP, gameTurn.phase);
		
		gameTurn.endPhase(TurnPhase.CLEANUP);
		assertEquals(TurnPhase.DRAW, gameTurn.phase);
		
		try {
			gameTurn.endPhase(TurnPhase.BUY);
			fail("Failed to detect faulty end of game phase");
		}
		catch (RuntimeException expected) {
			// successfully detected the fault
		}
	}

//	@Test
//	public void testBuyCard() {
//		fail("Not yet implemented");
//	}

}
