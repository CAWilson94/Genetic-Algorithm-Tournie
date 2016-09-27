package geneticHelloWorld;

import java.util.Random;

public class Chromosome {

	final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,./<>?!£$%^&*()_+-=[];#{}:@~'";
	final String target = "Hello, World!";
	public int fitness = 0;
	public String chromoStr = null;
	String chromoString = null;

	public Chromosome(String chromoString, int fitness) {
		fitness = this.fitness; // Defaults at 0
		chromoString = this.chromoString;
	}

	public Chromosome() {
		// Basically just want to be able to initalize in whatever way I want..
	}

	Random rnd = new Random();

	public String randomChromosome() {
		String chromosome = null;
		StringBuilder sb = new StringBuilder(13);

		for (int i = 0; i < 13; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		chromosome = sb.toString();
		return chromosome;
	}

	public Chromosome randomChromo() {
		Chromosome chromosome = new Chromosome();
		StringBuilder sb = new StringBuilder(13);

		for (int i = 0; i < 13; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		chromosome.setChromoStr(sb.toString());
		return chromosome;
	}

	public void setChromoStr(String chromoStr) {
		this.chromoStr = chromoStr;
	}

	public String getChromoStr() {
		return chromoStr;

	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
