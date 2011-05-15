package org.antvillage.cards.base;

import org.antvillage.cards.ActionCard;
import org.antvillage.cards.Cards;
import org.antvillage.game.Player;

public class WitchCard extends ActionCard {
    public void playAction(Player player) {
    	for (Player opponent: player.opponents) {
    		if (player.supply.countCard(Cards.CURSE) > 0) {
    			player.supply.takeCard(Cards.CURSE);
    			/*
    			 * TODO: This should not be a hardcoded gain to the discard pile, but a
    			 * "You just gained this card" event that goes to the opponent. This is
    			 * necessary to make sure that cards like Watchtower can be used.
    			 */
    			opponent.playArea.discardPile.add(Cards.CURSE);
    		}
    	}
    }

}
