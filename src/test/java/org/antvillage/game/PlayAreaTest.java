package org.antvillage.game;

import static org.junit.Assert.*;

import org.antvillage.cards.Card;
import org.junit.Test;

public class PlayAreaTest {

	@Test
	public void testDrawCard() {
		PlayArea area = new PlayArea();
		
		assertNull(area.drawCard());

		area.drawPile.add(Card.COPPER);
		
		assertEquals(Card.COPPER, area.drawCard());
		assertNull(area.drawCard());
		
		area.drawCard();
		assertNull(area.drawCard());

		
		area.discardPile.add(Card.SILVER);
		assertEquals(Card.SILVER, area.drawCard());
		assertNull(area.drawCard());
	}

	@Test
	public void testRevealCard() {
		PlayArea area = new PlayArea();
		
		assertNull(area.revealCard());

		area.drawPile.add(Card.COPPER);
		
		assertEquals(Card.COPPER, area.revealCard());
		assertEquals(Card.COPPER, area.revealCard());
		
		area.drawCard();
		assertNull(area.revealCard());

		
		area.discardPile.add(Card.SILVER);
		assertEquals(Card.SILVER, area.revealCard());
		assertEquals(Card.SILVER, area.revealCard());
	}

	@Test
	public void testReshuffle() {
		
		testShuffleExpectation(Card.SILVER, Card.GOLD);
		testShuffleExpectation(Card.GOLD, Card.SILVER);
	}

	private void testShuffleExpectation(Card firstExpected, Card secondExpected) {
		boolean success = false;
		int count = 0;
		while (!success && count < 20) {
			PlayArea area = new PlayArea();
			
			area.discardPile.add(Card.SILVER);
			area.discardPile.add(Card.GOLD);
			
			area.revealCard();
			
			assertEquals(2, area.drawPile.size());
			if (firstExpected == area.drawCard() && secondExpected == area.drawCard()) {
				success = true;
			}
		}
		assertTrue(success);
	}

}
