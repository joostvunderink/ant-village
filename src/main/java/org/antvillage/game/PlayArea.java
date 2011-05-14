package org.antvillage.game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.antvillage.cards.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a player's play area: the collection of all the
 * possible states that a card can be in, for a single player. What this means
 * is: Hand, Deck, Discard pile, Played cards area, Islanded cards ... and all other
 * areas that might be implemented by later Dominion expansions.
 * 
 * @author Verik 
 */
public class PlayArea {
	private static final Logger LOG = LoggerFactory.getLogger(PlayArea.class);
	
	public List<Card> hand = new LinkedList<Card>();
	public List<Card> drawPile = new LinkedList<Card>();
	public List<Card> discardPile = new LinkedList<Card>();
	public List<Card> playedPile = new LinkedList<Card>();
	
	public int bonus_vp;
	
	public Card drawCard() {
		if (drawPile.isEmpty()) {
			reshuffle();
		}
		if (drawPile.isEmpty()) {
			return null;
		}
		else {
			Card drawn = drawPile.remove(0);
			hand.add(drawn);
			LOG.debug("Drew {}", drawn);
			return drawn;
		}
	}
	
	public Card revealCard() {
		if (drawPile.isEmpty()) {
			reshuffle();
		}
		if (drawPile.isEmpty()) {
			return null;
		}
		return drawPile.get(0);
	}
	
	private void reshuffle() {
		assert(!drawPile.isEmpty());
		
		drawPile.addAll(discardPile);
		discardPile.clear();
		
		Collections.shuffle(drawPile);
	}
	
	public void play(Card card) {
		if ( hand.remove(card)) {
			playedPile.add(card);
		}
		else {
			throw new RuntimeException("Tried to play " + card + " which is not in hand ");
		}
	}
}
