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
	
	public Bot createRandomGenesBot() {
		Bot bot = new Bot();
		randomizeAndAdd(new EstateGene(), bot);
		randomizeAndAdd(new DuchyGene(), bot);
		randomizeAndAdd(new ProvinceGene(), bot);
		randomizeAndAdd(new SilverGene(), bot);
		randomizeAndAdd(new GoldGene(), bot);
		
		bot.name = nameGenerator.getName();
	
		return bot;
	}

	private void randomizeAndAdd(Gene gene, Bot bot) {
		gene.randomizeParameters();
		bot.addGene(gene);
	}
	
}
