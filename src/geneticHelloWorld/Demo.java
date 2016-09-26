package geneticHelloWorld;

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

import java.util.Random;

public class Demo {

	public String[] population() {
		String[] population = null;
		return population;
	}

	/*
	 * Mutates an individual by incrementing a random value in the string by 1,
	 * or leaving it alone.
	 * 
	 * Leave it alone so the mutation is random.
	 * 
	 * If you have the probability or chance of mutation this can be factored in
	 * also.
	 */
	public char[] mutate(String individual) {

		char[] indi = individual.toCharArray();
		Random r = new Random();
		int randChar = r.nextInt(indi.length);

		// System.out.println(randChar);

		// for (int i = 0; i < indi.length; i++) {
		// System.out.print(indi[i]);
		// }

		// System.out.println("\n");

		indi[randChar] += r.nextInt((1 - 0) + 1) + 0;

		// for (int i = 0; i < indi.length; i++) {
		// System.out.print(indi[i]);
		// }

		return indi;

	}

	public static void main(String[] args) {

		Demo ga = new Demo();

		String individual = "hello worlc";
		String target = "hello world";

		FitnessFunctions fit = new FitnessFunctions();
		int fitness = fit.fitnessHamming(individual, target);

		final long startTime = System.currentTimeMillis();
		System.out.println("total fitness is: " + fitness);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

		/*
		 * int i = 0; while (true) {
		 * 
		 * System.out.println("\n\n" + i + " : FITNESS AT THIS: " + fitness +
		 * "\n\n");
		 * 
		 * i += 1; char[] mutate = ga.mutate(individual);
		 * 
		 * String mutateString = String.valueOf(mutate);
		 * 
		 * System.out.println(mutateString);
		 * 
		 * int fitnessCurrent = ga.fitnessHamming(mutateString, target);
		 * 
		 * System.out.println(fitnessCurrent + " cf " + fitness + " fit");
		 * 
		 * if (fitnessCurrent < fitness) { individual = mutateString;
		 * System.out.println(i + " : " + "current fitness : " + fitnessCurrent
		 * + " mutate : " + individual); }
		 * 
		 * if (fitnessCurrent == 0 && !individual.contains("!")) {
		 * System.out.println(i + " : " + "current fitness : " + fitnessCurrent
		 * + " mutate : " + individual); break; } }
		 */
	}

}
