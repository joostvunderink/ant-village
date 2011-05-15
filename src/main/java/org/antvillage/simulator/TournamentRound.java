package org.antvillage.simulator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antvillage.cards.Card;
import org.antvillage.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class defines the tournament round. Such a round consists of one supply 
 * (one selection of kingdom action cards) and each combination of bots will play
 * a run of games with that supply.
 *
 * @author Verik
 */
public class TournamentRound {
	private static final Logger logger = LoggerFactory.getLogger(TournamentRound.class);

	public List<Card> kingdomCards;
	public List<Player> participants;
	public Map<Player, Integer> wins;
	public Run run = new Run();
	
	public Map<Player, Integer> start() {
		init();
		run.kingdomCards = kingdomCards;
		
		for (Player player1: participants) {
			for (Player player2: participants) {
				if (!player1.equals(player2)) {
					playRun(player1, player2);
				}
			}
		}
		
		logRoundResults();
		
		return wins;
	}

	private void logRoundResults() {
		logger.info("Results for round: {}", kingdomCards);
		for (Player player: participants) {
			int roundWins = wins.get(player);
			logger.info("{} wins : {}", player, roundWins);
		}
	}

	private void playRun(Player player1, Player player2) {
		List<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		run.players = players;
		Map<Player, Integer> runResults = run.start();
		
		processResults(players, runResults);
	}

	private void processResults(List<Player> players, Map<Player, Integer> runResults) {
		for (Player player: players) {
			int runWinsForPlayer = runResults.get(player);
			int roundWinsForPlayer = wins.get(player);
			
			roundWinsForPlayer += runWinsForPlayer;
			wins.put(player, roundWinsForPlayer);
		}
	}

	private void init() {
		wins = new HashMap<Player, Integer>();
		for (Player player: participants) {
			wins.put(player, 0);
		}
	}
}
