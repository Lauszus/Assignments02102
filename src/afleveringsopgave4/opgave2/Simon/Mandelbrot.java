package afleveringsopgave4.opgave2.Simon;
import java.util.*;
import java.awt.Color;

import afleveringsopgave3.StdDraw;

public class Mandelbrot {
	final static int MAX = 50;
	final static int g = 512;
	private static double x0, y0,s;

	public static void main(String[] args) {

		/*Scanner console = new Scanner (System.in);
		System.out.print("Please enter coordinates: ");
		x0 = console.nextDouble();
		y0 = console.nextDouble();
		System.out.print("Coordinates are: "+ x0+", " +y0 + ". Enter bordersize please: ");
		s = console.nextInt();
		System.out.println("Bordersize is "+s);*/
		x0 = 0.10259;
		y0 = -0.641;
		s = 0.0086;

		drawMandelbrot(true, true);

	}


	private static void drawMandelbrot(boolean drawPoints, boolean randomColour){
		StdDraw.setCanvasSize(700, 700); // Adjust the size of the window
		StdDraw.setBorder(0); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, g-1); // Set size of the coordinate system
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		StdDraw.show(0);

		StdDraw.setPenRadius(1.2/g);

		int[] colorNumber = new int[256];
		for(int i = 0; i<256; i++){
			colorNumber[i] = i;
		}

		for( int i = 0; i< g; i++){
			for( int j= 0; j<g; j++){
				int paint = iterate(getCoordinates(i,j));
				if(drawPoints){
					if(paint == MAX){
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.point(i,j);
					}
					if(paint != MAX){	
						if(randomColour == true){
							StdDraw.setPenColor(new Color(paint,2,100));
							//StdDraw.setPenColor(new Color(colorNumber[i],0,0));
							StdDraw.point(i,j);
						} 
						else {
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.point(i,j);

						}


					}
				}
			}
		}

		StdDraw.show(0);
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
