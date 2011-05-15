package org.antvillage.game;

import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.strategy.BmPocStrategy;

/**
 * This class provides reusable methods for seting up test data for game setup.
 *
 * @author Verik
 */
public class GameSetupHelper {
	
	public static List<Player> createTwoPlayerBmPoc() {
		Player p1 = new BmPocStrategy();
		p1.name="BmPocStrategy 1";
		Player p2 = new BmPocStrategy();
		p2.name="BmPocStrategy 2";
		List<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		return players;
	}

	public static List<Card> createSimpleKingdom(Card card) {
		List<Card> kingdomCards = new LinkedList<Card>();
		kingdomCards.add(card);
		return kingdomCards;
	}

}
