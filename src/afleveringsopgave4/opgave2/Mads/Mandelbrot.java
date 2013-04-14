package afleveringsopgave4.opgave2.Mads;

import java.util.Scanner;
import java.awt.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the main Mandelbrot class. This will open a window and draw the Mandelbrot set. 
 * When running the program, press "h" for help
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */

public class Mandelbrot {
	
	public static final int COLORDEPTH=256;
	public static final int GRIDSIZE=768;
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
	
	
	/**
	 * This will be called at runtime. Depending on CONSOLEINPUT it will take input from the console or draw the Mandelbrot set with default values
	 * @param args This String array is not used.
	 * @throws FileNotFoundException called if the file is not found. Though it is tested if the file exists
	 */
	public static void main(String[] args) throws FileNotFoundException {	
		
		String filename="";
		
		if (CONSOLEINPUT){
			//This part is only to show that we have implemented console input.
			//The same functionality can be found by typing "g" on the canvas.
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
		
		//Entering endless while loop when the Mandelbrot set have been drawn the first time
		while(true) {
			char key = ' ';
			boolean mousepressed=false;
			while(!(StdDraw.mousePressed()|| StdDraw.hasNextKeyTyped() )); // Wait for mouse or keyboard press
			if(StdDraw.mousePressed()){
				int corX = (int)Math.round(StdDraw.mouseX()); // Read mouse position
				int corY = (int)Math.round(StdDraw.mouseY());
				
				if (corX >= 0 && corX < GRIDSIZE && corY >= 0 && corY < GRIDSIZE ){ 
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
					JOptionPane.showMessageDialog(null,"Input is not an integer.\n"+maxIterations+" iterations is used","Input is not an integer",JOptionPane.WARNING_MESSAGE);
				redrawAll(colors);

			}else if(key=='w'){ //Tells the user the complex coordinates of the center of the drawing panel
				JOptionPane.showMessageDialog(null,"Coordinates of the center is: \n"+complexGrid[GRIDSIZE/2][GRIDSIZE/2].toString(),"Center coordinates",JOptionPane.INFORMATION_MESSAGE);

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

			} else if(key=='g'){ //Prompts the user for a place and zoom-level to go to
				
				centerRe = dialogInputDouble("Enter the real coordinate of the center","New center"); 
				centerIm = dialogInputDouble("Enter the imaginary coordinate of the center","New center");
				sidelength = dialogInputDouble("Enter the sidelength","Sidelength");
				redrawAll(colors);

			} else if(key=='h'){ //Display help box
				JOptionPane.showMessageDialog(null,"Click mouse to set new center \nKeys: \n'i' - iterations - sets iterations\n'w' - where am I? - tells you the coordintes of the center\n'c' - colors - loads new color file \n'g' - go to - Enter coordinates and zoom level to go to\n'+' - zooms to double size\n'-' - zooms to half size \n'j' - draws the Julia set for the center \n'm' - draws the Mandelbrot set","Help",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	

	/**
	 * Recalculates everything and redraws the canvas
	 * @param colors the array containing the color values
	 */
	public static void redrawAll(int[][] colors){
		initializeCanvas();
		Complex center = new Complex(centerRe,centerIm);
		complexGrid = createComplexGrid(center, sidelength);
		drawingData = getDrawingData();
		drawMandelbrot(drawingData, colors);
	}
	
	/**
	 * Initializes the canvas. After first initilization it is only used to show the message "Please wait..."
	 */
	public static void initializeCanvas(){
		StdDraw.setBorder(0.001);
		StdDraw.setCanvasSize(GRIDSIZE,GRIDSIZE);
		StdDraw.setXscale(0,GRIDSIZE-1);
		StdDraw.setYscale(0,GRIDSIZE-1);
		StdDraw.setPenRadius(1.0/GRIDSIZE);
		StdDraw.text(GRIDSIZE/2,GRIDSIZE/2, "Please wait..."); //"Please wait..." is shown on the canvas until the data has been calculated and drawn
		StdDraw.show(0);
	}

	/**
	 * Creates an array of the complex numbers on the canvas
	 * @param centrum is used to make the complex grid
	 * @param sidelength is used to make the complex grid
	 */
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

	/**
	 * Creates an array of the iterations for the corresponding point by iterating the points
	 * @return returns an array of the number of iterations
	 */
	public static int[][] getDrawingData(){
		int[][] drawingData = new int[GRIDSIZE][GRIDSIZE];
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				drawingData[i][j]=(int)(iterate(complexGrid[i][j])/((double)maxIterations/((double)COLORDEPTH-1)));
			}
		}
		return drawingData;
	}

	/**
	 * Draws the Mandelbrot set (or Julia set)
	 * @param drawingData an array with numbers of iterations
	 * @param colors an array with the colors use to draw this
	 */
	public static void drawMandelbrot(int drawingData[][], int colors[][]){
		StdDraw.clear();
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				
				if(colors!=null){
					StdDraw.setPenColor(new Color(colors[drawingData[i][j]][0], colors[drawingData[i][j]][1], colors[drawingData[i][j]][2]));
				} else {
					StdDraw.setPenColor(new Color(drawingData[i][j], drawingData[i][j], drawingData[i][j])); //Equal amounts of R, G and B gives a grayscale
				}
				StdDraw.point(i, j);
			}
		}
		
		StdDraw.show(0);
	}


	/**
	 * Iterates the complex number passed to it. 
	 * The number can either be iterated by the Mandelbrot algorithm or the Julia algorithm
	 * @param z0 the complex number to iterate 
	 */
	public static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		if (mandelbrot){
			//Iterates the passed complex z after the formula z_next = z^2 +z0
			for (int i = 0; i < maxIterations; i++) {
				if (z.abs() > 2.0) {
					return i;
				}
				z = z.times(z).plus(z0);
			}
			return maxIterations;
			
		} else {
			//Iterates the passed complex z after the formula z_next = z^2 + c
			//c is the complex number for which the Julia set is found
			for (int i = 0; i < maxIterations; i++) {
				if (z.abs() > 2.0) {
					return i;
				}
				z = z.times(z).plus(juliaConstant);
			}
			return maxIterations;
		}
	}
	
	
	
	/**
	 * Prompts the user for a filename for the color file
	 */	
	private static String dialogInputFilename() {
		JFrame parent = new JFrame();
		String filename = JOptionPane.showInputDialog(parent,"Please write filename (in directory src" + File.separator + "mnd" + File.separator +" )","Filename",JOptionPane.OK_CANCEL_OPTION);
		if (filename == null)
			return "";
		return filename;
	}
	
	/**
	 * Prompts the user for a new value for iterations
	 * @return Returns an integer typed by the user. 
	 */	
	private static int dialogInputIterations() {
		JFrame parent = new JFrame();
		String input= JOptionPane.showInputDialog(parent,maxIterations+" iterations is currently used\nPlease type a new number of iterations","Iterations",JOptionPane.OK_CANCEL_OPTION);
		if (input == null)
			return 0;
		input = input.replaceAll("[^(0-9)]", ""); //Uses a regular expression to remove alle non-numerical characters from the input
		if (input.equals(""))
			return 0;
		return Integer.parseInt(input);
	}

	/**
	 * Prompts the user for a new value for iterations
	 * @param message What the user is told to type
	 * @param title The title of the inputdialog
	 * @return Returns a double typed by the user. 
	 */	
	private static double dialogInputDouble(String message, String title) {
		JFrame parent = new JFrame();
		String input= JOptionPane.showInputDialog(parent,message,title,JOptionPane.OK_CANCEL_OPTION);
		return doubleFromString(input);
	}
	
	/**
	 * Finds a double in a string if a such exists. If not, 0 is returned
	 * @param input String to find a double in
	 * @return Returns a double found in the string passed. Returns 0 if the input is invalid 
	 */	
	private static double doubleFromString(String input){
		if (input == null)
			return 0;
		input = input.replaceAll(",", "."); //Regex replacing ","" with the proper decimal point
		input = input.replaceAll("[^(0-9)][.|-]", ""); //Regex removing everything that can not be parsed to a double
		if (input.equals(""))
			return 0;
		double output;
		try{
			output=Double.parseDouble(input); 
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Invalid input. 0.0 is used.","Not a valid number!",JOptionPane.WARNING_MESSAGE);
			return 0; //Returning 0 if the string can not be parsed to a double (eg. it has more than one decimal point)
		}
		return output;
	}
	

	/**
	 * Used to open a file containing color data
	 * @param filename The name of the file you want to open located in the "src/mnd/" directory.
	 * @return Return a COLORDEPTH x 3 dimensional array with the color values
	 * @throws FileNotFoundException throws this error in case the file doesn't exist.
	 */
	public static int[][] loadColors(String filename) throws FileNotFoundException{
		int[][] colors = new int[COLORDEPTH][3];
			
		if (filename.equals("")){
			return null;
		}
		
		File colorFile = new File("src" + File.separator + "mnd" + File.separator + filename);
		
		if(!colorFile.exists()){	
			System.out.print("Invalid filename! Mandelbrot is drawn without colors");
			JOptionPane.showMessageDialog(null,"Invalid filename! Mandelbrot is drawn without colors","No such file!",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		Scanner fileScanner = new Scanner(colorFile);
		//The following code assumes a properly formatted color file
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


	/**
	 * Checks if the console input is a double
	 * @param console the scanner-object to check for a double
	 */
	public static double consoleInputCheck(Scanner console){
		while(!console.hasNextDouble()){
			console.next();
			System.out.println("Invalid input! Try again: ");
		}
		return console.nextDouble();
	}

}
