package afleveringsopgave4.opgave1.Lauszus;

import java.util.ArrayList;
import java.util.List;

public class Artikel {
	List<String> forfattere = new ArrayList<String>();
	String titel;
	Tidsskrift tidsskrift;
	List<Artikel> referenceliste = new ArrayList<Artikel>();

	public Artikel(List<String> forfattere, String titel, Tidsskrift tidsskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidsskrift = tidsskrift;
	}
	
	public void setReferenceliste(List<Artikel> referenceliste) {
		this.referenceliste = referenceliste;
	}
	
	public void printArtikel() {		
		int size = forfattere.size();
		for(String forfatter : forfattere) {
			System.out.print(forfatter);
			if(size-- > 1)
				System.out.print(" & ");
		}
		System.out.print(": \"" + titel + "\". ");
		System.out.print(tidsskrift.titel + ". ");
		
		if(tidsskrift.issn != null)
			System.out.print("ISSN: " + tidsskrift.issn + ". ");
		
		if(tidsskrift.forlag != null)
			System.out.print(tidsskrift.forlag.navn + ", " + tidsskrift.forlag.sted + ".");
		
		if(!referenceliste.isEmpty()) {
			System.out.print("\nReferenceliste: ");
			for(Artikel artikel : referenceliste) {
				artikel.printArtikel();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
}
