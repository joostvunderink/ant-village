package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;

import org.antvillage.cards.Cards;
import org.junit.Test;


public class ProvinceGeneTest {
	@Test
	public void testProvinceGene() {
		Bot bot = GeneTestHelper.setupGeneTest();

		bot.gameTurn.money = 10;

		CardValues currentValues = new CardValues();
		currentValues.initFromSupply(bot);

		float value = 1.0f;
		ProvinceGene provinceGene = new ProvinceGene();
		provinceGene.setParameter("value", value);
		bot.addGene(provinceGene);

		provinceGene.calculateBuyValues(currentValues, bot);
		assertEquals(value, currentValues.get(Cards.PROVINCE), 0.001f);

		currentValues.clear();
		bot.gameTurn.money = 5;
		provinceGene.calculateBuyValues(currentValues, bot);
		assertEquals(0.0f, currentValues.get(Cards.PROVINCE), 0.001f);
	}
}
