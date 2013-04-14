package afleveringsopgave4.opgave1.Lauszus;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to test the Article, Journal and Publisher class.
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */
public class ArtikelTest {

	/**
	 * Main method.
	 * @param args This argument is not used.
	 */
	public static void main(String[] args) {
		Forlag forlag = new Forlag("University Press", "Denmark"); // Create a new publisher
		
		Tidsskrift journal = new Tidsskrift("Journal of Logic"); // Create two new journal instances
		Tidsskrift brain = new Tidsskrift("Brain");
		
		journal.setForlag(forlag); // Set the publisher of the journals
		brain.setForlag(forlag);
		
		List<String> forfattereA = new ArrayList<String>(); // Create a list with the writers of the first article
		forfattereA.add("Abe");
		forfattereA.add("A. Turing");
		Artikel A = new Artikel(forfattereA, "A", journal); // Create a new article instance
		List<String> forfattereB = new ArrayList<String>(); // Create a list with the writers of the second article
		forfattereB.add("Bim");
		Artikel B = new Artikel(forfattereB, "B", brain); // Create a new article instance
		
		List<Artikel> referenceliste = new ArrayList<Artikel>(); // Set a reference list
		referenceliste.add(B); // Add B to the reference list
		A.setReferenceliste(referenceliste);
		
		System.out.println(A); // Print information about the two articles
		System.out.println(B);
	}
}