package org.antvillage.simulator;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antvillage.cards.Card;
import org.antvillage.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides the facilities to run simulations. 
 *
 * @author Verik
 */
public class Simulator {
	private static final Logger logger = LoggerFactory.getLogger(Simulator.class);
	
	private Evolver evolver;
	
	public void runRandomGeneration() {
		evolver = new Evolver();
		evolver.init();
		evolver.spawnNewRandomGeneration(8);
		
		runAndEvolveGeneration();
	}
	
	public void runAndEvolveGeneration() {
		List<Card> kingdomCards = new LinkedList<Card>();
		TournamentRound round = new TournamentRound();
		round.kingdomCards = kingdomCards;
		round.participants = evolver.getGenerationAsPlayers();;
		
		Map<Player, Integer> results = round.start();
		
		List<Player> sortedPlayers = sortByWins(results);
		evolver.evolve(sortedPlayers);

	}

	public List<Player> sortByWins(Map<Player, Integer> wins) {
		
		List<Player> sortedPlayers = new LinkedList<Player>(wins.keySet());
		
		PlayerComparator comparator = new PlayerComparator(wins);
		Collections.sort(sortedPlayers, comparator);

//		logger.info("\nSorted results:");
//		for (Player player: sortedPlayers) {
//			logger.info("{} ", player);
//		}

		return sortedPlayers;
	}
	
	class PlayerComparator implements Comparator<Player> {

		Map<Player, Integer> wins;
		
		private PlayerComparator(Map<Player, Integer> wins) {
			this.wins = wins;
		}

		public int compare(Player player1, Player player2) {
			
			int wins1 = wins.get(player1);
			int wins2 = wins.get(player2);
			
			return wins2 - wins1;
		}
	}
}
