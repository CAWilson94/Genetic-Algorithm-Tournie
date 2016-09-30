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

	public static final String target = "hello, world!";
	public static final int crossoverRate = 7; // Trust the crossover rate

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
	public Chromosome mutate(String individual) {
		char[] indi = individual.toCharArray();
		Random r = new Random();

		int randChar = r.nextInt(indi.length);

		indi[randChar] += r.nextInt((1 - 0) + 1) + 0;
		String indiStr = String.valueOf(indi);
		Chromosome chromo = new Chromosome(indiStr, 0);

		return chromo;
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
			String before = populationSpawn.get(randChar).getChromoStr();
			System.out.println("some shit here");
			Chromosome before1 = new Chromosome(before, 0);
			System.out.println("before mutate: " + before1.getChromoStr());
			Chromosome boop = mutate(populationSpawn.get(randChar).getChromoStr());
			populationSpawn.set(randChar, boop);
			System.out.println("After mutate : " + boop.getChromoStr());
		}

		System.out.println("new mutates population");
		int i = 0;
		for (Chromosome o : populationSpawn) {
			System.out.println(i + " : " + o.getChromoStr());
			i++;
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

	/**
	 * Tournie selection
	 */
	public List<Chromosome> fightToTheDeath(List<Chromosome> population) {
		List<Chromosome> newGen = new ArrayList<Chromosome>();
		// for, half the number of the population
		Random rand = new Random();

		while (newGen.size() != population.size()) {
			Chromosome p1 = population.get(rand.nextInt(population.size()));
			Chromosome p2 = population.get(rand.nextInt(population.size()));
			System.out.println(p1.getChromoStr());
			System.out.println(p2.getChromoStr());
			int i = rand.nextInt(10 - 0 + 1);
			if (i < crossoverRate) {
				System.out.println("i less than crossover: " + i);
				List<Chromosome> newG = crossover(p1, p2, 2);
				newGen.addAll(newG);
			}
		}

		return newGen;
	}

	/**
	 * Sorts fitness lowest to highest
	 * 
	 * @param chromosomes
	 * @return
	 */
	public void sortbyFitness(List<Chromosome> chromosomes) {

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
	public void keepFittest(List<Chromosome> population) {

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
	public List<Chromosome> iscolateWeakest(List<Chromosome> population) {
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
			List<Chromosome> tempChildren = crossover(parent1, parent2, 2);
			children.add(tempChildren.get(0));
		}
		return children;
	}

	public static void main(String[] args) {
		GA ga = new GA();
		List<Chromosome> population = new ArrayList<Chromosome>();
		population.add(new Chromosome("bob", 0));
		population.add(new Chromosome("boop", 1));
		population.add(new Chromosome("yermaw", 3));
		population.add(new Chromosome("pebble", 1));

		List<Chromosome> boop = ga.fightToTheDeath(population);
		for (Chromosome c : boop) {
			System.out.println(c.getChromoStr());
		}
	}
}
