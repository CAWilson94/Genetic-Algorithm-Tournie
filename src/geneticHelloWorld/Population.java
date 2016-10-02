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
			population.add(c.randomChromoFromNum().getChromoStr());
		}
		return population;
	}

	public List<String> getRandPopulation(int popSize) {
		List<String> population = new ArrayList<String>();
		// Not sure if you should really do this...
		for (int i = 0; i < popSize; i++) {
			Chromosome c = new Chromosome();
			population.add(c.randomChromo().getChromoStr());
		}
		return population;
	}

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
		// Not sure if you should really do this...
		for (int i = 0; i < popSize; i++) {
			Chromosome c = new Chromosome();
			population.add(c.randomChromoFromNum());
		}
		return population;
	}

	public static void main(String[] args) {
		Population p = new Population();
		for (String ph : p.getRandPopulation()) {
			System.out.println(ph);
		}
	}

}
