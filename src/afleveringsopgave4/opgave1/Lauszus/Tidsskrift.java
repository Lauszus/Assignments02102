package afleveringsopgave4.opgave1.Lauszus;

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
	
	public String toString() {
		String output = titel + ". ";
		
		if(issn != null)
			output += "ISSN: " + issn + ". ";
		
		if(forlag != null)
			output += forlag + ".";
		
		return output;
	}
}
