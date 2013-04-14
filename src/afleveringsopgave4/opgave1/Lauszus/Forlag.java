package afleveringsopgave4.opgave1.Lauszus;

/**
 * This is the publisher class.
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */
public class Forlag {
	String navn;
	String sted;
	
	/**
	 * Constructor for the class.
	 * @param navn Name of the publisher.
	 * @param sted Location of the publisher.
	 */
	public Forlag(String navn, String sted) {
		this.navn = navn;
		this.sted = sted;
	}
	
	/**
	 * Method to print the publisher.
	 */
	public String toString() {
		return (navn + ", " + sted);
	}
}