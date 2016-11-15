import static javax.swing.JOptionPane.*;

public class DiceGame{
	public static void main(String[] args){
		boolean game = true;
		int round = 0;
		while (true){
			String input = showInputDialog("Player 1, enter name:");
			if (input==null){
				System.exit(0);
			}
			Player player1 = new Player(input);

			input = showInputDialog("Player 2, enter name:");
			if (input==null){
				System.exit(0);
			}
			Player player2 = new Player(input);

			while (game){
				round++;
				Object[] options = {"Throw die!","Concede"};
				int ch = showOptionDialog(null, String.format("Current score: "+player1.getSum()), player1.getName(),
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options, options[0]);
				if (ch == 1){
					Object[] options2 = {"Yay!"};
					showOptionDialog(null, String.format(player2.getName()+" wins by default!"), "Game over!",
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options2, options2[0]);
					System.exit(0);
				}
				int die = player1.kastTerning();
				if (die == 1){
					player1.resetSum();
				} else if (player1.isWinner()){
					Object[] options7 = {"Yay!"};
					showOptionDialog(null, String.format(player1.getName()+" wins!"), "Game over!",
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options7, options7[0]);
					System.exit(0);
				}

				Object[] options3 = {"Next player"};
				showOptionDialog(null, String.format("Die: "+die+" Current score: "+player1.getSum()), "Round: "+round+" - "+player1.getName(),
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options3, options3[0]);

				Object[] options4 = {"Throw die!","Concede"};
				int ch2 = showOptionDialog(null, String.format("Current score: "+player2.getSum()), player2.getName(),
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options4, options4[0]);
				if (ch2 == 1){
					Object[] options5 = {"Yay!"};
					showOptionDialog(null, String.format(player1.getName()+" wins by default!"), "Game over!",
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options5, options5[0]);
					System.exit(0);
				}
				die = player2.kastTerning();
				if (die == 1){
					player2.resetSum();
				}  else if (player2.isWinner()){
					Object[] options8 = {"Yay!"};
					showOptionDialog(null, String.format(player2.getName()+" wins!"), "Game over!",
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options8, options8[0]);
					System.exit(0);
				}

				Object[] options6 = {"Next player"};
				showOptionDialog(null, String.format("Die: "+die+" Current score: "+player2.getSum()),"Round: "+round+" - "+player2.getName(),
							DEFAULT_OPTION, PLAIN_MESSAGE,
							null, options6, options6[0]);
			}
		}
	}
}