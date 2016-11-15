import java.util.Random;

public class MinRandom{

	Random rand = new Random();

	public int nesteHeltall(int nedre,int ovre){
		boolean work = true;
		int r = 0;
		r = nedre + rand.nextInt((ovre-nedre)+1);
		return r;
	}

	public double nesteDesimal(double nedre, double ovre){
		double r = 0;
		r = (nedre + (ovre-nedre)*rand.nextDouble());
		return r;
	}
}