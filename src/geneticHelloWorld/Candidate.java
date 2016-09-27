package geneticHelloWorld;

public class Candidate {
	public int fitness = 0;
	public String chromosome = null;

	public Candidate(int fitness, String chromosome) {
		this.fitness = fitness;
		this.chromosome = chromosome;
	}

	public int getfitness() {
		return fitness;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

}
