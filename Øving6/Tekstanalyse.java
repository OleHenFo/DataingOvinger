import java.util.Scanner;
import java.io.IOException;
import static javax.swing.JOptionPane.*;

class Tekstanalyse{
	int[] antallTegn = new int[30];

	public Tekstanalyse(String input){
		analyserTekst(input);
	}

	public static void clear() throws IOException, InterruptedException{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

	public void analyserTekst(String input){
		antallTegn = null;
		antallTegn = new int[30];
		for (int i=0;i<=input.length()-1;i++){
			char tegn = input.charAt(i);
			int verdi = tegn;
			if (verdi>=97&&verdi<=122){
				antallTegn[verdi-97]=antallTegn[verdi-97]+1;
			} else if (verdi>=65&&verdi<=90){
				antallTegn[verdi-65]=antallTegn[verdi-65]+1;
			} else if (verdi==230||verdi==198){
				antallTegn[26]=antallTegn[26]+1;
			} else if (verdi==248||verdi==216){
				antallTegn[27]=antallTegn[27]+1;
			} else if (verdi==229||verdi==197) {
				antallTegn[28]=antallTegn[28]+1;
			} else {
				antallTegn[29]=antallTegn[29]+1;
			}
		}
	}

	public int getAntallForskjelligeBokstaver(){
		int number = 0;
		for (int i=0;i<=28;i++){
			if (antallTegn[i]>0){
				number++;
			}
		}
		return number;
	}

	public int getAntallBokstaver(){
		int number = 0;
		for (int i=0;i<=28;i++){
			number = number + antallTegn[i];
		}
		return number;
	}

	public int getProsentIkkeBokstav(){
		double tegn = antallTegn[29];
		double total = getAntallBokstaver()+tegn;

		return (int) ((tegn/total)*100);
	}

	public String getOftestBokstav(){
		String maxes = "";
		int max = 0;
		for (int i=0;i<=28;i++){
			if (antallTegn[i]>max){
				max = antallTegn[i];
			}
		}
		for (int i=0;i<=28;i++){
			if (antallTegn[i]==max){
				char ch = (char) (65+i);
				maxes += " " + ch;
			}
		}
		return maxes;
	}

	public int getTegn(String str){
		char ch = str.charAt(0);
		int num = (int) ch;
		if (num==8216||num==8217){
			return antallTegn[26];
		} else if (num==8250){
			return antallTegn[27];
		} else if (num==8224){
			return antallTegn[28];
		} else {
			for (int i=0;i<=28;i++){
				for (int k=97;k<=122;k++){
					if (num == k){
						return antallTegn[k-97];
					}
				}
				for (int k=65;k<=90;k++){
					if (num == k){
						return antallTegn[k-65];
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException, InterruptedException{
		Scanner scan = new Scanner(System.in);
		//System.out.println("Skriv inn tekst for analyse:");
		final Tekstanalyse ta = new Tekstanalyse(showInputDialog("Skriv inn tekst for analyse: "));
		clear();

		while (true){
			System.out.println("Velg metode:");
			System.out.println("1: Antall forskjellige bokstaver");
			System.out.println("2: Antall bokstaver");
			System.out.println("3: Antall av bestemt bokstav");
			System.out.println("4: Prosentandel av tekst som ikke er tekst");
			System.out.println("5: Bokstaven(e) som forekommer oftest");
			System.out.println("6: Ny tekst");
			System.out.println("7: Avslutt");

			int valg = 0;
			while (true){
				String input = scan.next();
				try {
					valg = Integer.parseInt(input);
					if (valg < 1 || valg > 7){
						System.out.println("Skriv et gyldig valg");
					} else {
						break;
					}
				} catch (Exception e) {
					System.out.println("Skriv et gyldig valg");
				}
			}

			clear();

			switch (valg){
				case 1:
					System.out.println("Antall forskjellige bokstaver: "+ta.getAntallForskjelligeBokstaver());
					break;
				case 2:
					System.out.println("Antall bokstaver: "+ta.getAntallBokstaver());
					break;
				case 3:
					System.out.println("Skriv inn bokstaven du vil sjekke:");
					String in = scan.next();
					System.out.println("Antall bokstaver: "+ta.getTegn(in));
					break;
				case 4:
					System.out.println("Prosentandel av teksten som er ikke-bokstav tegn: "+ta.getProsentIkkeBokstav()+"%");
					break;
				case 5:
					System.out.println("Bokstaven(e) som forekommer oftest er: "+ta.getOftestBokstav());
					break;
				case 6:
					String nyTekst = showInputDialog("Skriv ny tekst:");
					ta.analyserTekst(nyTekst);
					break;
				case 7:
					System.exit(0);
				default:
					break;
			}
			System.out.println("Trykk enter");
			System.in.read();
			clear();
		}
	}
}