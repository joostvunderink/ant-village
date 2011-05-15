package org.antvillage.simulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameGeneratorTest {

	@Test
	public void testInit() {
		NameGenerator generator = new NameGenerator();
		generator.init();
		assertEquals(5163, generator.names.size());
	}

}
