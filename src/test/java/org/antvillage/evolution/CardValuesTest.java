package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.GameTurn;
import org.antvillage.game.PlayArea;
import org.antvillage.game.Player;
import org.antvillage.game.Supply;
import org.junit.Test;


public class CardValuesTest {
	@Test
	public void testPutAndGet() {
		CardValues cardValues = new CardValues();
		cardValues.put(Cards.COPPER, 1.0f);
		assertEquals(cardValues.get(Cards.COPPER), 1.0f, 0.001f);
	}
	
	@Test
	public void testChangeValue() {
		CardValues cardValues = new CardValues();
		cardValues.put(Cards.COPPER, 0.0f);
		cardValues.changeValue(Cards.COPPER, 1.0f);
		assertEquals(cardValues.get(Cards.COPPER), 1.0f, 0.001f);
	}

	@Test
	public void testChangeValueNonexistingCard() {
		CardValues cardValues = new CardValues();
		cardValues.changeValue(Cards.COPPER, 1.0f);
		
		assertNull(cardValues.get(Cards.COPPER));
	}

	@Test
	public void testClear() {
		CardValues cardValues = new CardValues();
		cardValues.put(Cards.COPPER, 1.0f);
		assertEquals(cardValues.get(Cards.COPPER), 1.0f, 0.001f);
		cardValues.clear();
		assertEquals(cardValues.get(Cards.COPPER), 0.0f, 0.001f);
	}

	@Test
	public void testGetDesiredCard() {
		CardValues cardValues = new CardValues();
		cardValues.put(Cards.COPPER, 1.0f);
		cardValues.put(Cards.GOLD, 2.2f);
		assertEquals(cardValues.getDesiredCard(), Cards.GOLD);
	}

	@Test
	public void testGetDesiredCardFail() {
		CardValues cardValues = new CardValues();
		try {
			cardValues.getDesiredCard();
			fail("Failed to get desired card for empty cardValues");
		} catch (RuntimeException expected) {
			// successfully detected absence
		}
	}

	@Test
	public void testInitFromSupply() {
		CardValues cardValues = new CardValues();
		Supply supply = new Supply();
		supply.init(2, new LinkedList<Card>());
		
		Player player = new Player();
		PlayArea playArea = new PlayArea();
		player.playArea = playArea;
		GameTurn gameTurn = new GameTurn();
		player.gameTurn = gameTurn;
		gameTurn.supply = supply;
		player.supply = supply;
		
		cardValues.initFromSupply(player);
		assertEquals(cardValues.get(Cards.COPPER), 0.0f, 0.001f);
		assertEquals(cardValues.get(Cards.CURSE), 0.0f, 0.001f);
	}
	
	
}
