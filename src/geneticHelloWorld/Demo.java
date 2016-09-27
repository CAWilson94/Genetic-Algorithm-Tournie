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
		System.out.println(target);
		Population p = new Population();
		// Generate an initial population of chromosomes
		List<Chromosome> population = p.getRandPopulationChromo(20);
		// Get the fitness of each
		GA ga = new GA();
		// Get fitness value for each chromosome in population
		for (Chromosome chrom : population) {
			chrom.setFitness(ga.individualFitness(chrom.getChromoStr(), target));
		}
		// Sort population by fitness
		ga.sortbyFitness(population);
		// Keep the first half of the population
		List<Chromosome> populationSpawn = ga.keepFittest(population);
		// Now have crossover and mutation of second half
		List<Chromosome> children = ga.children(population);
		populationSpawn.addAll(children);
		System.out.println("aw the kids");
		for (Chromosome yer : populationSpawn) {
			System.out.println(yer.getChromoStr());
		}
		double mutationChance = Math.round((1 / (double) populationSpawn.size()) * 100);
		ga.mutateRandString((int) mutationChance, populationSpawn);
		System.out.println("with mutations");
		for (Chromosome yer : populationSpawn) {
			System.out.println(yer.getChromoStr());
		}
		//TODO: two children appearing twice
	}

}
