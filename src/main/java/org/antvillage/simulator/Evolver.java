package org.antvillage.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.antvillage.evolution.Bot;
import org.antvillage.evolution.Gene;
import org.antvillage.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the evolution of genes.
 * 
 * @author Verik
 */
public class Evolver {
	private static final Logger logger = LoggerFactory.getLogger(Evolver.class);

	private BotFactory botFactory = new BotFactory();

	public Random random = new Random();
	public List<Bot> generation;
	public int generationNumber = 0;
	public Map<Integer, List<Bot>> generationHistory = new HashMap<Integer, List<Bot>>();
	
	public float MUTATION_CHANCE = 0.1f;
	public float MUTATION_FACTOR = 0.5f;

	public void init() {
		botFactory.init();
	}

	public void spawnNewRandomGeneration(int amountToSpawn) {
		if (amountToSpawn % 8 != 0) {
			throw new RuntimeException("amountToSpawn must be a multiple of 8 due to evolving mechanics. See Evolver.createChildren() method.");
			
		}
		
		generation = new LinkedList<Bot>();

		for (int i = 0; i < amountToSpawn; i++) {
			Bot bot = botFactory.createRandomGenesBot();
			generation.add(bot);
		}
		nextGeneration();
	}

	public List<Player> getGenerationAsPlayers() {
		List<Player> players = new ArrayList<Player>(generation);
		return players;
	}

	public void evolve(List<Player> sortedPlayers) {
		List<Bot> survivors = getTopHalfAsBots(sortedPlayers);

		this.generation = createChildren(survivors);
	
		nextGeneration();
	}

	private void nextGeneration() {
		if (logger.isDebugEnabled()) {
			
			int described = 0;
			
			logger.debug("----\nGeneration {}\n---", generationNumber);
			for (Bot bot: generation) {
				bot.describe();
				described ++;
				if (described >= 2) {
					break;
				}
			}
		}
		
		if (generationNumber % 10 == 0) {
			generationHistory.put(generationNumber, generation);
		}
		generationNumber++;
	}

	private List<Bot> createChildren(List<Bot> survivors) {

		List<Bot> children = new ArrayList<Bot>();

		Iterator<Bot> iterator = survivors.iterator();

		while (iterator.hasNext()) {
			Bot one = iterator.next();
			Bot two = iterator.next();
			Bot three = iterator.next();
			Bot four = iterator.next();

			createTwoChildren(one, two, children);
			createTwoChildren(three, four, children);
			createTwoChildren(one, three, children);
			createTwoChildren(two, four, children);
		}

		return children;
	}

	private void createTwoChildren(Bot father, Bot mother, List<Bot> children) {
		Bot child1 = createChild(father, mother);
		children.add(child1);
		
		Bot child2 = createChild(father, mother);
		children.add(child2);
	}

	private Bot createChild(Bot father, Bot mother) {
		Bot child = botFactory.createBot();

		Iterator<Gene> fatherGeneIterator = father.genes.iterator();
		Iterator<Gene> motherGeneIterator = mother.genes.iterator();
		for (Gene gene : child.genes) {
			Gene fatherGene = fatherGeneIterator.next();
			Gene motherGene = motherGeneIterator.next();
			if ( random.nextBoolean()) {
				updateGene(gene, fatherGene);
			}
			else {
				updateGene(gene, motherGene);
			}
		}

		return child;
	}

	private void updateGene(Gene childeGene, Gene parentGene) {
		for (String name: parentGene.parameters.keySet()) {
			float parentValue = parentGene.getParameter(name);
			
			float childeValue = parentValue;
			
			if (random.nextFloat() < MUTATION_CHANCE) {
				float deviation = (float)random.nextGaussian() * MUTATION_FACTOR;
				childeValue += deviation;
			}
			
			childeGene.setParameter(name, childeValue);
		}	
	}

	private List<Bot> getTopHalfAsBots(List<Player> sortedPlayers) {
		int survivorCount = sortedPlayers.size() / 2;
		List<Bot> survivors = new ArrayList<Bot>(survivorCount);

		for (Player player : sortedPlayers) {
			Bot bot = (Bot) player;
			survivors.add(bot);
			survivorCount--;
			if (survivorCount == 0) {
				break;
			}
		}

		return survivors;
	}

}
