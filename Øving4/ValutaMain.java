import java.util.Scanner;

public class ValutaMain{
	public static void main(String[] args){
		Valuta eur = new Valuta(9.277);
		Valuta usd = new Valuta(8.333);
		Valuta sek = new Valuta(0.9748);
		while (true){
			System.out.println("Velg valuta:");
			System.out.println("1: Euro");
			System.out.println("2: US Dollar");
			System.out.println("3: Svenske Kr");
			System.out.println("4: Avslutt");
			Scanner scan = new Scanner(System.in);
			String input=scan.next();

			double ch = 0;
			try {
				ch = Integer.parseInt(input);
				if (ch<1||ch>4){
					System.out.println("Velg 1-4\n");
					continue;
			}
			} catch (Exception e){
				System.out.println("Velg 1-4\n");
			}

			if (ch==4){
				System.exit(0);
			}
			System.out.print("\n");
			System.out.println("Hvor mye?");
			input = scan.next();
			double cash = 0;
			try {
				cash = Double.parseDouble(input);
			} catch (Exception e){
				System.out.println("Skriv et tall\n");
			}

			double out = 0;
			if (ch==1){
				out = cash*eur.getValue();
				//System.out.println(cash+" Euro = "+out+" NOK\n");
			} else if (ch==2){
				out = cash*usd.getValue();
				//System.out.println(cash+" Usd = "+out+" NOK\n");
			} else if (ch==3){
				out = cash*sek.getValue();
				//System.out.println(cash+" Sek = "+out+" NOK\n");
			}
			System.out.println("Verdi i nok: "+out+"\n");
		}
	}
}