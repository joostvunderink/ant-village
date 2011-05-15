package org.antvillage.simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antvillage.evolution.Bot;
import org.antvillage.game.Player;

/**
 * This class manages the evolution of genes.
 *
 * @author Verik
 */
public class Evolver {

	private BotFactory botFactory = new BotFactory();

	public List<Bot> generation;
	
	public void init() {
		botFactory.init();
	}
	
	public void spawnNewRandomGeneration(int amountToSpawn) {
		generation = new LinkedList<Bot>();
		
		for( int i = 0; i < amountToSpawn; i++ ) {
			Bot bot = botFactory.createRandomGenesBot();
			generation.add(bot);
		}
	}
	
	public List<Player> getGenerationAsPlayers() {
		List<Player> players = new ArrayList<Player>(generation);
		return players;
	}
	
}
