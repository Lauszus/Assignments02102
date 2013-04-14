package afleveringsopgave4.opgave1.Lauszus;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the article class.
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */
public class Artikel {
	List<String> forfattere = new ArrayList<String>();
	String titel;
	Tidsskrift tidsskrift;
	List<Artikel> referenceliste = new ArrayList<Artikel>();
	
	/**
	 * Constructor for the article class.
	 * @param forfattere Name of the writers.
	 * @param titel Title of the article.
	 * @param tidsskrift The journal of the article.
	 */
	public Artikel(List<String> forfattere, String titel, Tidsskrift tidsskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidsskrift = tidsskrift;
	}
	
	/**
	 * Method to set the reference list.
	 * @param referenceliste A list of references. 
	 */
	public void setReferenceliste(List<Artikel> referenceliste) {
		this.referenceliste = referenceliste;
	}
	
	/**
	 * Used to print all the information of the article.
	 */
	public String toString() {
		String output = "";
		int size = forfattere.size();
		for(String forfatter : forfattere) {
			output += forfatter;
			size--;
			if(size == 1) // Put & between the two last writers
				output += " & ";
			else if(size > 1) // Put a comma between writers
				output += ", ";
		}
		output += ": \"" + titel + "\". ";
		output += tidsskrift;
		
		if(!referenceliste.isEmpty()) {
			output += "\nReference List: ";
			for(Artikel artikel : referenceliste) {
				output += artikel;
				output += " ";
			}
			output += "\n";
		}
		
		return output;
	}
}