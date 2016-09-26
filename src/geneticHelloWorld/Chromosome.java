package geneticHelloWorld;

import java.util.Random;

public class Chromosome {
	
	final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,./<>?!£$%^&*()_+-=[];#{}:@~'";
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

}
