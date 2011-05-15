package org.antvillage.simulator;

import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.GameSetupHelper;
import org.antvillage.game.Player;
import org.junit.Test;

public class RunTest {

	@Test
	public void testStart() {
		List<Player> players = GameSetupHelper.createTwoPlayerBmPoc();
		List<Card> kingdomCards = GameSetupHelper.createSimpleKingdom(Cards.VILLAGE);

		Run run = new Run();
		run.games = 11;
		run.kingdomCards = kingdomCards;
		run.players = players;
		
		run.start();
	}

}
