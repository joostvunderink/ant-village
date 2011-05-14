package org.antvillage.game;

import java.util.HashMap;
import java.util.Map;

public enum TurnPhase {
    ACTION(1),
        MONEY(2),
        BUY(3),
        CLEANUP(4),
        DRAW(5),;
    
    private static Map<Integer, TurnPhase> byOrder = new HashMap<Integer, TurnPhase>();

    static {
    	for (TurnPhase phase: TurnPhase.values()) {
    		TurnPhase.byOrder.put(phase.order, phase);
    	}
    }
    
    public int order;
    
	private TurnPhase(int order) {
		this.order = order;
	}
	
	public TurnPhase next() {
		int nextOrder = order + 1;
		return byOrder.get(nextOrder);
	}
    
}
