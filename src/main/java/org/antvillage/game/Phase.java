package org.antvillage.game;

public enum Phase {
	ACTION, MONEY, BUY, CLEANUP, DRAW;

	static {
		ACTION.next = MONEY;
		MONEY.next = BUY;
		BUY.next = CLEANUP;
		CLEANUP.next = DRAW;
	}

	public Phase next;

}
