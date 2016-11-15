import java.util.Scanner;

public class GangeTabell{
	public static void main(String[] args){
		int tall = 0;
		int start = 0;
		int slutt = 0;
		int svar = 0;
		String input = "";
		System.out.println("Vis gangetabell for tall mellom:");
		Scanner scan = new Scanner(System.in);
		while (start==0){
			input = scan.next();
			try {
				start = Integer.parseInt(input);
			} catch (Exception e){
				System.out.print("Ugyldig tall, prøv igjen:");
			}
		}
		System.out.println("og:");
				while (slutt==0){
					input = scan.next();
					try {
						slutt = Integer.parseInt(input);
					} catch (Exception e){
						System.out.print("Ugyldig tall, prøv igjen:");
					}
		}
		System.out.println("Vis hvor langt inn i tabellene?");
		while (tall==0){
					input = scan.next();
					try {
						tall = Integer.parseInt(input);
					} catch (Exception e){
						System.out.print("Ugyldig tall, prøv igjen:");
					}
		}
		if (start>slutt){
			int temp = slutt;
			slutt = start;
			start = temp;
		}
		System.out.println("");
		for (int K=start;K<=slutt;K++){
			System.out.println(K+"-gangen:");
			for (int I=1; I<=tall; I++){
				svar = K*I;
				System.out.println(K+" x "+I+" = "+svar);
			}
			System.out.println("");
		}
	}
}