public class Oppgaver{
	private Student[] studenter;
	private int antStud = 0;

	public Oppgaver(){
		studenter = new Student[50];
	}

	public int antallStudenter(){
		return antStud;
	}

	public int oppgStud(int nr){
		return studenter[nr].getAntOppg();
	}

	public void nyStudent(String navn){
		studenter[antStud++] = new Student(navn);
	}

	public String getStudentNavn(int nr){
		return studenter[nr].getNavn();
	}

	public void okOppg(int nr,int tall){
		studenter[nr].okAntOppg(tall);
	}

	public String toString(){
		String string = "";
		for (int i = 0 ; i <= antStud-1 ; i++){
			string += ", "+studenter[i].getNavn()+" - "+studenter[i].getAntOppg();
		}
		return string;
	}
}