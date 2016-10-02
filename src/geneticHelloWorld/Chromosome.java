package geneticHelloWorld;

import java.util.Random;

public class Chromosome {

	public int fitness = 0;
	public String chromoStr = null;

	public Chromosome(String chromoStr, int fitness) {
		this.fitness = fitness; // Defaults at 0
		this.chromoStr = chromoStr;
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
		this.fitness = GA.individualFitness(this.getChromoStr(), Constants.TARGET);
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
		int boop = r.nextInt(126 - 32 + 1) + 32;
		randGene = String.valueOf(Character.toChars(boop));
		return randGene;
	}
}
