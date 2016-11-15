import java.util.Scanner;

class RandomTest{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		while (true){
			System.out.print("Double(1) or integer(2)?");
			int in = scan.nextInt();
			if (in == 1){
				MinRandom rand = new MinRandom();
				System.out.println("Nederste tall:");
				double ned = scan.nextDouble();
				System.out.println("Topp tall:");
				double opp = scan.nextDouble();
				System.out.println("");
				System.out.println(rand.nesteDesimal(ned,opp));
			}

			if (in == 2){
				MinRandom rand = new MinRandom();
				System.out.println("Nederste tall:");
				int ned = scan.nextInt();
				System.out.println("Topp tall:");
				int opp = scan.nextInt();
				System.out.println("");
				System.out.println(rand.nesteHeltall(ned,opp));
			}
		}
	}
}