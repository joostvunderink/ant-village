package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;

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

}
