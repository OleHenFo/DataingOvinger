import static javax.swing.JOptionPane.*;

public class Klient{
	public static void main(String[] args){
		Oppgaver opg = new Oppgaver();
		int valg = 0;
		int valg2 = 0;
		String input;
		String string = "";
		while (!false){
			Object[] options = {"Antall studenter","Oppgaver","Ny student","Godkjenn","Avslutt"};
			valg = showOptionDialog(null,String.format("Velg oprasjon"),"Oppage oversikt",DEFAULT_OPTION, PLAIN_MESSAGE,null,
									options,options[0]);
			switch (valg){
				case 0:
					showMessageDialog(null,"Det er "+opg.antallStudenter()+" studenter.");
					break;
				case 1:
					if (opg.antallStudenter()>0){
						string = "";
						for (int i=0;i<=opg.antallStudenter()-1;i++){
							string += (i+1)+": "+opg.getStudentNavn(i)+" \t "+opg.oppgStud(i)+"\n";
						}
						valg = Integer.parseInt(showInputDialog(null,"Velg student:\n"+string));
						showMessageDialog(null,opg.getStudentNavn(valg-1)+" har "+opg.oppgStud(valg-1)+" godkjente oppgaver");
					} else {
						showMessageDialog(null,"Ingen studenter!");
					}
					break;
				case 2:
					opg.nyStudent(showInputDialog(null,"Ny students navn:"));
					break;
				case 3:
					if (opg.antallStudenter()>0){
						string = "";
						for (int i=0;i<=opg.antallStudenter()-1;i++){
							string += (i+1)+": "+opg.getStudentNavn(i)+"\n";
						}
						valg = Integer.parseInt(showInputDialog(null,"Velg student:\n"+string));
						valg2 = Integer.parseInt(showInputDialog(null,"Øke hvor mye?"));
						opg.okOppg(valg-1,valg2);
						showMessageDialog(null,"Øker godkjenning med "+valg2+", "+opg.getStudentNavn(valg-1)+" har nå "+opg.oppgStud(valg-1)+" godkjente.");
					} else {
						showMessageDialog(null,"Ingen studenter!");
					}
					break;
				case 4:
					System.exit(0);
					break;
			}
		}
	}
}