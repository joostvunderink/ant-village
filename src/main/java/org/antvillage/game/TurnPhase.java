package org.antvillage.game;

public enum TurnPhase {
    ACTION(1),
        MONEY(2),
        BUY(3),
        CLEANUP(4),
        DRAW(5),;
    
    public int order;

	private TurnPhase(int order) {
		this.order = order;
	}
    
}
