package geneticHelloWorld;

import java.util.Random;

public class Chromosome {

	GA ga = new GA();
	final String target = "Hello, World!";
	public int fitness = 0;
	public String chromoStr = null;
	public String chromoString = null;

	public Chromosome(String chromoString, int fitness) {
		this.fitness = fitness; // Defaults at 0
		this.chromoStr = chromoString;
	}

	public Chromosome() {
		// Basically just want to be able to initalize in whatever way I want..
	}

	/**
	 * Sets String associated with chromosome
	 * 
	 * @param String
	 *            chromoStr
	 */
	public void setChromoStr(String chromoStr) {
		this.chromoStr = chromoStr;
	}

	/**
	 * Returns String associated with chromosome
	 * 
	 * @return String chromoStr
	 */
	public String getChromoStr() {
		return chromoStr;

	}

	/**
	 * Returns fitness associated with chromosome
	 * 
	 * @return int fitness
	 */
	public int getFitness() {
		return fitness;
	}

	/**
	 * Sets fitness associated with chromosome
	 * 
	 * @param fitness
	 * 
	 * @param int
	 *            fitness
	 */
	public void setFitness() {
		GA ga = new GA();
		this.fitness = ga.individualFitness(this.getChromoStr(), Constants.TARGET);
	}

	/**
	 * Returns the string of a chromosome directly: might be useless...
	 * 
	 * @return chromosome String
	 */
	public String randomChromosome() {
		Random rnd = new Random();
		String chromosome = null;
		StringBuilder sb = new StringBuilder(13);

		for (int i = 0; i < Constants.TARGET.length(); i++) {
			sb.append(Constants.AB.charAt(rnd.nextInt(Constants.AB.length())));
		}
		chromosome = sb.toString();
		return chromosome;
	}

	/**
	 * Returns the chromosome objects generated randomly
	 * 
	 * @return Chromosome object with initialized chromosome string
	 */
	public Chromosome randomChromo() {
		Random rnd = new Random();
		Chromosome chromosome = new Chromosome();
		StringBuilder sb = new StringBuilder(13);

		for (int i = 0; i < Constants.TARGET.length(); i++) {
			sb.append(Constants.AB.charAt(rnd.nextInt(Constants.AB.length())));
		}
		chromosome.setChromoStr(sb.toString());
		chromosome.setFitness();
		return chromosome;
	}

	/**
	 * Generate random chromosome String from random numbers
	 * 
	 * @return Chromosome chromosome
	 */
	public Chromosome randomChromoFromNum() {
		StringBuilder sb = new StringBuilder(13);
		for (int i = 0; i < Constants.TARGET.length(); i++) {
			sb.append(genRandGene());
		}
		this.setChromoStr(sb.toString());
		this.setFitness();
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public String genRandGene() {
		Random r = new Random();
		String randGene = null;
		int boop = r.nextInt(31 - 0 + 1) + 31;
		randGene = String.valueOf(Character.toChars(boop));
		return randGene;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Chromosome c = new Chromosome();
		System.out.println(c.randomChromoFromNum().getChromoStr());
		System.out.println(c.randomChromo().getChromoStr());
	}
}
