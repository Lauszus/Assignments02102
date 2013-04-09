package afleveringsopgave4.opgave1.Simon;

public class Forlag {	
	String navn;
	String sted;

	public Forlag(String n, String s){
		navn = n;
		sted = s;
	}
	public String toString(){
		return navn + ", "+ sted;
	}
}

