public class Tekst{
	String string;
	public Tekst(String arg){
		string = arg;
	}

	public int antallOrd(){
		return string.split(" ").length;
	}

	public float snittOrdlengde(){
		float teller = 0;
		String[] temp = string.split(" ");
		for (int i=0;i<=temp.length-1;i++){
			teller += temp[i].length();
		}
		return teller/temp.length;
	}

	public float snittSetning(){
		float teller = 0;
		String temp = string.replaceAll("[!?:;]{1,}",".");
		String[] liste = temp.split("[.]");
		for (int i=0;i<=liste.length-1;i++){
			teller += liste[i].split(" ").length;
		}
		return teller/liste.length;
	}

	public void byttOrd(String s1,String s2){
		String temp = string.toLowerCase();
		temp = string.replaceAll("("+s1.toLowerCase()+")",s2);
		string = temp;
	}

	public String getCapsString(){
			return string.toUpperCase();
	}

	public String getString(){
		return string;
	}

	public static void main(String[] args){
		Tekst test = new Tekst("Millom Bakkar og Berg ut med Havet heve Nordmannen fenget sin Heim, der han sjølv hever Tufterna gravet og sett sjølv sine Hus uppaa deim.\nOg han vandast til Vaagnad og Møda, der han laut vera herdig og sterk; og han høyrer med Hugnad ei Røda um stor Manndom og dugande Verk.");
		System.out.println("Antall ord:         "+test.antallOrd());
		System.out.println("Snitt ordlengde:    "+test.snittOrdlengde());
		System.out.println("Snitt ord pr. setn: "+test.snittSetning());
		test.byttOrd("og","ÆÆ");
		System.out.println(test.getString());
		System.out.println(test.getCapsString());
	}
}