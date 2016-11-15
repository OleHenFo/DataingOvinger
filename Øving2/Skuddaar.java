import java.util.Scanner;

public class Skuddaar{
	public static void main(String[] args){
		while (true){
			System.out.println("Sjekk om år er skuddår:");
			System.out.print("År: ");
			Scanner scan = new Scanner(System.in);
			String input = "";
			int year = 0;
			while (year == 0){
				input = scan.next();
				try {
					year = Integer.parseInt(input);
					if (year%4==0&&year%100!=0||year%400==0){
						System.out.println(year+" er ett skuddår!\n");
					} else {
						System.out.println(year+" er IKKE ett skuddår!\n");
					}
				} catch (Exception e) {
					System.out.println("Skriv inn ett gydlig år. (ie. 1999)");
				}
			}
		}
	}
}