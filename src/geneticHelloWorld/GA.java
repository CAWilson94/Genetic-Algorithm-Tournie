package geneticHelloWorld;

import java.util.ArrayList;
import java.util.List;

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
	 * Hamming distance to get fitness
	 */
	public int fitnessCurrent(String target, List<String> population) {
		// int[] fitnessCurrent;
		int fitnessCurrent = 0;
		Demo d = new Demo();
		for (String p : population) {
			/**
			 * Each position in population has a chromosome full of genes
			 * Need to see difference between each chromsome and the target
			 */
			if (!target.equals(p)) {
				System.out.println(p + " not the same as " + target);
				fitnessCurrent = d.fitnessHamming(p,target);
			}
			
		}

		return fitnessCurrent;
	}

	public static void main(String[] args) {

		String target = "hello, world!";
		// Step 1
		Population p = new Population();
		GA ga = new GA();
		List<String> population = p.getRandPopulation();
		
		/*for(String p1: population){
			System.out.println(p1);
		}
		 */
		
		// Step 2
		System.out.println(ga.fitnessCurrent(target, population));

	}

}
