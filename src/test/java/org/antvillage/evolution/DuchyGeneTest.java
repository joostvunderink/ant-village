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
		GameSetup gameSetup = new GameSetup();

		Bot p1 = new Bot();
		List<Player> players = new LinkedList<Player>();
		players.add(p1);

		List<Card> kingdomCards = new LinkedList<Card>();

		Game game = gameSetup.createGame(kingdomCards, players);

		game.gameTurn.activePlayer = p1;
		game.gameTurn.activePlayArea = p1.playArea;
		game.gameTurn.phase = Phase.BUY;
		
		game.gameTurn.money = 10;

		CardValues currentValues = new CardValues();
		currentValues.initFromSupply(p1);

		float value = 1.0f;
		DuchyGene duchyGene = new DuchyGene();
		duchyGene.setParameter("value", value);
		duchyGene.setParameter("provincesLeft", 5);
		p1.addGene(duchyGene);

		p1.supply.stacks.put(Cards.PROVINCE, 1);
		duchyGene.calculateBuyValues(currentValues, p1);
		assertEquals(value, currentValues.get(Cards.DUCHY), 0.001f);

		currentValues.clear();
		p1.supply.stacks.put(Cards.PROVINCE, 8);
		duchyGene.calculateBuyValues(currentValues, p1);
		assertEquals(0.0f, currentValues.get(Cards.DUCHY), 0.001f);
	}
}
