import java.io.*;
import static javax.swing.JOptionPane.*;

public class Konto{
	public static void main(String[] args) throws IOException{
		String saldoFil = "saldo.txt";
		String transFil = "trans.txt";
		String[] split = new String[2];
		double t = 0;
		double saldo;

		FileReader leserSaldoFil = new FileReader(saldoFil);
		BufferedReader leserSaldo = new BufferedReader(leserSaldoFil);
		FileReader leserTransFil = new FileReader(transFil);
		BufferedReader leserTrans = new BufferedReader(leserTransFil);

		saldo = Double.parseDouble(leserSaldo.readLine());
		System.out.println("Start saldo: "+saldo+"\n");
		System.out.println("--- Transaksjoner: ---");
		leserSaldo.close();

		String trans = leserTrans.readLine();
		while (trans != null){
			if (trans!=null){
				System.out.println(trans);
				split = trans.split(" ");
				if (split[0].charAt(0)=='I'){
					t += Double.parseDouble(split[1]);
				} else {
					t -= Double.parseDouble(split[1]);
				}
			}
			trans = leserTrans.readLine();
		}
		System.out.println("-----------------------\n");
		leserTrans.close();

		if ((saldo + t)<0){
			System.out.println("Saldo vil gå under 0, transaksjon umulig");
		} else {
			saldo += t;
			System.out.println("Ny saldo: "+saldo);

			FileWriter skrivSaldoFil = new FileWriter(saldoFil,false);
			PrintWriter skriver = new PrintWriter(new BufferedWriter(skrivSaldoFil));
			skriver.println(saldo);
			skriver.close();
		}
	}
}