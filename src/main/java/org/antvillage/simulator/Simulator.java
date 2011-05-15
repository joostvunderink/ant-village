package org.antvillage.simulator;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;

/**
 * This class provides the facilities to run simulations. 
 *
 * @author Verik
 */
public class Simulator {
	
	public void runRandomGeneration() {
		Evolver evolver = new Evolver();
		evolver.init();
		evolver.spawnNewRandomGeneration(4);
		
		List<Card> kingdomCards = new LinkedList<Card>();
		
		TournamentRound round = new TournamentRound();
		round.kingdomCards = kingdomCards;
		round.participants = evolver.getGenerationAsPlayers();;
//		round.run.games = 11;
		
		round.start();
	}

}
