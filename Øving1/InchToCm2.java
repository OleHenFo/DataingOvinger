import static javax.swing.JOptionPane.*;

class InchToCm2{
	public static void main(String[] args){
		while (true){
			double inch = 0;
			double cm = 0;
			double value = 0;
			while (inch == 0){
				String inchRead = showInputDialog("Input inches:");
				try {
					inch = Double.parseDouble(inchRead);
				} catch (Exception e){
					showMessageDialog(null,"Enter a valid number!");
				}
			}
			cm = inch*2.54;
			value = Math.round(cm * 100d)/100d;
			Object[] options = {"Again","Exit"};
			int choice = showOptionDialog(null,"Centimeters: "+value,"Test",DEFAULT_OPTION,WARNING_MESSAGE,null,options,options[0]);
			if (choice==1) {
				break;
			}
		}
		System.exit(0);
	}
}