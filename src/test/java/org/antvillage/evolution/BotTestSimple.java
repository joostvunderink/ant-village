package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.Game;
import org.antvillage.game.GameSetup;
import org.antvillage.game.Phase;
import org.antvillage.game.Player;
import org.junit.Test;

public class BotTestSimple {
	
	@Test
	public void testBotClone() {
		SilverGene silverGene = new SilverGene();
		GoldGene goldGene = new GoldGene();
		silverGene.setParameter("value", 1.0f);

		Bot bot = new Bot();
		bot.addGene(silverGene);
		bot.name = "Henk";
		
		Bot bot2 = bot.clone();
		assertNotSame(bot, bot2);
		assertEquals("Henk", bot2.name);
		assertEquals(1.0f, bot2.genes.get(0).getParameter("value"), 0.001f);
		
		bot.genes.get(0).setParameter("value", 2.0f);
		assertEquals(2.0f, bot.genes.get(0).getParameter("value"), 0.001f);
		assertEquals(1.0f, bot2.genes.get(0).getParameter("value"), 0.001f);
		
		bot.addGene(goldGene);
		assertEquals(bot.genes.size(), 2);
		assertEquals(bot2.genes.size(), 1);
		
		bot.genes.clear();
		assertEquals(bot.genes.size(), 0);
		assertEquals(bot2.genes.size(), 1);
	}

	@Test
	public void testSilverBot() {
		Bot bot = new Bot();

		SilverGene silverGene = new SilverGene();
		silverGene.setParameter("value", 2.0f);

		bot.addGene(silverGene);

		GameSetup gameSetup = new GameSetup();

		List<Player> players = new LinkedList<Player>();
		players.add(bot);

		Game game = gameSetup.createGame(new LinkedList<Card>(), players);

		game.gameTurn.activePlayer = bot;
		game.gameTurn.activePlayArea = bot.playArea;
		game.gameTurn.phase = Phase.ACTION;
		game.gameTurn.buys = 1;

		bot.playArea.hand.add(Cards.GOLD);
		
		// This does not perform the cleanup + draw at the end, so the end result
		// will be that the discard pile contains just a single Silver.
		bot.takeTurn();

		assertEquals(1, bot.playArea.discardPile.size());
		assertEquals(bot.playArea.discardPile.get(0), Cards.SILVER);
	}

	@Test
	public void testSilverGoldBot() {
		Bot bot = new Bot();

		SilverGene silverGene = new SilverGene();
		silverGene.setParameter("value", 2.0f);
		GoldGene goldGene = new GoldGene();
		goldGene.setParameter("value", 3.0f);

		bot.addGene(silverGene);
		bot.addGene(goldGene);

		GameSetup gameSetup = new GameSetup();

		List<Player> players = new LinkedList<Player>();
		players.add(bot);

		Game game = gameSetup.createGame(new LinkedList<Card>(), players);

		game.gameTurn.activePlayer = bot;
		game.gameTurn.activePlayArea = bot.playArea;
		game.gameTurn.phase = Phase.ACTION;
		game.gameTurn.buys = 1;

		bot.playArea.hand.add(Cards.GOLD);
		bot.playArea.hand.add(Cards.GOLD);
		
		// This does not perform the cleanup + draw at the end, so the end result
		// will be that the discard pile contains just a single Gold.
		bot.takeTurn();

		assertEquals(1, bot.playArea.discardPile.size());
		assertEquals(bot.playArea.discardPile.get(0), Cards.GOLD);
	}
}
