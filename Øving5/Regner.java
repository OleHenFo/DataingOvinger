import java.util.Scanner;

public class Regner{
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
		int tell;
		int nevn;
        while (true){
			System.out.println("Første teller:");
			tell = getNum();
			System.out.println("Første nevner:");
			nevn = getNum();
			Brok first = new Brok(tell,nevn);

			System.out.println("Andre teller:");
			tell = getNum();
			System.out.println("Andre nevner:");
			nevn = getNum();
			Brok scnd = new Brok(tell,nevn);

			System.out.println("");
			System.out.println("Choose operation:");
			System.out.println("1: Add");
			System.out.println("2: Subtract");
			System.out.println("3: Multiply");
			System.out.println("4: Divide");

			switch (getNum()){
			case 1:
				first.sum(scnd);
				break;
			case 2:
				first.sub(scnd);
				break;
			case 3:
				first.mul(scnd);
				break;
			case 4:
				first.div(scnd);
				break;
			default:
				System.out.println("Not valid answer, defaulting to addition\n");
				first.sum(scnd);
				break;
			}
			System.out.println("******** "+first.getTeller()+" ********");
			System.out.println("******** "+first.getNevner()+" ********\n");
		}
	}
}