package geneticHelloWorld;

public class FitnessFunctions {

	public int fitnessHamming(String individual, String target) {

		int hammingFitness = 0;

		char[] indi = individual.toCharArray();
		char[] targ = target.toCharArray();
		
		for (int i = 0; i < indi.length; i++) {

			if (targ[i] != indi[i]) {
				hammingFitness++;
			}
		}
		return hammingFitness;
	}

	public int fitnessFunction(String individual, String target) {

		int fitness = 0;
		System.out.println(individual);
		char[] indi = individual.toCharArray();
		char[] targ = target.toCharArray();

		for (int i = 0; i < indi.length; i++) {
			int indiInt = Math.abs((Character.getNumericValue(indi[i])));
			int targInt = Math.abs((Character.getNumericValue(targ[i])));
			int currentFitness = Math.abs(indiInt - targInt);
			fitness += currentFitness;
		}
		return fitness;
	}
}
