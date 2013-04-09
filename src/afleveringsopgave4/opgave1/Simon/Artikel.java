package afleveringsopgave4.opgave1.Simon;

public class Artikel {
	String[] Artikelfakta = new String[4];




	public void SetReferencelist( Artikel referencer){
		String r = referencer.toString();
		Artikelfakta[3] = r;
	}

	public Artikel(String f, String t, Tidsskrift ts){
		Artikelfakta[0] = f;
		Artikelfakta[1] = t;
		Artikelfakta[2]= ts.toString();
	}
	public String toString(){
		if(Artikelfakta[3] == null){
			Artikelfakta[3] = "No references";
		}
		return Artikelfakta[0]+ ", " + Artikelfakta[1] + ", "+Artikelfakta[2]+", "+ "References: " +Artikelfakta[3];  
	}
}
