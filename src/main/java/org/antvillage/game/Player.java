package org.antvillage.game;

/**
 * This class is the base class for all strategies.
 * 
 * @author Verik
 *
 */
public abstract class Player {

	public PlayArea playArea;
	public GameTurn gameTurn;
	public Supply supply;
	public int playerCount;
	
	public void takeTurn() {
		
	}
}
