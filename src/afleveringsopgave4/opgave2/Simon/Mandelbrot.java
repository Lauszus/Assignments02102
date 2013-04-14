package afleveringsopgave4.opgave2.Simon;
import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

import afleveringsopgave3.StdDraw;

public class Mandelbrot {
	final static Scanner console = new Scanner (System.in);
	final static int MAX = 255;
	final static int g = 1000;
	private static double x0, y0,s;

	public static void main(String[] args) {

		/*Scanner console = new Scanner (System.in);
		System.out.print("Please enter coordinates: ");
		x0 = console.nextDouble();
		y0 = console.nextDouble();
		System.out.print("Coordinates are: "+ x0+", " +y0 + ". Enter bordersize please: ");
		s = console.nextInt();
		System.out.println("Bordersize is "+s);*/
		x0 = 0.10684;
		y0 = -0.63675;
		s = 0.0085;
		
		//console.nextLine();
		//System.out.print("Enter the filename of the color-file (press enter to skip): ");
		//String filename = console.nextLine();
		
		String filename = "blues.mnd";
		
		drawMandelbrot(false, false, filename);

	}

	private static void drawMandelbrot(boolean drawPoints, boolean randomColour, String filename){
		StdDraw.setCanvasSize(512, 512); // Adjust the size of the window
		StdDraw.setBorder(0); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, g-1); // Set size of the coordinate system

		//StdDraw.setPenColor();
		//StdDraw.filledSquare(255, 255, 255);

		StdDraw.show(0);

		StdDraw.setPenRadius(1.4/g);
		
		int[][] colors = new int[MAX+1][3];
		if(drawPoints == false && randomColour==false && filename != null && filename != "") {
			try {
				colors = loadColors(filename);
			} catch (FileNotFoundException e) {
				System.out.println("Using default colors");
				filename = null;
			}
		}
		for( int i = 0; i< g; i++){
			for( int j= 0; j<g; j++){
				int paint = iterate(getCoordinates(i,j));

				if(drawPoints){
					if(paint == MAX){
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.point(i,j);
					}else{
						if(randomColour == true){
							if(paint == 255){
								StdDraw.setPenColor(new Color(255,0,0));

								StdDraw.point(i,j);
							}
							else if(paint < 25){
								StdDraw.setPenColor(new Color(0,0,paint*10));

								StdDraw.point(i,j);	
							}
							else if(1<=paint && paint < 50){
								StdDraw.setPenColor(new Color(0,paint*5,0));

								StdDraw.point(i,j);	
							}
							else if(50< paint && paint <= 100){
								StdDraw.setPenColor(new Color(0,paint*2,0));

								StdDraw.point(i,j);	
							}
							else if(100 < paint&& paint <= 150){
								StdDraw.setPenColor(new Color(0,paint,0));

								StdDraw.point(i,j);	
							}
							else if(150 < paint && paint <= 200){
								StdDraw.setPenColor(new Color(150,150,150));

								StdDraw.point(i,j);	
							}
							else{
								StdDraw.setPenColor(new Color(0,paint,0));

								StdDraw.point(i,j);

							}
						}
						

					}
				}
				else{
					StdDraw.setPenColor(new Color(colors[paint][0],colors[paint][1],colors[paint][2]));
					
					StdDraw.point(j, i);
				}
			}

		}
		StdDraw.show(0);
	}
	private static int[][] loadColors(String filename) throws FileNotFoundException {
		int[][] colors = new int[MAX+1][3];
		Scanner fileScanner = new Scanner(new File("src" + File.separator + "afleveringsopgave4" + File.separator + "Files" + File.separator + "mnd" + File.separator + filename));

		for (int j = 0; j <= MAX; j++) {
			for (int i = 0; i < 3; i++)
				colors[j][i] = fileScanner.nextInt();
		}
		return colors;
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
