package org.antvillage.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.antvillage.cards.Card;
import org.antvillage.game.Supply;
import org.junit.Test;

public class SupplyTest {

	@Test
	public void testInitOnePlayer() {
		Supply supply = new Supply();
		
		assertEquals(0, supply.emptyStacks);
		assertEquals(0, supply.stacks.size());
		
		supply.init(1, new ArrayList<Card>());
		
		assertEquals(0, supply.emptyStacks);
		assertEquals(7, supply.stacks.size());
		
		assertTreasureCards(supply);
		assertVictoryCards(supply, 8);
		assertCurses(supply,0);
	}

	@Test
	public void testInitTwoPlayers() {
		Supply supply = new Supply();
		supply.init(2, new ArrayList<Card>());

		assertTreasureCards(supply);
		assertVictoryCards(supply, 8);
		assertCurses(supply,10);
	}

	@Test
	public void testInitThreePlayers() {
		Supply supply = new Supply();
		supply.init(3, new ArrayList<Card>());

		assertTreasureCards(supply);
		assertVictoryCards(supply, 12);
		assertCurses(supply,20);
	}

	@Test
	public void testInitFourPlayers() {
		Supply supply = new Supply();
		supply.init(4, new ArrayList<Card>());

		assertTreasureCards(supply);
		assertVictoryCards(supply, 12);
		assertCurses(supply,30);
	}
	
	@Test
	public void takeCard() {
		Supply supply = new Supply();
		supply.init(2, new ArrayList<Card>());
		
		try {
			supply.takeCard(Card.SMITHY);
			fail("Failed to detect absense of smithy in supply");
		}
		catch (RuntimeException expected) {
			// successfully detected absence
		}
		
		takeCardAndAssert(supply, 9, 0);
		takeCardAndAssert(supply, 8, 0);
		takeCardAndAssert(supply, 7, 0);
		takeCardAndAssert(supply, 6, 0);
		takeCardAndAssert(supply, 5, 0);
		takeCardAndAssert(supply, 4, 0);
		takeCardAndAssert(supply, 3, 0);
		takeCardAndAssert(supply, 2, 0);
		takeCardAndAssert(supply, 1, 0);
		takeCardAndAssert(supply, 0, 1);

		try {
			supply.takeCard(Card.CURSE);
			fail("Failed to detect zero left of GOLD in supply");
		}
		catch (RuntimeException expected) {
			// successfully detected absence
		}
}

	private void takeCardAndAssert(Supply supply, Integer expectedLeft, int stacksEmpty) {
		supply.takeCard(Card.CURSE);
		assertEquals(expectedLeft, supply.stacks.get(Card.CURSE));
		assertEquals(stacksEmpty, supply.emptyStacks);
	}

	private void assertVictoryCards(Supply supply, Integer expectedVictoryCards) {
		assertEquals(expectedVictoryCards, supply.stacks.get(Card.ESTATE));
		assertEquals(expectedVictoryCards, supply.stacks.get(Card.DUCHY));
		assertEquals(expectedVictoryCards, supply.stacks.get(Card.PROVINCE));
	}

	private void assertCurses(Supply supply, Integer expectedCurses) {
		assertEquals(expectedCurses, supply.stacks.get(Card.CURSE));
	}

	private void assertTreasureCards(Supply supply) {
		assertEquals((Integer)60, supply.stacks.get(Card.COPPER));
		assertEquals((Integer)40, supply.stacks.get(Card.SILVER));
		assertEquals((Integer)30, supply.stacks.get(Card.GOLD));
	}



//	public void testTakeCard() {
//	}	

}
