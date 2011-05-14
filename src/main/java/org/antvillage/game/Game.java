package org.antvillage.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class implements a single game.
 * 
 * @author Verik
 *
 */
public class Game {
	private static final Logger RECORDER = LoggerFactory.getLogger(Recorder.class);


	private static final int MAX_ROUNDS = 150;
	
	private int round = 1;
	public List<Player> players = new LinkedList<Player>();
	public GameTurn gameTurn = new GameTurn();


	public List<Player> start() {
		Player playerEndingGame = runGameTurns();
		
		return determineWinners(playerEndingGame);
	}


	private List<Player> determineWinners(Player playerEndingGame) {
		List<Player> winners = new LinkedList<Player>();
		return winners;
	}


	private Player runGameTurns() {
		while (round < MAX_ROUNDS) {
			RECORDER.info("\nROUND {}", round);
			for (Player player: players) {
				runTurn(player);
				if (gameFinished()) {
					return player;
				}
			}
		}
		throw new RuntimeException("Failed to finish game in " + MAX_ROUNDS + " turns, aborting. " );
	}


	private boolean gameFinished() {
		
		return false;
	}


	private void runTurn(Player player) {
		RECORDER.info("\n{}", player);
		gameTurn.takeTurn(player);
	}


}
