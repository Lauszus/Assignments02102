package afleveringsopgave4.opgave1;

import java.util.ArrayList;
import java.util.List;

public class ArtikelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Forlag forlag = new Forlag("University Press", "Denmark");
		
		Tidsskrift journal = new Tidsskrift("Journal of Logic");
		Tidsskrift brain = new Tidsskrift("Brain");
		
		journal.setForlag(forlag);
		brain.setForlag(forlag);
		
		List<String> forfattereA = new ArrayList<String>();
		forfattereA.add("Abe");
		forfattereA.add("A. Turing");
		Artikel A = new Artikel(forfattereA, "A", journal);
		List<String> forfattereB = new ArrayList<String>();
		forfattereB.add("Bim");
		//Artikel B = new Artikel(forfattereB, "B", journal);
		Artikel B = new Artikel(forfattereB, "B", brain);
		
		List<Artikel> referenceliste = new ArrayList<Artikel>();
		referenceliste.add(B);
		A.setReferenceliste(referenceliste);
		
		A.printArtikel();
		System.out.println();
		B.printArtikel();
	}
}
