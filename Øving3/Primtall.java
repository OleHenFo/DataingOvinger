import static javax.swing.JOptionPane.*;
public class Primtall{
	public static void main(String[] args){
		int tall = 0;
		String input = "";
		while (true){
			String read = showInputDialog("Sjekk om primtall:");
			if (read == null) {
				System.exit(0);
			}
			try {
				tall = Integer.parseInt(read);
			} catch (Exception e){
				showMessageDialog(null,"Skriv ett gyldig tall!");
			}
			boolean prim = true;
			for (int I=2;I<tall;I++){
				if (tall%I==0){
					prim=false;
					break;
				}
			}
			if (prim){
				showMessageDialog(null,tall+" er et primtall!");
			} else {
				showMessageDialog(null,tall+" er IKKE et primtall!");
			}
		}

	}
}