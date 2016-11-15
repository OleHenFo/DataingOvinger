//package pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecToTime extends JFrame{
	public SecToTime(){
		super("Seconds to H:M:S");
		this.pack();
		setSize(300,200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel p = new JPanel();
		JPanel p2 = new JPanel(new GridBagLayout());
		JPanel p3 = new JPanel();

		JTextField input = new JTextField("",12);
		JLabel text = new JLabel("Enter seconds/hours:");
		JLabel answer = new JLabel("       ");
		answer.setFont(new Font(answer.getFont().getName(),Font.PLAIN,20));
		JButton b = new JButton("Sec to H:M:S");
		JButton b2 = new JButton("H:M:S to sec");
		JButton c = new JButton("Close");

		p.add(text);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,4,4,4);

		gbc.gridx = 0;
		gbc.gridy = 0;
		p2.add(input,gbc);
		gbc.gridy = 1;
		p2.add(answer,gbc);

		p3.add(b);
		p3.add(b2);
		p3.add(c);

		add(p,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);

		ActionListener act = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String secI = input.getText();
				int secTest = 0;
				boolean error = false;

				try {
					secTest = Integer.parseInt(secI);
				} catch (Exception h){
					error = true;
				}

				if (error){
					answer.setText("Input error");
				} else {
					int hours = secTest / 3600;
					int min = (secTest % 3600)/60;
					int sec = (secTest % 3600)%60;

					answer.setText(hours+":"+min+":"+sec);
				}
			};
		};
		b.addActionListener(act);
		this.getRootPane().setDefaultButton(b);

		ActionListener act2 = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String timeI = input.getText();
				int hourT = 0;
				int minT = 0;
				int secT = 0;
				boolean error = false;

				try {
					String[] timeS = timeI.split(":");
					hourT = Integer.parseInt(timeS[0]);
					minT = Integer.parseInt(timeS[1]);
					secT = Integer.parseInt(timeS[2]);
				} catch (Exception h){
					error = true;
				}

				if (error){
					answer.setText("Input error");
				} else {
					int timeO = hourT*3600+minT*60+secT;
					String time2sec = ""+timeO;

					answer.setText(""+time2sec);
				}
			};
		};
		b2.addActionListener(act2);

		ActionListener exit = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			};
		};
		c.addActionListener(exit);
	}
	public static void main(String[] args){
		SecToTime fw = new SecToTime();
		//fw.pack();
		fw.setVisible(true);
	}
}