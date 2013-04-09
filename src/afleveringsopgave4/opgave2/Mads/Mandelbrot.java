package afleveringsopgave4.opgave2.Mads;

import java.util.Scanner;
import java.awt.*;
import java.io.*;

public class Mandelbrot {
	public static final int MAX = 255;
	public static final int COLORDEPTH=256;
	public static final int GRIDSIZE=768; //evt 768 eller 900 for større grid
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner console=new Scanner(System.in);
		System.out.print("Enter the real coordinate of the center: ");
		consoleInputCheck(console);
		double centrumRe = console.nextDouble();
		
		System.out.print("Enter the imaginary coordinate of the center: ");
		consoleInputCheck(console);
		double centrumIm = console.nextDouble();
		
		System.out.print("Enter the length of the sides: ");
		consoleInputCheck(console);
		double sidelength = console.nextDouble();
		
		console.nextLine();
		System.out.print("Enter the filename of the color-file: ");
		String filename= console.nextLine();	
		File inputFile = new File(filename);
		
		while(!inputFile.exists()){			
			System.out.print("Invalid filename! Try again: ");
			filename= console.nextLine();
			inputFile = new File(filename);
		}
		console.close();
		int[][] colors = loadColors(filename);
			
		Complex centrum = new Complex(centrumRe,centrumIm);
		
		Complex[][] complexGrid=createComplexGrid(centrum, sidelength); 
		
		
		
		int[][] drawingData = new int[GRIDSIZE][GRIDSIZE];
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				drawingData[i][j]=iterate(complexGrid[i][j]);
			}
		}
		
		drawMandelbrot(drawingData, colors);
		
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
	
	public static void consoleInputCheck(Scanner console){
		while(!console.hasNextDouble()){
			console.next();
			System.out.println("Invalid inut! Try again: ");
		}
	}
	
	public static Complex[][] createComplexGrid(Complex centrum, double sidelength){
		Complex[][] cGrid = new Complex[GRIDSIZE][GRIDSIZE];
		
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				double re = centrum.getRe()-(sidelength/2)+((sidelength*i)/(GRIDSIZE-1));
				double im = centrum.getIm()-(sidelength/2)+((sidelength*j)/(GRIDSIZE-1));
				cGrid[i][j]= new Complex(re,im);			
			}
		}
		return cGrid;
	}
	
	public static void drawMandelbrot(int drawingData[][], int colors[][]){
		StdDraw.setCanvasSize(GRIDSIZE,GRIDSIZE);
		StdDraw.setXscale(0,GRIDSIZE-1);
		StdDraw.setYscale(0,GRIDSIZE-1);
		StdDraw.setPenRadius(1.0/GRIDSIZE);
		StdDraw.show(0);
		
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				
				StdDraw.setPenColor(new Color(colors[drawingData[i][j]][0], colors[drawingData[i][j]][1], colors[drawingData[i][j]][2]));
				StdDraw.point(i, j);
			}
		}
		
		StdDraw.show(0);
		
	}
	
	public static int[][] loadColors(String filename) throws FileNotFoundException{
		int[][] colors = new int[COLORDEPTH][3];
			
		File colorFile = new File(filename);
		Scanner fileScanner = new Scanner(colorFile);
		
		for (int i=0; i<COLORDEPTH; i++){
			for (int j=0; j<3; j++){
				if (fileScanner.hasNextInt()){
					colors[i][j]=fileScanner.nextInt();
				}
			}
		}
		return colors;
	}
}
