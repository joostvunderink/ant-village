package org.antvillage.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CoreCardTest {
	
	private Object[][] tests = {
			/*
			    class,
			    vp, cost, money, buys, draws, actions
			    is_action, is_treasure, is_victory
			*/
			{
				Cards.ESTATE,
				1, 2, 0, 0, 0, 0,
				false, false, true
			},
			{
				Cards.DUCHY,
				3, 5, 0, 0, 0, 0,
				false, false, true
			},
			{
				Cards.PROVINCE,
				6, 8, 0, 0, 0, 0,
				false, false, true
			},
			{
				Cards.COPPER,
				0, 0, 1, 0, 0, 0,
				false, true, false
			},
			{
				Cards.SILVER,
				0, 3, 2, 0, 0, 0,
				false, true, false
			},
			{
				Cards.GOLD,
				0, 6, 3, 0, 0, 0,
				false, true, false
			},
			{
				Cards.CURSE,
				-1, 0, 0, 0, 0, 0,
				false, false, false
			},
	};
	
	@Test
	public void testAll() {
		for (int i = 0; i < tests.length; i++) {
			Object[] testparams = tests[i];
			Card card = (Card)testparams[0];
			String txt = card.toString();
	    	assertEquals(txt, card.getVictoryPoints(), testparams[1]);
	    	assertEquals(txt, card.getCost(null), testparams[2]);
	    	assertEquals(txt, card.getMoneyValue(null), testparams[3]);
	    	assertEquals(txt, card.getExtraBuys(), testparams[4]);
	    	assertEquals(txt, card.getExtraDraws(), testparams[5]);
	    	assertEquals(txt, card.getExtraActions(), testparams[6]);
	    	assertEquals(txt, card.isAction(), testparams[7]);
	    	assertEquals(txt, card.isTreasure(), testparams[8]);
	    	assertEquals(txt, card.isVictory(), testparams[9]);
		}
	}
}
