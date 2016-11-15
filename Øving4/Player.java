import java.util.Random;

public class Player{
	private int sumPoeng;
	private String navn;
	Random dice = new Random();

	Player(){
		sumPoeng=0;
		navn="No-Name";
	}

	Player(String nIn){
		sumPoeng = 0;
		navn = nIn;
	}

	public String getName(){
		return navn;
	}

	public int getSum(){
		return sumPoeng;
	}

	public void resetSum(){
			sumPoeng = 0;
	}

	public int kastTerning(){
		int rand = dice.nextInt(6)+1;
		sumPoeng += rand;
		return rand;
	}

	public boolean isWinner(){
		if (sumPoeng>=100){
			return true;
		} else {
			return false;
		}
	}
}