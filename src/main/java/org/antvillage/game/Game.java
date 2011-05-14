package org.antvillage.game;

import java.util.LinkedList;
import java.util.List;


/**
 * This class implements a single game.
 * 
 * @author Verik
 *
 */
public class Game {

	private static final int MAX_ROUNDS = 150;
	
	public List<Player> players = new LinkedList<Player>();




	public List<Player> start() {
		Player playerEndingGame = runGameTurns();
		
		return determineWinners(playerEndingGame);
	}


	private List<Player> determineWinners(Player playerEndingGame) {
		List<Player> winners = new LinkedList<Player>();
		return winners;
	}


	private Player runGameTurns() {
		return null;
	}


}
