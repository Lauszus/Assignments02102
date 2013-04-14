package afleveringsopgave4.opgave3.Mads;


import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import opgave2_Mandelbrot.StdDraw;

public class GameOfLifeMain {
	
	private static int GRIDSIZE=50;
	private static final int ITERATIONS=200;
	private static final int WAIT=50;
	
	public static void main(String[] args) throws FileNotFoundException {
		String filename = "acorn.gol";
		
		GRIDSIZE=openMatrixfile(filename).length;
		
		System.out.println(GRIDSIZE);
		GameOfLife thisGame = new GameOfLife(openMatrixfile(filename));
		
		
		StdDraw.setXscale(0,GRIDSIZE-1);
		StdDraw.setYscale(0,GRIDSIZE-1);
		StdDraw.setPenRadius(1.0/GRIDSIZE);
		
		drawGameOfLife(thisGame);
		
		for (int i=1; i<=ITERATIONS; i++){
			drawGameOfLife(thisGame);
			thisGame = thisGame.nextState();
			
		}
		
	}

	public static void drawGameOfLife(GameOfLife thisGame){
		StdDraw.clear();
		for (int i=0; i<GRIDSIZE; i++){
			for (int j=0; j<GRIDSIZE; j++){
				
				if (thisGame.state(i,j)==1){
					StdDraw.point(i, j);
				}
				
			}
		}
		
		StdDraw.show(WAIT);
		
	}
	
	public static int[][] openMatrixfile(String filename) throws FileNotFoundException {
		
		File inputFile = new File("src" + File.separator + "gol" + File.separator + filename);
		
		if(!inputFile.exists()){			
			return null;			
		}
		Scanner lengthScanner = new Scanner(inputFile);
		int arrayLength =1;
		String linje = lengthScanner.nextLine();
		for (int i=0; i<linje.length(); i++){
			if (linje.charAt(i)==' '){
				arrayLength++;
			}
		}
		lengthScanner.close();
		
		Scanner fileScanner = new Scanner(inputFile);
		int[][] matrixContent = new int[arrayLength][arrayLength];
		
		for (int i=0; i<arrayLength; i++){
			for (int j=0; j<arrayLength; j++){
				matrixContent[i][j]=fileScanner.nextInt();
			}
		}
		
		fileScanner.close();
		
		return matrixContent;
	}
}
