package org.antvillage.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class generates random names.
 *
 * @author Verik
 */
public class NameGenerator {
	
	private String FILE_LOCATION = "org/antvillage/simulator/names.txt";
	
	public List<String> names = new ArrayList<String>(5000);
	
	private Random random = new Random();

	public NameGenerator() {
		super();
	}
	
	public String getName() {
		int nameIndex = random.nextInt(names.size());
		return names.get(nameIndex);
	}
	
	public void init() {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(FILE_LOCATION);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			readFile(reader);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void readFile(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		while (line != null) {
			String name = line.substring(6);
			names.add(name);
			line = reader.readLine();
		}
	}

}
