package afleveringsopgave4.opgave2.Simon;
import java.util.*;

public class Mandelbrot {
	final static int MAX = 30;
	final static int g = 5;
	private static double x0, y0, s;

	public static void main(String[] args) {

		Scanner console = new Scanner (System.in);
		System.out.print("Please enter coordinates: ");
		x0 = console.nextDouble();
		y0 = console.nextDouble();
		System.out.print("Coordinates are: "+ x0+", " +y0 + ". Enter bordersize please: ");
		s = console.nextDouble();
		System.out.println("Bordersize is "+s);
	}
	
	public static Complex getCoordinates(double j, double k){
		return new Complex(x0 - s / 2.0 + (s * j) / (g - 1.0), y0 - s / 2.0 + (s * k) / (g - 1.0));
	}
	public static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		for (int i = 0; i < MAX; i++) {
		if (z.abs() > 2.0) {
		return i;
		}
		z = z.times(z).plus(z0);
		}
		return MAX;
}
}
