import java.util.Scanner;

public class Skuddaar{
	public static void main(String[] args){
		while (true){
			System.out.println("Sjekk om �r er skudd�r:");
			System.out.print("�r: ");
			Scanner scan = new Scanner(System.in);
			String input = "";
			int year = 0;
			while (year == 0){
				input = scan.next();
				try {
					year = Integer.parseInt(input);
					if (year%4==0&&year%100!=0||year%400==0){
						System.out.println(year+" er ett skudd�r!\n");
					} else {
						System.out.println(year+" er IKKE ett skudd�r!\n");
					}
				} catch (Exception e) {
					System.out.println("Skriv inn ett gydlig �r. (ie. 1999)");
				}
			}
		}
	}
}