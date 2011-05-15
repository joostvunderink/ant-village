package org.antvillage.game;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Cards;
import org.junit.Test;

public class GameTest {

	@Test
	public void testStart() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDetermineWinners() {
		Game game = new Game();
		List<Player> players = new LinkedList<Player>();
		Player winner;
		game.players = players;
		
		Player player1 = createAndAddPlayer(players, "player1");
		Player player2 = createAndAddPlayer(players, "player2");

 		// player 1: 6 (winner) (ending)
		// player 2: 0 
		player1.playArea.discardPile.add(Cards.PROVINCE);
		winner = game.determineWinners(player1);
		assertEquals(winner, player1);

 		// player 1: 6 (winner)
		// player 2: 6 (winner) (ending)
		player2.playArea.discardPile.add(Cards.PROVINCE);
		winner = game.determineWinners(player2);
		assertEquals(winner, null);

 		// player 1: 6          (ending)
		// player 2: 6 (winner)
		winner = game.determineWinners(player1);
		assertEquals(winner, player2);

 		// player 1: 6          (ending)
		// player 2: 6 (winner)
		// player 3: 3
		Player player3 = createAndAddPlayer(players, "player3");
		player3.playArea.discardPile.add(Cards.DUCHY);
		winner = game.determineWinners(player1);
		assertEquals(winner, player2);

 		// player 1: 6          (ending)
		// player 2: 6 (winner)
		// player 3: 6 (winner)
		player3.playArea.discardPile.add(Cards.DUCHY);
		winner = game.determineWinners(player1);
		assertEquals(winner, null);
		
 		// player 1: 6          (ending)
		// player 2: 6 
		// player 3: 9 (winner)
		player3.playArea.discardPile.add(Cards.DUCHY);
		winner = game.determineWinners(player1);
		assertEquals(winner, player3);
	}

	private void assertWinner(List<Player> players, Player player) {
		assertEquals(1, players.size());
		assertEquals(player, players.get(0));
	}

	private Player createAndAddPlayer(List<Player> players, String name) {
		Player player = new Player();
		player.name = name;
		PlayArea area = new PlayArea();
		player.playArea = area;
		
		players.add(player);
		return player;
	}

}
