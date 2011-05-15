package org.antvillage.game;

import org.junit.Test;

/**
 * This test exists only to deal with the unfortunate side-effect of the ECLemma
 * coverage tool. This tool cannot handle Enums perfectly, and scores them as
 * partly untested. This muddles the coverage numbers, and the workaround is to
 * just 'hit' the generated enum methods.
 * 
 * @author Verik
 */
public class PhaseTest {

	@Test
	public void undoCoverageLeak() {
		Phase.valueOf(Phase.ACTION.name());
		Phase.values();
	}

}
