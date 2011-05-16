package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.Game;
import org.antvillage.game.GameSetup;
import org.antvillage.game.Phase;
import org.antvillage.game.Player;
import org.junit.Test;

public class DuchyGeneTest {
	@Test
	public void testDuchyGeneRandomize() {
		DuchyGene duchyGene = new DuchyGene();
		duchyGene.randomizeParameters();
		assertTrue(duchyGene.getParameter("provincesLeft") <= 9.0f);
		assertTrue(duchyGene.getParameter("provincesLeft") >= 0.0f);
	}

	@Test
	public void testDuchyGene() {
		Bot bot = GeneTestHelper.setupGeneTest();
		bot.gameTurn.money = 10;

		CardValues currentValues = new CardValues();
		currentValues.initFromSupply(bot);

		float value = 1.0f;
		DuchyGene duchyGene = new DuchyGene();
		duchyGene.setParameter("value", value);
		duchyGene.setParameter("provincesLeft", 5);
		bot.addGene(duchyGene);

		bot.supply.stacks.put(Cards.PROVINCE, 1);
		duchyGene.calculateBuyValues(currentValues, bot);
		assertEquals(value, currentValues.get(Cards.DUCHY), 0.001f);

		currentValues.clear();
		bot.supply.stacks.put(Cards.PROVINCE, 8);
		duchyGene.calculateBuyValues(currentValues, bot);
		assertEquals(0.0f, currentValues.get(Cards.DUCHY), 0.001f);
	}
}
