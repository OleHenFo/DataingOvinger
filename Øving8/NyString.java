public class NyString{
	String string;
	public NyString(String arg){
		string = arg;
	}

	public String forkort(){
		String[] temp = string.split(" ");
		String ut="";
		for (int i=0;i<=temp.length-1;i++){
			ut += temp[i].charAt(0);
		}
		return ut;
	}

	public String fjern(String tegn){
		String ut=string.replaceAll(tegn,"");
		return ut;
	}

	public static void main(String[] args){
		NyString test = new NyString("Dette er en tekst med ord i!");

		System.out.println(test.forkort());
		System.out.println(test.fjern("e"));
	}
}