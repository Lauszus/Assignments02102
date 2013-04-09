package afleveringsopgave4.opgave1.Simon;
import java.util.Arrays;



public class Opg1 {


	public static void main(String[] args) {
		Forlag f = new Forlag("University Press", "Denmark");
		System.out.println(f.toString());
		
		Tidsskrift t1 = new Tidsskrift("Journal of Logic");
		t1.setIssn("Unknown");
		t1.setForlag(f);
		
		System.out.println(t1.toString());
		
		Tidsskrift t2 = new Tidsskrift("Brain");
		t2.setIssn("Unknown");
		t2.setForlag(f);
		
		System.out.println(t2.toString());
		
		Artikel A = new Artikel("A. Abe & A. Turing", "A", t1 );
		Artikel B = new Artikel("B. Bim", "Logic", t1);
		A.SetReferencelist(B);
		System.out.println(A.toString());
	
	}

}
