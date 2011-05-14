package org.antvillage.game;

import java.util.List;

import org.antvillage.cards.Card;
import org.antvillage.cards.Cards;

/**
 * The GameSetup class ties everything together and creates a Game object
 * that start() can be called on. It makes sure that the supply is created
 * and all players are given all necessary information.
 * 
 * @author Joost Vunderink
 * 
 */
public class GameSetup {
	public final int START_NUM_COPPER = 7;
	public final int START_NUM_ESTATE = 3;
	public final int STARTING_HAND_SIZE = 5;
	
	public Game createGame(List<Card> kingdomCards, List<Player> players) {
		Game game = new Game();
		Supply supply = new Supply();
		supply.init(players.size(), kingdomCards);
		game.supply = supply;
		
		game.players = players;
		
		GameTurn gameTurn = new GameTurn();
		gameTurn.supply = supply;
		game.gameTurn = gameTurn;
		
		for (Player player: players) {
			player.gameTurn = gameTurn;
			player.supply = supply;
			player.playerCount = players.size();
			
			PlayArea playArea = new PlayArea();
			player.playArea = playArea;
			
			giveStartingCards(player);
		}
		
		return game;
	}
	
	public void giveStartingCards(Player player) {
		PlayArea playArea = player.playArea;
		
		for (int i=0; i < START_NUM_COPPER; i++) {
			playArea.discardPile.add(Cards.COPPER);
		}
		
		for (int i=0; i < START_NUM_ESTATE; i++) {
			playArea.discardPile.add(Cards.ESTATE);
		}
		
		for (int i=0; i < STARTING_HAND_SIZE; i++) {
			playArea.drawCard();
		}
	}
}

