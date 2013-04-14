package opgave2_Mandelbrot;

import java.util.Scanner;
import java.awt.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mandelbrot {
	
	public static final int COLORDEPTH=256;
	public static final int GRIDSIZE=768; //512 evt 768 eller 900 for større grid
	public static final boolean CONSOLEINPUT=false;
	
	private static double centerRe;
	private static double centerIm;
	private static double sidelength;
	
	private static double centerReMandel;
	private static double centerImMandel;
	private static double sidelengthMandel;
	
	private static boolean mandelbrot = true;
	private static Complex juliaConstant;
	
	private static int[][] drawingData;
	private static int maxIterations = 255;
	private static Complex[][] complexGrid;
	
	public static void main(String[] args) throws FileNotFoundException {
				
		//Forslag til forbedringer:
		
		//Spørg brugeren om de vil starte op med konsol?
		//"g" (GoTo)
		
		//Starter op med -0.5+0i som centrum og bredde på 2 og color=blues.mnd
		//klik for centrer (eller zoom?)
		//"+" zoomer ind og "-" zoomer ud
		//"i" (Iterations) bringer en dialogboks frem hvor den spørger om antal iterations
		//"j" (Julia) laver juliaset for centrum
		//"c" (Color) spørger om navnet på color-filen
		//"w" (Where? am I?) giver koordinaterne på centrum
		
		
		
		
		String filename="";
		
		if (CONSOLEINPUT){
			Scanner console=new Scanner(System.in);
			System.out.print("Enter the real coordinate of the center: ");
			centerRe = consoleInputCheck(console);
			
			System.out.print("Enter the imaginary coordinate of the center: ");
			centerIm = consoleInputCheck(console);
			
			System.out.print("Enter the length of the sides: ");
			sidelength = consoleInputCheck(console);
			
			console.nextLine();
			System.out.print("Enter the filename of the color-file (press enter to skip): ");
			filename= console.nextLine();	
			console.close();
		}else {
			centerRe =-0.5;
			centerIm=0;
			sidelength=2;
			
		}
		centerReMandel = centerRe;
		centerImMandel =  centerIm;
		sidelengthMandel = sidelength;
		
		
		
		int[][] colors = loadColors(filename);
		redrawAll(colors);
		
		
		while(true) {
			char key = ' ';
			boolean mousepressed=false;
			while(!(StdDraw.mousePressed()|| StdDraw.hasNextKeyTyped() )); // Wait for button press
			if(StdDraw.mousePressed()){
				int corX = (int)Math.round(StdDraw.mouseX()); // Read mouse position
				int corY = (int)Math.round(StdDraw.mouseY());
				
				if (corX >= 0 && corX < GRIDSIZE && corY >= 0 && corY < GRIDSIZE ){
					System.out.println(corX);
					System.out.println(corY);
					centerRe = complexGrid[corX][corY].getRe();
					centerIm = complexGrid[corX][corY].getIm();
					mousepressed=true;
				}
				
				
				
				
				
			}
			
			if (StdDraw.hasNextKeyTyped()){
				key = Character.toLowerCase(StdDraw.nextKeyTyped());
			}
			while(StdDraw.mousePressed() || StdDraw.hasNextKeyTyped()); // Wait for release
			
			
			if (mousepressed){ //Sets a new center
				redrawAll(colors); 
			}else if(key=='i'){ //Prompts the user for a new number of iterations
				int iterations=dialogInputIterations();
				if (iterations!=0)
					maxIterations=iterations;
				else
					JOptionPane.showMessageDialog(null,"Input is not an integer.\n"+maxIterations+" iterations is used","Input is not an integer",JOptionPane.PLAIN_MESSAGE);
				redrawAll(colors);
			}else if(key=='w'){ //Tells the user the complex coordinates of the center of the drawing panel
				JOptionPane.showMessageDialog(null,"Coordinates of the center is: \n"+complexGrid[GRIDSIZE/2][GRIDSIZE/2].toString(),"Center coordinates",JOptionPane.PLAIN_MESSAGE);
			}else if (key=='c'){ //Promts the user for a new name for a color file
				colors=loadColors(dialogInputFilename());
				drawMandelbrot(drawingData, colors);
			}else if(key=='+'){ //Zooms to double the current size
				sidelength /= Math.sqrt(2);	
				redrawAll(colors);
			}else if(key=='-'){ //Zooms to half the current size
				sidelength *= Math.sqrt(2);
				redrawAll(colors);
				
			}else if(key=='j'){ //Draws the Juliaset corresponding to the coordinates of the center
				if (mandelbrot){
					juliaConstant = new Complex(centerRe , centerIm);
					sidelengthMandel = sidelength;
					centerReMandel = centerRe;
					centerImMandel = centerIm;
					mandelbrot = false;
					
					sidelength = 4;
					centerRe = 0;
					centerIm = 0;
					redrawAll(colors);
				}
				
				
			} else if(key=='m'){ //Draws the Mandelbrot set
				if(!mandelbrot){
					sidelength = sidelengthMandel;
					centerRe = centerReMandel;
					centerIm = centerImMandel;
					mandelbrot = true;
					redrawAll(colors);
				}
			}else if(key=='h'){ //Draws the Mandelbrot set
				JOptionPane.showMessageDialog(null,"Click mouse to set new center \nKeys: \n'i' - iterations - sets iterations\n'w' - where am I? - tells you the coordintes of the center\n'c' - colors - loads new color file \n'+' - zooms to double size\n'-' - zooms to half size \n'j' - draws the Julia set for the center \n'm' - draws the Mandelbrot set","Help",JOptionPane.PLAIN_MESSAGE);
			}
				
		}
			
		
	}
	
	
	public static void redrawAll(int[][] colors){
		initializeCanvas();
		Complex center = new Complex(centerRe,centerIm);
		complexGrid = createComplexGrid(center, sidelength);
		drawingData = getDrawingData();
		drawMandelbrot(drawingData, colors);
	}
	
	public static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		if (mandelbrot){
			//Iterates the passed complex, z, after the formula z_next = z^2 +z0
			for (int i = 0; i < maxIterations; i++) {
				if (z.abs() > 2.0) {
					return i;
				}
				z = z.times(z).plus(z0);
			}
			return maxIterations;
			
		} else {
			//Iterates the passed complex, z, after the formula z_next = z^2 + c
			for (int i = 0; i < maxIterations; i++) {
				if (z.abs() > 2.0) {
					return i;
				}
				z = z.times(z).plus(juliaConstant);
			}
			return maxIterations;
		}
		
	}
	
	public static double consoleInputCheck(Scanner console){
		while(!console.hasNextDouble()){
			console.next();
			System.out.println("Invalid input! Try again: ");
		}
		return console.nextDouble();
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
	
	public static int[][] getDrawingData(){ //Complex complexGrid[][]
		
		int[][] drawingData = new int[GRIDSIZE][GRIDSIZE];
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				drawingData[i][j]=(int)(iterate(complexGrid[i][j])/((double)maxIterations/((double)COLORDEPTH-1)));
			}
		}
		return drawingData;
	}
	public static void initializeCanvas(){
		//StdDraw.setBorder(0);
		StdDraw.setCanvasSize(GRIDSIZE,GRIDSIZE);
		StdDraw.setXscale(0,GRIDSIZE-1);
		StdDraw.setYscale(0,GRIDSIZE-1);
		StdDraw.setPenRadius(1.0/GRIDSIZE);
		StdDraw.text(GRIDSIZE/2,GRIDSIZE/2, "Please wait...");
		StdDraw.show(0);
	}
	
	public static void drawMandelbrot(int drawingData[][], int colors[][]){
		
		StdDraw.clear();
		
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				
				if(colors!=null){
					
					StdDraw.setPenColor(new Color(colors[drawingData[i][j]][0], colors[drawingData[i][j]][1], colors[drawingData[i][j]][2]));
				} else {
					StdDraw.setPenColor(new Color(drawingData[i][j], drawingData[i][j], drawingData[i][j]));
				}
				StdDraw.point(i, j);
			}
		}
		
		StdDraw.show(0);
		
	}
	
	private static String dialogInputFilename() {
		JFrame parent = new JFrame();
		String filename = JOptionPane.showInputDialog(parent,"Please write filename (in directory src" + File.separator + "mnd" + File.separator +" )","Filename",JOptionPane.OK_CANCEL_OPTION);
		
		if (filename == null)
			return "";
		return filename;
	}
	
	private static int dialogInputIterations() {
		JFrame parent = new JFrame();
		String input= JOptionPane.showInputDialog(parent,maxIterations+" iterations is currently used\nPlease type a new number of iterations","Iterations",JOptionPane.OK_CANCEL_OPTION);
		input = input.replaceAll("[^(0-9)]", "");
		if (input.equals(null))
			return 0;
		if (input.equals(""))
			return 0;
		return Integer.parseInt(input);
	}
		
	
	public static int[][] loadColors(String filename) throws FileNotFoundException{
		int[][] colors = new int[COLORDEPTH][3];
			
		if (filename.equals("")){
			return null;
		}
		
		File colorFile = new File("src" + File.separator + "mnd" + File.separator + filename);
		
		if(!colorFile.exists()){	
			System.out.print("Invalid filename! Mandelbrot is drawn without colors");
			JOptionPane.showMessageDialog(null,"Invalid filename! Mandelbrot is drawn without colors","No such file!",JOptionPane.PLAIN_MESSAGE);
			return null;
		}
		
		Scanner fileScanner = new Scanner(colorFile);
		
		for (int i=0; i<COLORDEPTH; i++){
			for (int j=0; j<3; j++){
				if (fileScanner.hasNextInt()){
					colors[i][j]=fileScanner.nextInt();
				}
			}
		}
		fileScanner.close();
		
		return colors;
	}
}
