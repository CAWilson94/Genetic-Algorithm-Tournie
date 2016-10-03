package geneticHelloWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.w3c.dom.events.MutationEvent;

/**
 * 
 * @author Charlotte Wilson
 * 
 *         1: initialize - create a population of n elements 2: Selection -
 *         evaluate the population i.e. fitness function 3: reproduction (repeat
 *         n times) - (a) Pick two parents with probability according to fitness
 *         (b) Crossover - create a child by combining two parents (c) Mutation
 *         - mutate childs DNA based on given probability (d) Add new child to a
 *         NEW population
 *
 */

public final class GA {

	/**
	 * Evaluates current fitness of the generation
	 * 
	 * @param target
	 *            the target to be evaluated against
	 * @param population
	 *            the population of individuals
	 * @return overall fitness of current population
	 */
	public static int populationFitness(String target, List<String> population) {
		int populationFitness = 0;
		for (String p : population) {
			populationFitness += individualFitness(p);
		}
		return populationFitness;
	}

	/**
	 * Calculate fitness of an individual chromosome
	 * 
	 * @param individual
	 * @param target
	 * @return
	 */
	public static int individualFitness(String individual) {
		int individualFitness = 0;
		FitnessFunctions fit = new FitnessFunctions();
		individualFitness = fit.fitnessHamming(individual);
		return individualFitness;
	}

	/**
	 * Show every chromosome in a population
	 * 
	 * @param population
	 */
	public static void showPopulation(List<Chromosome> population) {
		for (Chromosome P : population) {
			System.out.println(P.getChromoStr() + " : " + P.getFitness());
		}
	}

	/**
	 * Mutate a random gene in a chromosome
	 * 
	 * @param individual
	 */
	public static void mutate(Chromosome individual) {
		char[] indi = individual.getChromoStr().toCharArray();
		Random r = new Random();

		int randChar = r.nextInt(indi.length);
		Chromosome c = new Chromosome();

		indi[randChar] = c.genRandGene().charAt(0);
		if (indi[randChar] == 127) {
			indi[randChar] = 32;
		}
		String indiStr = String.valueOf(indi);
		individual.setChromoStr(indiStr);
		individual.setFitness();
	}

	/**
	 * 
	 * @return int survivalChance
	 */
	public static double calcSurvivalChance(int fitnessValue, int totalFitness) {
		double survival = 0;
		System.out.println("indi fitness: " + fitnessValue + ":" + "total: " + totalFitness);
		survival = Math.round((fitnessValue / (double) totalFitness) * 100);
		System.out.println("thus, survival is: " + survival);
		return survival;
	}

	/**
	 * Crossover two given chromosomes
	 * 
	 * @param parent1.getChromoStr()
	 * @param parent2
	 * @param crossoverPoint
	 * @return List<String> new chromosomes
	 */
	public static List<Chromosome> crossover(Chromosome parent1, Chromosome parent2) {
		List<Chromosome> newChromosomes = new ArrayList<Chromosome>();
		String newChromoStr1 = null;
		String newChromoStr2 = null;
		// Create substrings for crossover
		String indi1Part1 = parent1.getChromoStr().substring(0, Constants.crossoverPoint);
		String indi1Part2 = parent1.getChromoStr().substring(Constants.crossoverPoint, parent1.getChromoStr().length());
		String indi2Part1 = parent2.getChromoStr().substring(0, Constants.crossoverPoint);
		String indi2Part2 = parent2.getChromoStr().substring(Constants.crossoverPoint, parent2.getChromoStr().length());
		// New children strings for new generation
		newChromoStr1 = indi1Part1 + indi2Part2;
		newChromoStr2 = indi2Part1 + indi1Part2;
		Chromosome childOne = new Chromosome(newChromoStr1, 0);
		childOne.setFitness();
		Chromosome childTwo = new Chromosome(newChromoStr2, 0);
		childTwo.setFitness();
		Random r = new Random();
		int i = r.nextInt(10 - 0 + 1);
		if (i < Constants.MUTATION_CHANCE) {
			mutate(childOne);
			mutate(childTwo);
		}
		newChromosomes.add(childOne);
		newChromosomes.add(childTwo);
		return newChromosomes;
	}

	/**
	 * Select Parents for tournament method
	 * 
	 * @param population
	 * @return Chromosome parents
	 */
	private static List<Chromosome> randomParents(List<Chromosome> population) {
		List<Chromosome> tempArray = new ArrayList<Chromosome>();
		List<Chromosome> parents = new ArrayList<Chromosome>();
		// two random members of the population
		for (int i = 0; i < 2; i++) {
			Random rand = new Random();
			int one = rand.nextInt(population.size());
			int two = rand.nextInt(population.size());
			tempArray.add(population.get(one));
			tempArray.add(population.get(two));
			sortbyFitness(tempArray);
			parents.add(tempArray.get(0));
			tempArray.clear();
		}
		return parents;
	}

