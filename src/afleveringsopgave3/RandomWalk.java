package afleveringsopgave3;

import java.util.Random;
import java.util.Scanner;

public class RandomWalk {
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();
	private static int steps;
	final static int x = 0;
	final static int y = 1;
	private final static double size = 1.0; // This is the size equal to the distance between two lines in the grid
	
	public static void main(String[] args) {
		System.out.print("Enter size of grid: ");
		int n = scanner.nextInt();
		StdDraw.setScale(-n, n);
		StdDraw.setPenRadius((0.9*size)/((double)n*2+1));
		
		final int[] pos = {0,0};
				
		while (true) {
			System.out.println("Position = (" + pos[x] + "," + pos[y]+ ")");
			if(Math.abs(pos[x]) > n || Math.abs(pos[y]) > n)
				break;
			StdDraw.point(pos[x], pos[y]);
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
		}
		System.out.println("\nTotal number of steps = " + steps);
	}
}
