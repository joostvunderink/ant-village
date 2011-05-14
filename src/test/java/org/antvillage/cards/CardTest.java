package org.antvillage.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

	@Test
	public void testActionCard() {
		ActionCard card = new ActionCard();
		assertTrue(card.isAction());
		assertFalse(card.isTreasure());
		assertFalse(card.isVictory());
	}

	@Test
	public void testTreasureCard() {
		TreasureCard card = new TreasureCard();
		assertFalse(card.isAction());
		assertTrue(card.isTreasure());
		assertFalse(card.isVictory());
	}

	@Test
	public void testVictoryCard() {
		VictoryCard card = new VictoryCard();
		assertFalse(card.isAction());
		assertFalse(card.isTreasure());
		assertTrue(card.isVictory());
	}
}