	/**
	 * Tournament to generate new generation
	 * 
	 * @param population
	 * @param crossoverPoint
	 * @return
	 */
	public static List<Chromosome> tournie(List<Chromosome> population) {
		List<Chromosome> newGen = new ArrayList<Chromosome>();
		List<Chromosome> crossed = new ArrayList<Chromosome>();
		// Until new generation has same number of individuals as population
		while (crossed.size() != population.size()) {
			// Select random parents: i.e. the best fittest
			newGen = randomParents(population);
			// Check against crossover rate, before crossover
			Random r = new Random();
			int i = r.nextInt(10 - 0 + 1);
			if (i < Constants.CROSSOVER_RATE) {
				List<Chromosome> temp = crossover(newGen.get(0), newGen.get(1));
				crossed.addAll(temp);
				newGen.clear();
			}
		}
		return crossed;
	}

	public List<Chromosome> elitist() {
		return null;
	}

	/**
	 * Sorts fitness lowest to highest
	 * 
	 * @param chromosomes
	 * @return
	 */
	public static void sortbyFitness(List<Chromosome> chromosomes) {

		Comparator<Chromosome> comparator = new Comparator<Chromosome>() {
			@Override
			public int compare(Chromosome can1, Chromosome can2) {
				return can1.getFitness() - can2.getFitness();
			}
		};
		Collections.sort(chromosomes, comparator);
	}

	/**
	 * Keeps the fittest half of the population i.e. the end half of the
	 * arraylist
	 * 
	 * @param population
	 * @return
	 */
	public static void keepFittest(List<Chromosome> population) {

		int half = population.size() / 2;
		for (int i = 0; i < half; i++) {
			System.out.println(population.get(i).getChromoStr());
			population.remove(i);
		}
	}

	/**
	 * Take out the weakest half of the population i.e. lower half of arraylist
	 * 
	 * @param population
	 * @return
	 */
	public static List<Chromosome> iscolateWeakest(List<Chromosome> population) {
		int half = population.size() / 2 - 1;
		int end = population.size() - 1;

		for (int i = end; i > half; i--) {
			population.remove(i);
		}
		return population;
	}

	/**
	 * Return children, i.e. crossed over lower half of population (weakest)
	 * 
	 * @param population
	 * @return
	 */
	public static List<Chromosome> children(List<Chromosome> population) {
		List<Chromosome> children = new ArrayList<Chromosome>();
		// Want to look at second half of arraylist here
		population = iscolateWeakest(population);
		while (children.size() < population.size()) {
			Random rand = new Random();
			int randomNum = rand.nextInt(population.size());
			int randomNum1 = rand.nextInt(population.size());
			Chromosome parent1 = population.get(randomNum);
			Chromosome parent2 = population.get(randomNum1);
			List<Chromosome> tempChildren = crossover(parent1, parent2);
			children.add(tempChildren.get(0));
		}
		return children;
	}

	public static Boolean levelOff(List<Chromosome> best) {
		Boolean level = false;
		int bestNum = 0;
		for (int i = 1; i < best.size(); i++) {
			FitnessFunctions f = new FitnessFunctions();
			int one = f.fitnessFunction(best.get(0).getChromoStr());
			int two = f.fitnessFunction(best.get(i).getChromoStr());
			int temp = Math.abs(one - two);
			if (temp == 0) {
				bestNum = +1;
			}
		}
		if (bestNum == best.size()) {
			level = true;
			return level;
		}
		return level;
	}

	/**
	 * Genetic algorithm: evolves a string until target found
	 */
	public static int GAlgorithm() {
		// Initalise a population
		Population pop = new Population();
		List<Chromosome> population = pop.getRandPopulationChromo();
		List<Chromosome> best = new ArrayList<Chromosome>();

		int gen = 0;
		while (true) {
			gen++;
			// Sort the fitness of all
			sortbyFitness(population);
			// Keep best recent at size 5
			if (best.size() > 5) {
				best.remove(best.get(best.size() - 1));
			}
			// Add current to best array
			best.add(population.get(0));
			// Check if you have found hello world yet
			if (population.get(0)
					.getFitness() == 0 /* || gen == Constants.MAX_GENERATION */) {
				System.out.println("found something: " + population.get(0).getChromoStr());
				return gen;
			}

			System.out.println("generation: " + gen + " best: " + population.get(0).getChromoStr());
			// Assuming you havent found it: reproduction
			population = tournie(population);
		}
	}

	public static int random() {
		Population pop = new Population();
		List<Chromosome> beep = pop.getRandPopulationChromo();
		int gen = 0;
		while (true) {
			gen++;
			sortbyFitness(beep);
			System.out.println("generation: " + gen + " best: " + beep.get(0).getChromoStr() + " Fitness: "
					+ beep.get(0).getFitness());

			if (beep.get(0).getFitness() == 0 || gen == Constants.MAX_GENERATION) {
				System.out.println("found something: " + beep.get(0).getChromoStr());
				return gen;
			}

			beep = pop.getRandPopulationChromo();
		}
	}
	
	public static void hillClimbing(){
		
	}

	public static void main(String[] args) {
		System.out.println(Constants.CHARLOTTE.length());
		GA.GAlgorithm();
	}
}
