package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.antvillage.cards.Cards;
import org.junit.Test;


public class EstateGeneTest {
	@Test
	public void testEstateGeneRandomize() {
		EstateGene estateGene = new EstateGene();
		estateGene.randomizeParameters();
		assertTrue(estateGene.getParameter("provincesLeft") <= 9.0f);
		assertTrue(estateGene.getParameter("provincesLeft") >= 0.0f);
	}

	@Test
	public void testEstateGene() {
		Bot bot = GeneTestHelper.setupGeneTest();
		bot.gameTurn.money = 10;

		CardValues currentValues = new CardValues();
		currentValues.initFromSupply(bot);

		float value = 1.0f;
		EstateGene estateGene = new EstateGene();
		estateGene.setParameter("value", value);
		estateGene.setParameter("provincesLeft", 5);
		bot.addGene(estateGene);

		bot.supply.stacks.put(Cards.PROVINCE, 1);
		estateGene.calculateBuyValues(currentValues, bot);
		assertEquals(value, currentValues.get(Cards.ESTATE), 0.001f);

		currentValues.clear();
		bot.supply.stacks.put(Cards.PROVINCE, 8);
		estateGene.calculateBuyValues(currentValues, bot);
		assertEquals(0.0f, currentValues.get(Cards.ESTATE), 0.001f);
	}

}
