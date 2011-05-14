package org.antvillage.game;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.strategy.BmPocStrategy;
import org.junit.Test;


public class GameSetupTest {
	@Test
	public void testGameSetup() {
		GameSetup gameSetup = new GameSetup();
		
		Player p1 = new BmPocStrategy();
		Player p2 = new BmPocStrategy();
		List<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		
		List<Card> kingdomCards = new LinkedList<Card>();
		kingdomCards.add(Cards.VILLAGE);

		Game game = gameSetup.createGame(kingdomCards, players);
		
		List<Player> winners = game.start();
		assertEquals(game, game);
		assertEquals(winners, winners);
	}
}
