package org.antvillage.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurnPhaseTest {

	@Test
	public void testNext() {
		
		assertEquals(TurnPhase.MONEY, TurnPhase.ACTION.next());
		assertEquals(TurnPhase.BUY, TurnPhase.MONEY.next());
		assertEquals(TurnPhase.CLEANUP, TurnPhase.BUY.next());
		assertEquals(TurnPhase.DRAW, TurnPhase.CLEANUP.next());
		assertEquals(null, TurnPhase.DRAW.next());
	}

}
