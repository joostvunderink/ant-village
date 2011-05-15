package org.antvillage.game;

import java.util.List;

/**
 * This class is the base class for all strategies.
 * 
 * @author Verik
 *
 */
public class Player {

	public PlayArea playArea;
	public GameTurn gameTurn;
	public Supply supply;
	public int playerCount;
	public List<Player> opponents;
	
	public void takeTurn() {
		
	}
}
