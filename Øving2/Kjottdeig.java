import java.text.DecimalFormat;
import java.util.Scanner;

public class Kjottdeig{
	public static void main(String[] args){
		double a = 35.90;
		double b = 39.50;
		double kiloA = a/450 * 1000;
		double kiloB = b/500 * 1000;
		DecimalFormat formatN = new DecimalFormat("#.00");
		System.out.println("Kjøttdeig A koster "+formatN.format(kiloA));
		System.out.println("Kjøttdeig B koster "+formatN.format(kiloB));
		if (kiloA>kiloB){
			System.out.println("Kjøttdeig B er billigst!");
		} else {
			System.out.println("Kjøttdeig A er billigst!");
		}
	}
}