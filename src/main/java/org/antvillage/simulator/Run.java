package org.antvillage.simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antvillage.cards.Card;
import org.antvillage.game.Game;
import org.antvillage.game.GameSetup;
import org.antvillage.game.Player;
import org.antvillage.log.Recorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class defines a run of multiple games with the same setup. A run is a unit that is useful for
 * measuring the effectiveness of strategies. A typical run should consists of at least 10000 games 
 * to have a win rate delta in the order of a few percents.
 *
 * @author Verik
 */
public class Run {
	private static final Logger logger = LoggerFactory.getLogger(Run.class);
	private static final Recorder recorder = Recorder.getRecorder();

	public int games = 1000;
	public List<Player> players;
	public List<Card> kingdomCards;
	public Map<Player, Integer> wins;
	public int ties;
	
	public Map<Player, Integer> start() {
		init();
		adjustLogLevelForGames();
		long start = System.currentTimeMillis();
		playGames();
		logOutcome(start);
		
		return wins;
	}

	private void playGames() {
		GameSetup gameSetup = new GameSetup();
		for (int i = 0; i < games; i++) {
			Game game = gameSetup.createGame(kingdomCards, players);
			Player winner = game.start();
			updateWins(winner);
		}
	}

	private void logOutcome(long start) {
		long end = System.currentTimeMillis();
		long duration = end - start;
		
		int totalWins = games - ties;
		
		for (Player player: players) {
			float playerWins = wins.get(player);
			float percentageWins = (float)(100.0 * playerWins) / totalWins;
			
			Object[] args = { player, playerWins, percentageWins };
			logger.info("{} wins {} ({} %)", args);
		}
		logger.info("Duration: {} ms)", duration);
	}

	private void init() {
		wins = new HashMap<Player, Integer>();
		for (Player player: players) {
			wins.put(player, 0);
		}
		ties = 0;
	}

	private void adjustLogLevelForGames() {
		recorder.adjustForGames(games);
	}

	private void updateWins(Player winner) {
		if (winner == null) {
			ties ++;
		}
		else {
			int winsForPlayer = wins.get(winner);
			winsForPlayer ++;
			wins.put(winner, winsForPlayer);
		}
	}

}
