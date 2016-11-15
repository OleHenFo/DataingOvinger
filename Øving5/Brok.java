public class Brok{
	int teller;
	int nevner;

	Brok(int tellerI){
			teller = tellerI;
			nevner = 1;
	}

	Brok(int tellerI, int nevnerI) throws IllegalArgumentException{
		teller = tellerI;
		if (nevnerI == 0){
			throw new IllegalArgumentException();
		} else {
			nevner = nevnerI;
		}
	}

	void Forkort(){
		int tellMod;
		int nevnMod;
		boolean ok;
		for (int i=this.getTeller();i>2;i--){
			tellMod = this.getTeller()%i;
			nevnMod = this.getNevner()%i;
			if (tellMod==0&&nevnMod==0){
				teller = this.getTeller()/i;
				nevner = this.getNevner()/i;
				i++;
			}
		}
		if (teller == nevner){
			teller = 1;
			nevner = 1;
		}
	}

	int getTeller(){
		return teller;
	}

	int getNevner(){
		return nevner;
	}

	void sum(Brok in){
		int felles = this.getNevner()*in.getNevner();
		int tempTeller = this.getTeller()*in.getNevner()+in.getTeller()*this.getNevner();
		nevner = felles;
		teller = tempTeller;
		this.Forkort();
	}

	void sub(Brok in){
		int felles = this.getNevner()*in.getNevner();
		int tempTeller = this.getTeller()*in.getNevner()-in.getTeller()*this.getNevner();
		nevner = felles;
		teller = tempTeller;
		this.Forkort();
	}

	void mul(Brok in){
		int tempTeller = this.getTeller()*in.getTeller();
		int tempNevner = in.getNevner()*this.getNevner();
		nevner = tempNevner;
		teller = tempTeller;
		this.Forkort();
	}

	void div(Brok in){
		int tempTeller = this.getTeller()*in.getNevner();
		int tempNevner = this.getNevner()*in.getTeller();
		nevner = tempNevner;
		teller = tempTeller;
		this.Forkort();
	}
}