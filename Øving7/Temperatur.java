import java.util.Random;
import java.util.Arrays;
import static javax.swing.JOptionPane.*;

class Temperatur{
	public static int[][] temp = new int[31][24];

	public static void middHverDag(){
		int midTemp=0;
		int sum=0;
		for (int i=0;i<=30;i++){
			sum=0;
			for (int k=0;k<=23;k++){
				sum += temp[i][k];
			}
			midTemp=sum/24;
			if (i<9){
				System.out.println(" "+(i+1)+". ---\t "+midTemp);
			} else {
				System.out.println((i+1)+". ---\t "+midTemp);
			}
		}
		System.out.println("----End----");
	}

	public static void middHverTime(){
		int midTemp=0;
		int sum=0;
		for (int i=0;i<=23;i++){
			sum=0;
			for (int k=0;k<=30;k++){
				sum += temp[k][i];
			}
			midTemp=sum/31;
			if (i<9){
				System.out.println(" "+(i+1)+". ---\t "+midTemp);
			} else {
				System.out.println((i+1)+". ---\t "+midTemp);
			}
		}
		System.out.println("----End----");
	}

	public static void middMnd(){
			int midTemp=0;
			int sum=0;
			for (int i=0;i<=23;i++){
				for (int k=0;k<=30;k++){
					sum += temp[k][i];
				}
			}
			midTemp=sum/(24*31);
			System.out.println("Midd temp denne maaneden: "+midTemp);
			System.out.println("----End----");
	}

	public static void middGrupper(){
			int[] grupper = new int[5];
			int mid=0;
			int sum=0;
			for (int i=0;i<=30;i++){
				sum=0;
				for (int k=0;k<=23;k++){
					sum += temp[i][k];
				}
				mid=sum/24;
				if (mid<(-5)){
					grupper[0]+=1;
				} else if (mid<1){
					grupper[1]+=1;
				} else if (mid<6){
					grupper[2]+=1;
				} else if (mid<11){
					grupper[3]+=1;
				} else {
					grupper[4]+=1;
				}
			}
			System.out.println("Mindre enn -5 : "+grupper[0]);
			System.out.println("Mellom -5 og 0: "+grupper[1]);
			System.out.println("Mellom 0 og 5 : "+grupper[2]);
			System.out.println("Mellom 5 og 10: "+grupper[3]);
			System.out.println("Større enn 10 : "+grupper[4]);
			System.out.println("----End----");
	}

	public static void main(String[] args){
		Random rand = new Random();
		int change = 12;
		int r = 0;

		for (int i=0;i<=30;i++){
			for (int k=0;k<=23;k++){
				r = (-2) + rand.nextInt((2-(-2))+1);
				change += r;
				if (change<(-10)){
					change+=1;
				} else if (change>20){
					change-=1;
				}
				temp[i][k]=change;
				System.out.print(change+" , ");
			}
			System.out.println("");
		}

		Object[] options1 = { "Mideltemp hver dag", "Middeltemp hver time","Middeltemp måned","Middel i grupper", "Avslutt" };
		while (true){
			int ch = showOptionDialog(null, String.format("Valg av temperatur undersøkelses operasjoner"), "Temperatur undersøkelse",
						DEFAULT_OPTION, PLAIN_MESSAGE,
						null, options1, options1[0]);
			switch (ch){
				case 0:
					middHverDag();
					break;
				case 1:
					middHverTime();
					break;
				case 2:
					middMnd();
					break;
				case 3:
					middGrupper();
					break;
				case 4:
					System.exit(0);
			}
		}
	}
}