public class ArbTaker{
	static java.util.GregorianCalendar kal = new java.util.GregorianCalendar();
	final static int ar = kal.get(java.util.GregorianCalendar.YEAR);
	private Person personalia;
	private int arbTakerNr = 0;
	private int ansattAr = 0;
	private double lonn = 0;
	private double skatt = 0;

	public ArbTaker(Person pers, int nr, int ari, double l, double s){
		this.personalia = pers;
		this.arbTakerNr = nr;
		this.ansattAr = ari;
		this.lonn = l;
		this.skatt = s;
	}

	public Person getPerson(){
		return personalia;
	}
	public int getNr(){
		return arbTakerNr;
	}
	public int getAnsattAr(){
			return ansattAr;
	}
	public double getLonn(){
			return lonn;
	}
	public double getSkatt(){
			return skatt;
	}

	public void setLonn(double l){
		this.lonn = l;
	}
	public void setSkatt(double s){
		this.skatt = s;
	}

	public double skatteTrekk(){
		return lonn * (skatt/100);
	}
	public double bruttoLonnAr(){
		return lonn*12;
	}
	public double skattAr(){
		return (lonn*10.5)*(skatt/100);
	}
	public String navn(){
		return personalia.getEtterNavn()+", "+personalia.getForNavn();
	}
	public int alder(){
		return ar - personalia.getFodt();
	}
	public int arAnsatt(){
		return ar - this.ansattAr;
	}
	public boolean ansattOverAr(int arIn){
		if (this.arAnsatt()>arIn){
			return true;
		} else {
			return false;
		}
	}
}