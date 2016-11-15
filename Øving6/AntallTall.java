import java.util.Random;

class AntallTall{
	public static void main(String[] args){
		int[] antall = new int[11];
		int tall = 0;
		Random rand = new Random();
		for (int i=1;i<=1500;i++){
			tall = rand.nextInt(10)+1;
			antall[tall]+=1;
		}
		for (int i=1;i<=10;i++){
			int loops = Math.round(antall[i]/10);
			if (i==10){
				System.out.print(i+": "+antall[i]+"\t");
			} else {
				System.out.print(" "+i+": "+antall[i]+"\t");
			}
			for (int k=1;k<=loops;k++){
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}