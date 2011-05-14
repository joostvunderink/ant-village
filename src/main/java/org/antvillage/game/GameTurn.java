package org.antvillage.game;

/**
 * This class provides the functionality for operating a game turn from the perspective of a player.
 *
 * @author Verik
 */
public class GameTurn {

	private Supply supply;
	private PlayArea activePlayArea;
	
	public void takeTurn(Player player) {
		activePlayArea = player.playArea;
		
		player.takeTurn();
		
		// cleanup and draw cards
	}
	
	
	
	
}
