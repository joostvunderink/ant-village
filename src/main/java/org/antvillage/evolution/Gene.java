package org.antvillage.evolution;

import java.util.HashMap;
import java.util.Map;

import org.antvillage.game.Player;

public class Gene {
	private Map<String, Float> parameters = new HashMap<String, Float>();
	
	public final int GENE_PARAMETER_RANDOM_MAX = 100;

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
	
	public void setParameter(String name, float value) {
		if (parameters.get(name) == null) {
			throw new RuntimeException("Trying to set non-existing parameter " + name +
					"; try addParameter() first.");
		}
		
		parameters.put(name, value);
	}
	
	public float getParameter(String name) {
		if (parameters.get(name) == null) {
			throw new RuntimeException("Trying to get non-existing parameter " + name +
					"; try addParameter() first.");
		}
		
		return parameters.get(name);
	}
	
	public void randomizeParameters() {
		for (String name: parameters.keySet()) {
			float randomValue = (float)(GENE_PARAMETER_RANDOM_MAX * Math.random() + 1.0f);
			setParameter(name, randomValue);
		}
	}
	
	public void calculateBuyValues(CardValues currentValues, Player player) {
		throw new RuntimeException("You need to override calculateBuyValues for class " + this);
	}
}
