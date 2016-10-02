package geneticHelloWorld;

import java.util.ArrayList;
import java.util.List;

public class Population {

	/**
	 * Current use population maker
	 * 
	 * @param popSize
	 * @return random population
	 */
	public List<Chromosome> getRandPopulationChromo(int popSize) {
		if ((popSize % 2) != 0) {
			popSize += 1;
		}
		List<Chromosome> population = new ArrayList<Chromosome>();
		for (int i = 0; i < popSize; i++) {
			Chromosome c = new Chromosome();
			population.add(c.randomChromoFromNum());
		}
		return population;
	}
}
