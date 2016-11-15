import java.util.Scanner;

public class TallTest{
	public static int getNum(){
		int out = 0;
		Scanner scan = new Scanner(System.in);
		while (out == 0) {
			String input = scan.next();
			try {
				out = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Enter a valid integer");
			}
		}

		return out;
	}

	public static void main(String[] args){
		while (true){
			System.out.println("Input number:");
			// First
			int num1 = getNum();
			if (num1>0){
				System.out.println("You enetered the positive number "+num1);
			} else {
				System.out.println("You enetered the negative number "+num1);
			}
			System.out.println("\nTest division with number:");
			int num2 = getNum();
			if (num1%num2!=0){
				System.out.println(num1+" is NOT divisable by "+num2+" ("+num1%num2+" remainder)");
			} else {
				System.out.println(num1+" is divisable by "+num2+"! ("+num1/num2+" times)");
			}
			System.out.println("\nCheck if "+num1+" is in intervall:");
			System.out.println("Enter first number (min):");
			int num3 = getNum();
			System.out.println("Enter second number (max):");
			int num4=num3-1;
			while (num4<num3){
				num4 = getNum();
				if (num4<num3){
					System.out.println("Max number has to be bigger than min number!");
				}
			}
			if (num1>num3&&num1<num4){
				System.out.println("Yes, "+num1+" is between "+num3+" and "+num4+"!");
			} else {
				System.out.println("No, "+num1+" is NOT between "+num3+" and "+num4+"!");
			}
			System.out.println("Enter 1 for again or 2 for exit");
			int ch = getNum();
			if (ch==2){
				break;
			}
		}
	}
}