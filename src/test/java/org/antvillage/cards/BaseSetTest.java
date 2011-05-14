package org.antvillage.cards;

import static org.junit.Assert.assertEquals;

import org.antvillage.cards.base.VillageCard;
import org.junit.Test;


public class BaseSetTest {
	private Object[][] tests = {
		/*
		    class,
	    	vp, cost, money, buys, draws, actions
		    is_action, is_treasure, is_victory
		*/
		{
			new VillageCard(),
			0, 3, 0, 0, 1, 2,
			true, false, false
		},
	};
	
	@Test
	public void testAll() {
		for (int i = 0; i < tests.length; i++) {
			Object[] testparams = tests[i];
			BaseCard card = (BaseCard)testparams[0];
	    	assertEquals(card.getVictoryPoints(), testparams[1]);
	    	assertEquals(card.getCost(), testparams[2]);
	    	assertEquals(card.getMoneyValue(), testparams[3]);
	    	assertEquals(card.getExtraBuys(), testparams[4]);
	    	assertEquals(card.getExtraDraws(), testparams[5]);
	    	assertEquals(card.getExtraActions(), testparams[6]);
	    	assertEquals(card.isAction(), testparams[7]);
	    	assertEquals(card.isTreasure(), testparams[8]);
	    	assertEquals(card.isVictory(), testparams[9]);
		}
	}

}
