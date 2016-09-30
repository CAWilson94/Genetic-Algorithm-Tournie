package geneticHelloWorld;

import java.util.ArrayList;
import java.util.List;

/*
 * Basic idea is the source, we can view as the DNA of the organism It
 * will randomly mutate some of the DNA then judge the new mutated
 * strings fitness.
 * 
 * So, in this instance, how do we determine fitness? Take the value of
 * each character in the String and see how much it differs from the
 * same character in the target string.
 * 
 * So this is the distance between two characters, we then add all those
 * differences which leads to a single value which is the fitness
 * function.
 * 
 * A fitness of 0 is perfect while a fitness of 1 means one of the
 * characters is off by 1 eh hello and hellp both have a fitness of one
 * - the higher the fitness number the less the fit actually is.
 */

public class Demo {

	public static void main(String[] args) {

		String target = "Hello, World!";
		Chromosome targ = new Chromosome(target, 13);
		Population p = new Population();
		// Generate an initial population of chromosomes
		List<Chromosome> population = p.getRandPopulationChromo(4);
		// Get the fitness of each
		GA ga = new GA();

		while (true) {
			// Get fitness value for each chromosome in population
			for (Chromosome chrom : population) {
				chrom.setFitness(ga.individualFitness(chrom.getChromoStr(), targ.getChromoStr()));
			}
			// Sort population by fitness
			ga.sortbyFitness(population);
			System.out.println("all the population: sorted by fitness");
			for (Chromosome cp : population) {
				System.out.println(cp.getFitness() + " : " + cp.getChromoStr());
			}
			System.out.println("\n" + "fight to death" + "\n");
			ga.fightToTheDeath(population);
			List<Chromosome> boop = ga.fightToTheDeath(population);
			for (Chromosome c : boop) {
				System.out.println(c.getChromoStr());
			}
			population = boop;
			if (population.contains(targ.getChromoStr())) {
				System.out.println("Yassss");
				break;
			}
		}

	}

}
