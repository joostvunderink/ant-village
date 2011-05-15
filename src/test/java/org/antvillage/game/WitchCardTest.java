package org.antvillage.game;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.junit.Test;

public class WitchCardTest {
	@Test
	public void testGameSetup() {
		GameSetup gameSetup = new GameSetup();

		Player p1 = new Player();
		Player p2 = new Player();
		List<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);

		List<Card> kingdomCards = new LinkedList<Card>();
		kingdomCards.add(Cards.WITCH);

		Game game = gameSetup.createGame(kingdomCards, players);

		game.gameTurn.activePlayer = p1;
		game.gameTurn.activePlayArea = p1.playArea;
		game.gameTurn.phase = Phase.ACTION;
		
		p1.playArea.hand.add(Cards.WITCH);
		game.gameTurn.playAction(Cards.WITCH);

		assertEquals(p2.playArea.discardPile.size(), 1);
		assertEquals(p2.playArea.discardPile.get(0), Cards.CURSE);
	}

}
