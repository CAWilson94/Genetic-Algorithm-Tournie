package geneticHelloWorld;

import java.util.Random;

public class Chromosome {

	final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,./<>?!£$%^&*()_+-=[];#{}:@~'";
	final String target = "Hello, World!";
	public int fitness = 0;

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

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
