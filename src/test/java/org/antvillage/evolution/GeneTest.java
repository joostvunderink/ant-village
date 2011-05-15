package org.antvillage.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.antvillage.game.Player;
import org.junit.Test;


public class GeneTest {
	@Test
	public void testAddParameter() {
		Gene gene = new Gene();
		String param = "test";
		gene.addParameter(param);
		
		assertEquals(gene.getParameter(param), 0.0f, 0.001f);
	}
	
	@Test
	public void testAddParameterTwiceFails() {
		Gene gene = new Gene();
		String param = "testparam";
		gene.addParameter(param);
		try {
			gene.addParameter(param);
			fail("Failed to add parameter " + param + " twice");
		} catch (RuntimeException expected) {
			// successfully detected absence
		}

	}

	@Test
	public void testSetGetParameter() {
		Gene gene = new Gene();
		String param = "test";
		float value = 4.2f;
		gene.addParameter(param);
		gene.setParameter(param, value);
		
		assertEquals(gene.getParameter(param), value, 0.001f);
	}

	@Test
	public void testGetNonExistingParameterFails() {
		Gene gene = new Gene();
		String param = "testparam";
		try {
			gene.getParameter(param);
			fail("Failed to get non-existing parameter " + param);
		} catch (RuntimeException expected) {
			// successfully detected absence
		}

	}

	@Test
	public void testSetNonExistingParameterFails() {
		Gene gene = new Gene();
		String param = "testparam";
		try {
			gene.setParameter(param, 1.0f);
			fail("Failed to set non-existing parameter " + param);
		} catch (RuntimeException expected) {
			// successfully detected absence
		}

	}

	@Test
	public void testRandomizeParameter() {
		Gene gene = new Gene();
		String param = "test";
		gene.addParameter(param);
		assertEquals(gene.getParameter(param), 0.0f, 0.001f);

		gene.randomizeParameters();
		assertTrue(gene.getParameter(param) > 0.5f);
	}

	@Test
	public void testCalculateBuyValuesFails() {
		Gene gene = new Gene();
		CardValues cardValues = new CardValues();
		Player player = new Player();
		
		try {
			gene.calculateBuyValues(cardValues, player);
			fail("Failed to call calculateBuyValues which needs to be implemented by child classes");
		} catch (RuntimeException expected) {
			// successfully detected absence
		}
	}

	@Test
	public void testSilverGeneClone() {
		SilverGene gene = new SilverGene();
		gene.setParameter("value", 1.0f);
		
		Gene gene2 = gene.clone();
		assertEquals(1.0f, gene2.getParameter("value"), 0.001f);
		
		gene.setParameter("value", 2.0f);
		assertEquals(2.0f, gene.getParameter("value"), 0.001f);
		assertEquals(1.0f, gene2.getParameter("value"), 0.001f);
	}
}
