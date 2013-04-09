package afleveringsopgave4.opgave1.Kristian;

public class Tidsskrift {
	String titel;
	String issn;
	Forlag forlag;

	public Tidsskrift(String titel) {
		this.titel = titel;
	}
	
	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}
}
