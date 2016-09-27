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

public class GA {

	final String target = "hello, world!";

	/**
	 * Evaluates current fitness of the generation
	 * 
	 * @param target
	 *            the target to be evaluated against
	 * @param population
	 *            the population of individuals
	 * @return overall fitness of current population
	 */
	public int populationFitness(String target, List<String> population) {
		int populationFitness = 0;
		for (String p : population) {
			populationFitness += individualFitness(p, target);
		}
		return populationFitness;
	}

	/**
	 * Create list of candidates to be evolved
	 * 
	 * @param population
	 * @return List<Candidate> chromosomes to be evolved
	 */
	public List<Candidate> candidateCreation(List<String> population) {
		List<Candidate> candidates = new ArrayList<Candidate>();
		for (String p : population) {
			Candidate can = new Candidate(individualFitness(p, target), p);
			candidates.add(can);
		}
		return candidates;
	}

	/**
	 * Calculate fitness of an individual chromosome
	 * 
	 * @param individual
	 * @param target
	 * @return
	 */
	public int individualFitness(String individual, String target) {
		int individualFitness = 0;
		FitnessFunctions fit = new FitnessFunctions();
		individualFitness = 13 - fit.fitnessHamming(individual, target);
		return individualFitness;
	}

	/**
	 * Show every chromosome in a population
	 * 
	 * @param population
	 */
	public void showPopulation(List<String> population) {
		for (String P : population) {
			System.out.println(P);
		}
	}

	/**
	 * Mutate a random gene in a chromosome
	 * 
	 * @param individual
	 * @return
	 */
	public String mutate(String individual) {
		char[] indi = individual.toCharArray();
		Random r = new Random();

		int randChar = r.nextInt(indi.length);

		indi[randChar] += r.nextInt((1 - 0) + 1) + 0;
		String indiStr = String.valueOf(indi);

		return indiStr;
	}

	/**
	 * Mutate one random chromosome in the population
	 * 
	 * @param mutationChance
	 * @param populationSpawn
	 */
	public void mutateRandString(int mutationChance, List<Chromosome> populationSpawn) {
		for (int i = 0; i < mutationChance; i++) {
			Random r = new Random();
			int randChar = r.nextInt(populationSpawn.size());
			String boop = populationSpawn.get(randChar).getChromoStr();
			System.out.println("before mutate: " + boop);
			boop = mutate(boop);
			System.out.println("After mutate : " + boop);
		}
	}

	/**
	 * 
	 * @return int survivalChance
	 */
	public double calcSurvivalChance(int fitnessValue, int totalFitness) {
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
	public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, int crossoverPoint) {
		List<Chromosome> newChromosomes = new ArrayList<Chromosome>();
		String newChromoStr1 = null;
		String newChromoStr2 = null;
		// Create substrings for crossover
		String indi1Part1 = parent1.getChromoStr().substring(0, crossoverPoint);
		String indi1Part2 = parent1.getChromoStr().substring(crossoverPoint, parent1.getChromoStr().length());
		String indi2Part1 = parent2.getChromoStr().substring(0, crossoverPoint);
		String indi2Part2 = parent2.getChromoStr().substring(crossoverPoint, parent2.getChromoStr().length());
		// New children strings for new generation
		newChromoStr1 = indi1Part1 + indi2Part2;
		newChromoStr2 = indi2Part1 + indi1Part2;
		Chromosome childOne = new Chromosome(newChromoStr1, 0);
		Chromosome childTwo = new Chromosome(newChromoStr2, 0);
		newChromosomes.add(childOne);
		newChromosomes.add(childTwo);
		return newChromosomes;
	}

	public List<Chromosome> sortbyFitness(List<Chromosome> chromosomes) {

		Comparator<Chromosome> comparator = new Comparator<Chromosome>() {

			@Override
			public int compare(Chromosome can1, Chromosome can2) {
				return can1.getFitness() - can2.getFitness();
			}
		};
		Collections.sort(chromosomes, comparator);
		return chromosomes;
	}

	public List<Chromosome> keepFittest(List<Chromosome> population) {
		// Add fittest half of array to new population
		List<Chromosome> populationSpawn = new ArrayList<Chromosome>();
		for (int i = population.size() - 1; i >= (population.size() / 2); i--) {
			populationSpawn.add(population.get(i));
		}
		return populationSpawn;
	}

	public List<Chromosome> iscolateWeakest(List<Chromosome> population) {
		for (int i = 0; i < population.size() / 2; i++) {
			population.remove(i);
		}
		return population;
	}

	public List<Chromosome> children(List<Chromosome> population) {
		List<Chromosome> children = new ArrayList<Chromosome>();
		// Want to look at second half of arraylist here
		population = iscolateWeakest(population);
		while (children.size() < population.size()) {
			Random rand = new Random();
			int randomNum = rand.nextInt(population.size());
			int randomNum1 = rand.nextInt(population.size());
			Chromosome parent1 = population.get(randomNum);
			Chromosome parent2 = population.get(randomNum1);
			List<Chromosome> tempChildren = crossover(parent1, parent2, 4);
			children.add(tempChildren.get(0));
			children.add(tempChildren.get(1));
		}
		return children;
	}

	public static void main(String[] args) {
		/*
		 * Population p = new Population();
		 * 
		 * int populationSize = 20; // Step one: population creation
		 * List<String> pop = p.getRandPopulation(populationSize); GA ga = new
		 * GA(); List<Candidate> can = ga.candidateCreation(pop);
		 * 
		 * while (true) { // Evaluate the population for (Candidate boop :
		 * ga.candidateCreation(pop)) { System.out.println(boop.getChromosome()
		 * + " : " + boop.getfitness()); }
		 * 
		 * System.out.println("new list based on fitness"); // Order the
		 * candidates by fitness highest to lowest ga.sortbyFitness(can);
		 * 
		 * // Add fittest half of array to new population List<String>
		 * populationNew = new ArrayList<String>(); int half = can.size() / 2;
		 * for (int i = can.size() - 1; i >= half; i--) {
		 * populationNew.add(can.get(i).getChromosome()); } // Now need n =
		 * population size/2 parents for new gene pool int canSize = can.size()
		 * / 2; for (int i = 0; i < canSize; i++) { Random r = new Random(); int
		 * rc1 = r.nextInt(canSize); int rc2 = r.nextInt(canSize); String rent1
		 * = can.get(rc1).getChromosome(); String rent2 =
		 * can.get(rc2).getChromosome(); List<String> popCan =
		 * ga.crossover(rent1, rent2, 4); populationNew.add(popCan.get(0));
		 * populationNew.add(popCan.get(1)); } // Currently chooses random
		 * population from ALL previous population. // Not // the lower half of
		 * the candidates pop = populationNew; }
		 */

	}
}
