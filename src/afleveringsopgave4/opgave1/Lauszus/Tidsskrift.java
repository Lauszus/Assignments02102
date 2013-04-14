package afleveringsopgave4.opgave1.Lauszus;

/**
 * The journal class.
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */
public class Tidsskrift {
	String titel;
	String issn;
	Forlag forlag;

	/**
	 * Constructor for the journal class.
	 * @param titel Title of the journal.
	 */
	public Tidsskrift(String titel) {
		this.titel = titel;
	}
	
	/**
	 * Used to set the ISSN number of the journal.
	 * @param issn The ISSN number.
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	/**
	 * Used to set the publisher.
	 * @param forlag The publisher of the journal.
	 */
	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}
	
	/**
	 * Used to print the information of the journal.
	 */
	public String toString() {
		String output = titel + ". ";
		
		if(issn != null)
			output += "ISSN: " + issn + ". ";
		
		if(forlag != null)
			output += forlag + ".";
		
		return output;
	}
}