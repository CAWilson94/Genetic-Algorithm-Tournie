package geneticHelloWorld;

public class Constants {

	public static final String TARGET = "Yer Maw";
	public static final int CROSSOVER_RATE = 7; // Trust the crossover rate
	public static final double MUTATION_CHANCE = 0.1;
	public static final Chromosome TARGET_CHROMOSOME = new Chromosome(TARGET, 0);
	public static final int MAX_GENERATION = 3000;
	public static final String CHARLOTTE = "Charlotte";
	public static final Chromosome CHAR_CHROMOSOME = new Chromosome(CHARLOTTE, 0);
	public static final int POP_SIZE = 10000;
	public static final int crossoverPoint = TARGET.length()/2+1;
}
