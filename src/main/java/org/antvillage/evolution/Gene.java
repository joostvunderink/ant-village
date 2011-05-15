package org.antvillage.evolution;

import java.util.HashMap;
import java.util.Map;

import org.antvillage.game.Player;

public abstract class Gene {
	private Map<String, Float> parameters = new HashMap<String, Float>();

	/*
	 * TODO: Maybe this should be private; only subclasses of Combo should be calling
	 * addParameter, in their constructors.
	 */
	public void addParameter(String name) {
		if (parameters.get(name) != null) {
			throw new RuntimeException("Trying to add new parameter " + name + " which already exists.");
		}
		parameters.put(name, (float)0);
	}
	
	public void setParameter(String name, Float value) {
		if (parameters.get(name) == null) {
			throw new RuntimeException("Trying to set non-existing parameter " + name +
					"; try addParameter() first.");
		}
		
		parameters.put(name, value);
	}
	
	public Float getParameter(String name) {
		if (parameters.get(name) == null) {
			throw new RuntimeException("Trying to get non-existing parameter " + name +
					"; try addParameter() first.");
		}
		
		return parameters.get(name);
	}
	
	public abstract void calculateBuyValues(CardValues currentValues, Player player);
}
