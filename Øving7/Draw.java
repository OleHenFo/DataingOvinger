import java.awt.*;
import javax.swing.*;

public class Draw extends JFrame{

	/*public Draw(){
		super();
	}*/

	public void paint(Graphics g){
		//Body
		g.setColor(Color.YELLOW);
		g.fillOval(75,75,150,150);
		g.setColor(Color.BLACK);
		g.drawOval(75,75,150,150);

		// Eyes
		g.setColor(Color.WHITE);
		g.fillOval(75+30,75+40,35,35);
		g.fillOval(225-65,75+40,35,35);
		g.setColor(Color.BLACK);
		g.drawOval(75+30,75+40,35,35);
		g.drawOval(225-65,75+40,35,35);

		// Pupils
		g.fillOval(75+40,75+58,10,10);
		g.fillOval(225-55,75+58,10,10);

		// Mouth
		g.fillArc(75+30,120,90,90,180,180);
	}

	public static void main(String arg[]){
		Draw frame = new Draw();
		frame.setSize(300,300);

		frame.setVisible(true);
	}
}