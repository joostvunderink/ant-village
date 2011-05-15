package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
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
	public void testGetDesiredCard() {
		CardValues cardValues = new CardValues();
		cardValues.put(Cards.COPPER, 1.0f);
		cardValues.put(Cards.GOLD, 2.2f);
		assertEquals(cardValues.getDesiredCard(), Cards.GOLD);
	}
	
	@Test
	public void testInitFromSupply() {
		CardValues cardValues = new CardValues();
		Supply supply = new Supply();
		supply.init(2, new LinkedList<Card>());
		
		cardValues.initFromSupply(supply);
		assertEquals(cardValues.get(Cards.COPPER), 0.0f, 0.001f);
		assertEquals(cardValues.get(Cards.ESTATE), 0.0f, 0.001f);
		assertEquals(cardValues.get(Cards.CURSE), 0.0f, 0.001f);
	}
	
	
}
