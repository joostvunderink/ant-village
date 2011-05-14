package org.antvillage.cards;

import static org.junit.Assert.assertEquals;

import org.antvillage.cards.core.CopperCard;
import org.antvillage.cards.core.CurseCard;
import org.antvillage.cards.core.DuchyCard;
import org.antvillage.cards.core.EstateCard;
import org.antvillage.cards.core.GoldCard;
import org.antvillage.cards.core.ProvinceCard;
import org.antvillage.cards.core.SilverCard;
import org.junit.Test;


public class CoreCardTest {
	
	private Object[][] tests = {
			/*
			    class,
			    vp, cost, money, buys, draws, actions
			    is_action, is_treasure, is_victory
			*/
			{
				new EstateCard(),
				1, 2, 0, 0, 0, 0,
				false, false, true
			},
			{
				new DuchyCard(),
				3, 5, 0, 0, 0, 0,
				false, false, true
			},
			{
				new ProvinceCard(),
				6, 8, 0, 0, 0, 0,
				false, false, true
			},
			{
				new CopperCard(),
				0, 0, 1, 0, 0, 0,
				false, true, false
			},
			{
				new SilverCard(),
				0, 3, 2, 0, 0, 0,
				false, true, false
			},
			{
				new GoldCard(),
				0, 6, 3, 0, 0, 0,
				false, true, false
			},
			{
				new CurseCard(),
				-1, 0, 0, 0, 0, 0,
				false, false, false
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
