import java.util.Scanner;

public class Klient{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		ArbTaker[] ArbTakere = new ArbTaker[5];

		Person ole = new Person("Ole","Fossland",1993);
		ArbTakere[0] = new ArbTaker(ole,42,2002,35000,35);

		Person tryg = new Person("Trygve","Nerland",1993);
		ArbTakere[1] = new ArbTaker(tryg,43,1998,18000,12);

		Person tor = new Person("Tor","Grønning",1994);
		ArbTakere[2] = new ArbTaker(tor,44,2013,22000,25);

		Person kari = new Person("Kari","Bræmnes",1952);
		ArbTakere[3] = new ArbTaker(kari,45,1972,33000,28);

		Person simen = new Person("Simen","Storvik",1994);
		ArbTakere[4] = new ArbTaker(simen,46,2014,20000,23);

		for (int i=0;i<=ArbTakere.length-1;i++){
			System.out.println("Nr. "+i+": "+ArbTakere[i].getPerson().getForNavn());
			System.out.println("Skatt i mnd: "+ArbTakere[i].skatteTrekk());
			System.out.println("Brutto i år: "+ArbTakere[i].bruttoLonnAr());
			System.out.println("Skatt i år: "+ArbTakere[i].skattAr());
			System.out.println("Navn: "+ArbTakere[i].navn());
			System.out.println("Alder: "+ArbTakere[i].alder());
			System.out.println("År ansatt: "+ArbTakere[i].arAnsatt());
			System.out.println("Ansatt i over 5 år? "+ArbTakere[i].ansattOverAr(5));
			System.out.println("Ansatt i over 40 år? "+ArbTakere[i].ansattOverAr(40)+"\n");
		}
		while (true){
			boolean skip = false;
			System.out.println("Hvilken arbeider vil du endre på?");
			for (int i=0;i<=ArbTakere.length-1;i++){
				System.out.println((i+1)+": "+ArbTakere[i].navn());
			}
			System.out.println((ArbTakere.length+1)+": Full liste");
			System.out.println((ArbTakere.length+2)+": Avslutt");
			System.out.print("Nr: ");
			int nr = scan.nextInt()-1;

			if ((nr)==ArbTakere.length+1){
				break;
			} else if ((nr)==ArbTakere.length){
				System.out.println("");
				for (int i=0;i<=ArbTakere.length-1;i++){
					System.out.println("Nr. "+i+": "+ArbTakere[i].getPerson().getForNavn());
					System.out.println("Skatt i mnd: "+ArbTakere[i].skatteTrekk());
					System.out.println("Brutto i år: "+ArbTakere[i].bruttoLonnAr());
					System.out.println("Skatt i år: "+ArbTakere[i].skattAr());
					System.out.println("Navn: "+ArbTakere[i].navn());
					System.out.println("Alder: "+ArbTakere[i].alder());
					System.out.println("År ansatt: "+ArbTakere[i].arAnsatt());
					System.out.println("Ansatt i over 5 år? "+ArbTakere[i].ansattOverAr(5));
					System.out.println("Ansatt i over 40 år? "+ArbTakere[i].ansattOverAr(40)+"\n");
					skip = true;
				}
			} else {
				System.out.println("");
			}

			if (!skip){
				System.out.println("Hva vil du endre?");
				System.out.println("1: Skatt");
				System.out.println("2: Lønn");
				int nr2 = 0;
				while (nr2==0){
					nr2 = scan.nextInt();
					if (nr2!=1&&nr2!=2){
						nr2=0;
						System.out.println("Velg 1 eller 2");
					}
				}
				System.out.println("");
				if (nr2==1){
					System.out.println("Nåværende skatt for "+ArbTakere[nr].navn()+" er "+ArbTakere[nr].getSkatt()+"%");
					System.out.println("Skriv ny skatteprosent:");
					double skatt = scan.nextDouble();
					ArbTakere[nr].setSkatt(skatt);
					System.out.println("Ny skatteprosent for "+ArbTakere[nr].navn()+" er "+ArbTakere[nr].getSkatt()+"%\n");
				} else if (nr2==2){
					System.out.println("Nåværende lønn for "+ArbTakere[nr].navn()+"er "+ArbTakere[nr].getLonn()+"kr pr mnd");
					System.out.println("Skriv ny lønn:");
					double lonn = scan.nextDouble();
					ArbTakere[nr].setLonn(lonn);
					System.out.println("Ny lønn for "+ArbTakere[nr].navn()+" er "+ArbTakere[nr].getLonn()+"kr pr mnd\n");
				}
			}
		}
	}
}