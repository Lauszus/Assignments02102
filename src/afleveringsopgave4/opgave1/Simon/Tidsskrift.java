package afleveringsopgave4.opgave1.Simon;

public class Tidsskrift {
	String titel;
	Forlag forlag;
	String issn;

	public void setIssn( String i ){
		issn = i;
	}
	public void setForlag( Forlag f){
		forlag = f;
	}
	public Tidsskrift(String t){
		titel = t;
	}
	public String toString(){
		return titel + ", "+ issn + ", " + forlag;
	}

}

