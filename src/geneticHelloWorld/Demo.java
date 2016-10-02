package geneticHelloWorld;

import java.util.ArrayList;
import java.util.List;

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

public class Demo {

	public static void main(String[] args) {
		List<Double> time = new ArrayList<Double>();
		List<Integer> gen = new ArrayList<Integer>();
		int generation = 0;
		for (int i = 0; i < 20; i++) {
			long startTime = System.nanoTime();
			generation = GA.GAlgorithm(10000);
			gen.add(generation);
			long endTime = System.nanoTime();
			double duration = (endTime - startTime) / 1000000000.0;
			time.add(duration);
		}

		System.out.println("\n");
		
		for (int i = 0; i < time.size(); i++) {
			System.out.println("time:	" + time.get(i) + "	Gen:	" + gen.get(i));
		}

	}

}
