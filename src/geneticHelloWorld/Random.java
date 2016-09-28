package geneticHelloWorld;

import java.util.List;

public class Random {

	public static void main(String[] args) {
		Population pop = new Population();
		GA ga = new GA();
		List<String> beep = pop.getRandPopulation(1000);
		String target = "Hello, world!";
		while (true) {
			for (String popu : beep) {
				if (pop.equals(target)) {
					System.out.println("got it!");
					return;
				} else {
					ga.mutate(popu);
				}
				System.out.println(popu);
			}
		}
	}
}
