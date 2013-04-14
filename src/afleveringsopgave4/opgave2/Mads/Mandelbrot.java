package afleveringsopgave4.opgave2.Mads;

import java.util.Scanner;
import java.awt.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mandelbrot {
	
	public static final int COLORDEPTH=256;
	public static final int GRIDSIZE=768; //512 evt 768 eller 900 for større grid
	public static final boolean CONSOLEINPUT=false;
	
	public static int MAX = 255; //255
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Forslag til forbedringer:
		//Starter op med -0.5+0i som centrum og bredde på 2 og color=blues.mnd
		//klik for centrer (eller zoom?)
		//"+" zoomer ind og "-" zoomer ud
		//"g" (Go) bringer en dialog frem hvor man kan skrive et koordinat centrum skal gå til
		//"i" (Iterations) bringer en dialogboks frem hvor den spørger om antal iterations
		//"j" (Julia) laver juliaset for centrum
		//"c" (Color) spørger om navnet på color-filen
		//"w" (Where? am I?) giver koordinaterne på centrum
		double centerRe;
		double centerIm;
		double sidelength;
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
		
		
		
		initializeCanvas();
		int[][] colors = loadColors(filename);
		
		Complex center = new Complex(centerRe,centerIm);
		Complex[][] complexGrid=createComplexGrid(center, sidelength);
		int[][] drawingData= getDrawingData(complexGrid);
		drawMandelbrot(drawingData, colors);
		
		//"+" zoomer ind og "-" zoomer ud
				//"g" (Go) bringer en dialog frem hvor man kan skrive et koordinat centrum skal gå til
				//"i" (Iterations) bringer en dialogboks frem hvor den spørger om antal iterations
				// ikke implementeret endnu "j" (Julia) laver juliaset for centrum
				//"c" (Color) spørger om navnet på color-filen
				//"w" (Where am I?) giver koordinaterne på centrum
		
	//	StdDraw.isKeyPressed('G') || StdDraw.isKeyPressed('I') || StdDraw.isKeyPressed('C')|| StdDraw.isKeyPressed('W')
		
		
		
		while(true) {
			char key = ' ';
			boolean mousepressed=false;
			while(!(StdDraw.mousePressed()|| StdDraw.hasNextKeyTyped() )); // Wait for button press
			if(StdDraw.mousePressed()){
				int corX = (int)Math.round(StdDraw.mouseX()); // Read mouse position
				int corY = (int)Math.round(StdDraw.mouseY());
				centerRe = complexGrid[corX][corY].getRe();
				centerIm = complexGrid[corX][corY].getIm();
				mousepressed=true;
			}
			
			if (StdDraw.hasNextKeyTyped()){
				key = Character.toLowerCase(StdDraw.nextKeyTyped());
			}
			while(StdDraw.mousePressed() || StdDraw.hasNextKeyTyped()); // Wait for release
			
			
			if (mousepressed){
				center = new Complex(centerRe,centerIm);
				complexGrid=createComplexGrid(center, sidelength);
				initializeCanvas();
				drawingData=getDrawingData(complexGrid);
				drawMandelbrot(drawingData, colors);
			}else if(key=='i'){
				int iterations=dialogInputIterations();
				if (iterations!=0)
					MAX=iterations;	
			}else if(key=='w'){
				JOptionPane.showMessageDialog(null,"Coordinates of the center is: \n"+complexGrid[GRIDSIZE/2][GRIDSIZE/2].toString(),"Center coordinates",JOptionPane.PLAIN_MESSAGE);
			}else if (key=='c'){
				colors=loadColors(dialogInputFilename());
				drawMandelbrot(drawingData, colors);
			}else if(key=='+'){
				sidelength /= Math.sqrt(2);
				center = new Complex(centerRe,centerIm);
				complexGrid=createComplexGrid(center, sidelength);
				initializeCanvas();
				drawingData= getDrawingData(complexGrid);
				drawMandelbrot(drawingData, colors);
			}else if(key=='-'){
				sidelength *= Math.sqrt(2);
				center = new Complex(centerRe,centerIm);
				complexGrid=createComplexGrid(center, sidelength);
				initializeCanvas();
				drawingData= getDrawingData(complexGrid);
				drawMandelbrot(drawingData, colors);
			}
		}
			
		
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
	
	public static double consoleInputCheck(Scanner console){
		while(!console.hasNextDouble()){
			console.next();
			System.out.println("Invalid inut! Try again: ");
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
	
	public static int[][] getDrawingData(Complex complexGrid[][]){
		
		int[][] drawingData = new int[GRIDSIZE][GRIDSIZE];
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				drawingData[i][j]=(int)(iterate(complexGrid[i][j])/((double)MAX/((double)COLORDEPTH-1)));
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
		String input= JOptionPane.showInputDialog(parent,"","Iterations",JOptionPane.OK_CANCEL_OPTION);
		input = input.replaceAll("[^(0-9)]", "");
		if (input == null)
			return 0;
		if (input=="")
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
