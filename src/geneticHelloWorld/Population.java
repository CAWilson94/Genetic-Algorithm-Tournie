package geneticHelloWorld;

import java.util.ArrayList;
import java.util.List;

public class Population {

	public List<String> getRandPopulation() {
		int popSize = 10;
		List<String> population = new ArrayList<String>();
		// Not sure if you should really do this...
		Chromosome c = new Chromosome();
		for (int i = 0; i < popSize; i++) {
			population.add(c.randomChromosome());
		}
		return population;
	}

}