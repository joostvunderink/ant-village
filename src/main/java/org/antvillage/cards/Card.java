package org.antvillage.cards;

public abstract class Card {
	public int vp = 0;
    public int cost = 0;
    public int value = 0;
    
    public int buys = 0;
    public int draws = 0;
    public int actions = 0;
    
    public boolean isAction() {
    	return false;
    }
    
    public boolean isTreasure() {
    	return false;
    }
    
    public boolean isVictory() {
    	return false;
    }
    
    public int getVictoryPoints() {
    	return vp;
    }
    
    public int getCost() {
    	return cost;
    }
    
    public int getMoneyValue() {
    	return value;
    }
    
    public int getExtraBuys() {
    	return buys;
    }
    
    public int getExtraDraws() {
    	return draws;
    }
    
    public int getExtraActions() {
    	return actions;
    }
}
