package org.antvillage.game;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.log.Recorder;

/**
 * This class implements a single game.
 * 
 * @author Verik
 * 
 */
public class Game {
	private static final Recorder recorder = Recorder.getRecorder();

	private static final int MAX_ROUNDS = 150;

	private int round = 1;
	public List<Player> players;
	public GameTurn gameTurn;
	public Supply supply;

	public Player start() {
		Player playerEndingGame = runGameTurns();
		return determineWinners(playerEndingGame);
	}

	public Player determineWinners(Player playerEndingGame) {
		List<Player> winners = null;
		
		float highestScore = -10000;
		float turnNotPlayedEndBonus = 0;
		
		for(Player player: players) {
			float score = player.playArea.getVictoryPoints() + turnNotPlayedEndBonus;
			if (player.equals(playerEndingGame)) {
				turnNotPlayedEndBonus = 0.1f;
			}
			
			if (score > highestScore) {
				winners = new LinkedList<Player>();
				highestScore = score;
			}
			if (score == highestScore) {
				winners.add(player);
			}
		}
		
		if ( winners.size() > 1) {
			recorder.info("\nTie between {}", winners);
			return null;
		}
		else {
			Player winner = winners.get(0); 
			recorder.info("\nWinner: {}", winner);
			return winner;
			
		}
	}

	private Player runGameTurns() {
		while (round < MAX_ROUNDS) {
			recorder.info("\nROUND {}", round);
			for (Player player : players) {
				runTurn(player);
				if (supply.isGameFinished()) {
					return player;
				}
			}
			round++;
		}
		throw new RuntimeException("Failed to finish game in " + MAX_ROUNDS + " turns, aborting. ");
	}

	private void runTurn(Player player) {
		recorder.info("\n{}", player);
		gameTurn.takeTurn(player);
	}

}
