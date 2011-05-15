package org.antvillage.simulator;

import org.junit.Test;


public class SimulatorTest {

	@Test
	public void testStart() {
		Simulator simulator = new Simulator();
		
		simulator.runRandomGeneration();
		
		for (int i = 0; i < 100; i++ ) {
			simulator.runAndEvolveGeneration();
		}
	}

}
