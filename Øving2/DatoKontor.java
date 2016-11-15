import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DatoKontor extends JFrame{
	public DatoKontor() {
		super("Finn ditt ligningskontor");
		this.pack();
		setSize(380,180);
		setResizable(false);

		JPanel topTextPanel = new JPanel();
		JPanel midInputPanel = new JPanel(new GridBagLayout());
		JPanel botButtonsPanel = new JPanel();

		JLabel topText = new JLabel("Fyll inn hvilken dag i måneden du er født.");
		topTextPanel.add(topText);
		add(topTextPanel,BorderLayout.NORTH);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,4,4,4);

		JTextField input = new JTextField(20);
		JLabel answer = new JLabel("Kontor nr:     ");
		answer.setFont(new Font(answer.getFont().getName(),Font.PLAIN,20));
		gbc.gridx = 0;
		gbc.gridy = 0;
		midInputPanel.add(input,gbc);
		gbc.gridy = 1;
		midInputPanel.add(answer,gbc);
		add(midInputPanel,BorderLayout.CENTER);

		JButton enter = new JButton("Send");
		JButton exit = new JButton("Avslutt");
		botButtonsPanel.add(enter);
		botButtonsPanel.add(exit);
		add(botButtonsPanel,BorderLayout.SOUTH);

		ActionListener act = new ActionListener(){
			boolean error = false;
			int dato = 0;

			public void actionPerformed(ActionEvent e){
				String datoIn = input.getText();

				try {
					error = false;
					dato = Integer.parseInt(datoIn);
				} catch (NumberFormatException h) {
					error = true;
				}

				if (error){
					answer.setText("Skriv en dato mellom 1 og 31");
				} else {
					if (dato>0&&dato<9){
						answer.setText("Kontor nr: 113");
					} else if (dato>8&&dato<15){
						answer.setText("Kontor nr: 120");
					} else if (dato>14&&dato<26){
						answer.setText("Kontor nr: 125");
					} else if (dato>25&&dato<32){
						answer.setText("Kontor nr: 134");
					} else {
						answer.setText("Skriv en dato mellom 1 og 31");
					}
				}
			}

		};
		enter.addActionListener(act);

		ActionListener exitIn = new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					};
		};
		exit.addActionListener(exitIn);
	};

	public static void main(String[] args){
		DatoKontor frame = new DatoKontor();
		frame.setVisible(true);
	};
}