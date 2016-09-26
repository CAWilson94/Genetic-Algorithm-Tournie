package geneticHelloWorld;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * @param population
	 */
	public String mutateRandString(int mutationChance, List<String> population) {
		Random r = new Random();
		int randChar = r.nextInt(population.size());
		String boop = population.get(randChar);
		String mut = mutate(boop);
		return mut;
	}

	/**
	 * 
	 * @return int survivalChance
	 */
	public double calcSurvivalChance(int fitnessValue, int totalFitness) {
		double survival = 0;
		System.out.println("indi fitness: " + fitnessValue + ":" +  "total: " + totalFitness);
		survival = Math.round((fitnessValue / (double) totalFitness) * 100);
		System.out.println("thus, survival is: " + survival);
		return survival;
	}

	/**
	 * Crossover two given chromosomes
	 * 
	 * @param individual1
	 * @param individual2
	 * @param crossoverPoint
	 * @return List<String> new chromosomes
	 */
	public List<String> crossover(String individual1, String individual2, int crossoverPoint) {
		List<String> newChromosomes = new ArrayList<String>();
		String newChromo1 = null;
		String newChromo2 = null;

		String indi1Part1 = individual1.substring(0, crossoverPoint);
		String indi1Part2 = individual1.substring(crossoverPoint, individual1.length());
		String indi2Part1 = individual2.substring(0, crossoverPoint);
		String indi2Part2 = individual2.substring(crossoverPoint, individual2.length());

		newChromo1 = indi1Part1 + indi2Part2;
		newChromo2 = indi2Part1 + indi1Part2;

		newChromosomes.addAll(Arrays.asList(newChromo1, newChromo2));

		return newChromosomes;
	}

	public static void main(String[] args) {
		String target = "hello, world!";

		// Step 1: create random population
		Population p = new Population();
		GA ga = new GA();
		List<String> population = p.getRandPopulation();
		// Show whats in this random population
		ga.showPopulation(population);

		// Step 2: evaluate the fitness of each population

		int popFitness = ga.populationFitness(target, population);
		System.out.println("population fitness: " + popFitness);

		for (String l : population) {
			int fitness = ga.individualFitness(l, target);
			ga.calcSurvivalChance(fitness, popFitness);
		}
	}
}
