package org.antvillage.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurnPhaseTest {

	@Test
	public void testNext() {
		
		assertEquals(Phase.MONEY, Phase.ACTION.next);
		assertEquals(Phase.BUY, Phase.MONEY.next);
		assertEquals(Phase.CLEANUP, Phase.BUY.next);
		assertEquals(Phase.DRAW, Phase.CLEANUP.next);
		assertEquals(null, Phase.DRAW.next);
	}

}
