package org.antvillage.simulator;

import org.antvillage.evolution.Bot;
import org.antvillage.evolution.DuchyGene;
import org.antvillage.evolution.EstateGene;
import org.antvillage.evolution.Gene;
import org.antvillage.evolution.GoldGene;
import org.antvillage.evolution.ProvinceGene;
import org.antvillage.evolution.SilverGene;

/**
 * This class creates bots from genes.
 * 
 * @author Verik
 */
public class BotFactory {

	private NameGenerator nameGenerator = new NameGenerator();

	public void init() {
		nameGenerator.init();
	}

	public Bot createBot() {
		Bot bot = new Bot();
		bot.addGene(new EstateGene());
		bot.addGene(new DuchyGene());
		bot.addGene(new ProvinceGene());
		bot.addGene(new SilverGene());
		bot.addGene(new GoldGene());

		bot.name = nameGenerator.getName();

		return bot;
	}

	public Bot createRandomGenesBot() {
		Bot bot = createBot();

		for (Gene gene : bot.genes) {
			gene.randomizeParameters();
		}
		return bot;
	}

}
