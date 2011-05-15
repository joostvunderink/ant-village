package org.antvillage.game;

import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.junit.Test;

public class GameSetupTest {
	@Test
	public void testGameSetup() {
		GameSetup gameSetup = new GameSetup();

		List<Player> players = GameSetupHelper.createTwoPlayerBmPoc();
		List<Card> kingdomCards = GameSetupHelper.createSimpleKingdom(Cards.VILLAGE);
		Game game = gameSetup.createGame(kingdomCards, players);

		game.start();
	}
}
