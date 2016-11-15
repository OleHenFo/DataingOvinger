public class Student{
	private String navn;
	private int antOppg;

	public Student(String navn){
		this.navn = navn;
	}

	public String getNavn(){
		return this.navn;
	}
	public int getAntOppg(){
		return this.antOppg;
	}

	public void okAntOppg(int okning){
		this.antOppg += okning;
	}
	public String toString(){
		return "Navn: "+this.navn+", Antall oppgaver: "+this.antOppg;
	}
}