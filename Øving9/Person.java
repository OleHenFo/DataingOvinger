public class Person{
	final private String forNavn;
	final private String etterNavn;
	final private int fodt;

	public Person(String forIn, String etterIn, int fodtIn){
		this.forNavn = forIn;
		this.etterNavn = etterIn;
		this.fodt = fodtIn;
	}

	public String getForNavn(){
		return forNavn;
	}
	public String getEtterNavn(){
		return etterNavn;
	}
	public int getFodt(){
		return fodt;
	}
}