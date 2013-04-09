package afleveringsopgave4.opgave2.Lauszus;

import java.awt.Color;
import java.util.Scanner;

import afleveringsopgave3.StdDraw;

public class Mandelbrot {
	final static Scanner scanner = new Scanner(System.in);
	final static int g = 1000;
	final static int MAX = 255;
	private static double x0, y0, s;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Please enter center coordinates\nx0: ");
		x0 = scanner.nextDouble(); System.out.print("y0: ");
		y0 = scanner.nextDouble();
		
		System.out.print("Now enter side length: ");
		s = scanner.nextDouble();

		drawMandelbrot(false,true);
	}

	private static void drawMandelbrot(boolean drawPoints, boolean useColors) {
		StdDraw.setCanvasSize(700, 700); // Adjust the size of the window
		StdDraw.setBorder(0.15); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, g - 1); // Set size of the coordinate system
		
		StdDraw.show(0);

		for (int i = 0; i < g; i++) {
			for (int j = 0; j < g; j++) {
				int iterations = iterate(getComplexCoordinate(j, i));
				StdDraw.setPenRadius(1.2/(double)g);
				if(useColors) {
					int color;
					if(iterations < 5)
						color = (int)(50*iterations);
					else if(iterations < 10)
						color = (int)(25*iterations);
					else if(iterations < 100)
						color = (int)(2.5*iterations);
					else if(iterations < 150)
						color = (int)(1.5*iterations);
					else if(iterations < 200)
						color = (int)(1.25*iterations);
					else
						color = 255;
					
					StdDraw.setPenColor(new Color(color,255-color,color));
					StdDraw.point(j, i);
				} else if (iterations == MAX) {
					StdDraw.setPenColor(StdDraw.RED);						
					StdDraw.point(j, i);
				}
				if(drawPoints) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.setPenRadius(0.1/(double)g);
					StdDraw.point(j, i);
					//StdDraw.text(j, i+0.1, getComplexCoordinate(j, i).toString());					
				}
			}
		}
		
		StdDraw.show(0);
	}
	
	private static Complex getComplexCoordinate(double j, double k) {
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