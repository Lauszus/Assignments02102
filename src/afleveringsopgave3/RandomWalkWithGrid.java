package afleveringsopgave3;

import java.util.Random;
import java.util.Scanner;

public class RandomWalkWithGrid {
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();
	private static int steps;
	final static int x = 0;
	final static int y = 1;
	private final static double size = 1.0; // This is the size equal to the distance between two lines in the grid
	private final static boolean drawGrid = false;
	private final static boolean drawLines = false;
	
	public static void main(String[] args) {
		System.out.print("Enter size of grid: ");
		int n = scanner.nextInt();
		StdDraw.setScale(-n, n+1);
		if(drawGrid) {
			drawGrid(n);
			StdDraw.setPenRadius((0.9*0.2)/((double)n*2+1));
		}
		else
			StdDraw.setPenRadius((0.9*size)/((double)n*2+1));
		
		final int[] pos = {0,0};
		int[] oldPos = new int[2];
				
		while (true) {
			System.out.println("Position = (" + pos[x] + "," + pos[y]+ ")");
			if(Math.abs(pos[x]) > n || Math.abs(pos[y]) > n)
				break;
			if(!drawLines)
				StdDraw.point(pos[x]+0.5, pos[y]+0.5);
			steps++;
			switch (random.nextInt(4)) {
			case 0:
				pos[y]++; // Right				
				break;
			case 1:
				pos[x]++; // Up
				break;
			case 2:
				pos[y]--; // Left
				break;
			case 3:
				pos[x]--; // Down
				break;
			}			
			if(drawLines) {
				StdDraw.line(pos[x]+0.5, pos[y]+0.5, oldPos[x]+0.5, oldPos[y]+0.5);
				oldPos = pos.clone();
			}
		}
		System.out.println("\nTotal number of steps = " + steps);
	}
	
	private static void drawGrid(int n) {		
		StdDraw.setPenRadius(1.0/((double)n*1000.0));
		for(int i = 0; i <= n*2+1; i++) {
			StdDraw.line(-n+i, -n, -n+i, n+1); // Vertical line
			StdDraw.line(-n, -n+i, n+1, -n+i); // Horizontal line
		}
	}
}
