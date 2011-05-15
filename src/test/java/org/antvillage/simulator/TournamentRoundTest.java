package org.antvillage.simulator;

import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;
import org.antvillage.game.GameSetupHelper;
import org.antvillage.game.Player;
import org.junit.Test;

public class TournamentRoundTest {

	@Test
	public void testStart() {
		List<Player> players = GameSetupHelper.createTwoPlayerBmPoc();
		List<Card> kingdomCards = GameSetupHelper.createSimpleKingdom(Cards.VILLAGE);

		TournamentRound round = new TournamentRound();
		round.kingdomCards = kingdomCards;
		round.participants = players;
		round.run.games = 11;
		
		round.start();
	}

}
