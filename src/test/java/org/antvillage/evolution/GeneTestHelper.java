package org.antvillage.evolution;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.game.Game;
import org.antvillage.game.GameSetup;
import org.antvillage.game.Phase;
import org.antvillage.game.Player;

public class GeneTestHelper {
	public static Bot setupGeneTest() {
		GameSetup gameSetup = new GameSetup();

		Bot bot = new Bot();
		List<Player> players = new LinkedList<Player>();
		players.add(bot);

		List<Card> kingdomCards = new LinkedList<Card>();

		Game game = gameSetup.createGame(kingdomCards, players);

		game.gameTurn.activePlayer = bot;
		game.gameTurn.activePlayArea = bot.playArea;
		game.gameTurn.phase = Phase.BUY;

		return bot;
	}
}
