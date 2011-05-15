package org.antvillage.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.junit.Test;

public class PlayAreaTest {

	@Test
	public void testDrawCard() {
		PlayArea area = new PlayArea();

		assertNull(area.drawCard());

		area.drawPile.add(Cards.COPPER);

		assertEquals(Cards.COPPER, area.drawCard());
		assertNull(area.drawCard());

		area.drawCard();
		assertNull(area.drawCard());

		area.discardPile.add(Cards.SILVER);
		assertEquals(Cards.SILVER, area.drawCard());
		assertNull(area.drawCard());
	}

	@Test
	public void testRevealCard() {
		PlayArea area = new PlayArea();

		assertNull(area.revealCard());

		area.drawPile.add(Cards.COPPER);

		assertEquals(Cards.COPPER, area.revealCard());
		assertEquals(Cards.COPPER, area.revealCard());

		area.drawCard();
		assertNull(area.revealCard());

		area.discardPile.add(Cards.SILVER);
		assertEquals(Cards.SILVER, area.revealCard());
		assertEquals(Cards.SILVER, area.revealCard());
	}

	@Test
	public void testReshuffle() {
		testShuffleExpectation(Cards.SILVER, Cards.GOLD);
		testShuffleExpectation(Cards.GOLD, Cards.SILVER);
	}

	@Test
	public void testPlay() {
		PlayArea area = new PlayArea();

		try {
			area.play(Cards.VILLAGE);
			fail("Failed to detect absense of village in hand ");
		} catch (RuntimeException expected) {
			// successfully detected absence
		}

		area.hand.add(Cards.VILLAGE);

		assertEquals(1, area.hand.size());
		assertEquals(0, area.playedPile.size());

		area.play(Cards.VILLAGE);

		assertEquals(0, area.hand.size());
		assertEquals(1, area.playedPile.size());
	}

	private void testShuffleExpectation(Card firstExpected, Card secondExpected) {
		boolean success = false;
		int count = 0;
		while (!success && count < 20) {
			PlayArea area = new PlayArea();

			area.discardPile.add(Cards.SILVER);
			area.discardPile.add(Cards.GOLD);

			area.revealCard();

			assertEquals(2, area.drawPile.size());
			if (firstExpected == area.drawCard() && secondExpected == area.drawCard()) {
				success = true;
			}
		}
		assertTrue(success);
	}

}
