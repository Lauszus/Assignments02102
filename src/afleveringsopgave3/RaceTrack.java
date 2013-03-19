package afleveringsopgave3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RaceTrack {
	final static int X = 0; // These are used to make the code more readable
	final static int Y = 1;
	final static int scale = 10; // Adjust this value to adjust the size of the course
	final static int size = 4*scale; // The final size of the field
	final static int topscore_length = 10; // Length of the high score list
	
	static List<Integer[]> lineCoordinates = new ArrayList<Integer[]>(); // List with all the lines between dots
	
	static int moves; // Counter for the number of moves used to finish the course	
	static int[] pos = new int[2]; // The current position
	static int[] oldPos = new int[2]; // The previous position
	static int[] distance = new int[2]; // Vector between old position and new position
	
	static String[] topNames = new String[topscore_length];
	static int[] topScores = new int[topscore_length];
	
	static boolean middleCrossed;
	static boolean clockwise;
	static boolean sideCrossed;
	static boolean goal;
	
	static double[] resetButton = new double[4]; // Coordinates to reset button
	
	public static void main(String[] args) throws FileNotFoundException{
		StdDraw.setCanvasSize(700, 700); // Adjust the size of the window
		StdDraw.setBorder(0.10); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, size); // Set size of the coordinate system
		
		init(); // Reset all values and draw course
		
		while(true) { // Endless while loop
			while(!StdDraw.mousePressed()); // Wait for button press
			int corX = (int)Math.round(StdDraw.mouseX()); // Read mouse position
			int corY = (int)Math.round(StdDraw.mouseY());
			while(StdDraw.mousePressed()); // Wait for release
			
			if(corX >= resetButton[0]-resetButton[2] && corX <= resetButton[0]+resetButton[2] && corY >= resetButton[1]-resetButton[3] && corY <= resetButton[1]+resetButton[3]) { // Check if user presses the reset button
				System.out.println("Reset pressed");
				init();
			} else if(!goal) { // Don't draw if the car is already behind the finish line
				for(Integer[] coordinates : getPossibleLocationsCoordinates(pos[X]+distance[X],pos[Y]+distance[Y])) { // Check if the mouse click is equal to any of the possible coordinates
					if(corX == coordinates[X] && corY == coordinates[Y]) { // This will be true if there is a match
						//System.out.println("X:" + corX + " Y:" + corY);
						moves++; // Increment counter
						
						pos[X] = corX; // Update the position
						pos[Y] = corY;
						
						if(!middleCrossed && pos[X] > size/4 && pos[X] < size/4*3 && pos[Y] <= size/4) {
							System.out.println("Middle crossed");
							middleCrossed = true;
							clockwise = oldPos[X]-pos[X] > 0; // This is used so the car can go in both directions						
						}
						else if(!sideCrossed && middleCrossed && pos[Y] >= size/4*2 && ((clockwise && pos[X] <= size/4) || (!clockwise && pos[X] >= size/4*3))) {
							System.out.println("Side crossed");
							sideCrossed = true;
						}
						else if(sideCrossed && (clockwise && pos[X] >= size/2 ||  !clockwise && pos[X] <= size/2) && pos[Y] >= size/4*3 && pos[Y] <= size) {
							System.out.println("Goal!");						
							goal = true;
						}
						
						StdDraw.show(0);
						drawRoute(size);
						drawButton(size);
						drawLines(pos,oldPos);
						printMoves();
						if(checkRoute(pos)) { // Check if position is inside the course
							distance[X] = pos[X] - oldPos[X];
							distance[Y] = pos[Y] - oldPos[Y];
						} else {
							System.out.println("Out of bounds!");
							distance[X] = 0;
							distance[Y] = 0;
						}
						oldPos = pos.clone();
						if(!goal) {
							drawPossibleLocations(pos,distance);
							StdDraw.show(0);
						} else {
							StdDraw.picture(size/2, size/2-1, "trophy.png", size/4*2-3, size/4*2-3);
							StdDraw.show(0);
							if (topScores[topscore_length-1] > moves)
								newTopscore();
						}
					}
				}
			}
		}
	}
	
	private static void init() throws FileNotFoundException{
		middleCrossed = false;
		clockwise = false;
		sideCrossed = false;
		goal = false;
		distance[0] = 0;
		distance[1] = 0;
		moves = 0;
		lineCoordinates.clear();
		
		for (int i=0; i<topscore_length;i++)
			topNames[i]="";
		
		// Loading high score file
		File inputFile = new File("topscore.txt");
		if(!inputFile.exists()){			
			PrintStream output = new PrintStream(new File("topscore.txt"));
			for (int i=0; i<topscore_length; i++)
				output.print(",999;"); // We will just write 999 as the default value, this will be ignored automatically
			output.close();
			loadFile();
		} else
			loadFile();
		
		// Draw starting point
		pos[X] = (int)(size/2.0); // Start in the middle of the x-axis
		pos[Y] = (int) Math.ceil((7.0*(double)size)/8.0); // Round up so it will always start at the top if there is uneven number of fields
		oldPos = pos.clone(); // Clone position
		
		StdDraw.show(0); // Don't show the drawing as the are drawn
		drawRoute(size); // Draw grid
		drawButton(size);
		drawDot(pos[X],pos[Y]); // Draw starting position
		drawPossibleLocations(pos,distance); // Draw possible location to select
		printMoves(); // Print number of moves
		StdDraw.show(0); // Show the updated window
	}
	
	private static void drawButton(int n) {
		resetButton[0] = n/15.0;
		resetButton[1] = n+n/25.0;
		resetButton[2] = n/15.0;
		resetButton[3] = n/35.0;
		
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.setPenRadius(9.0/((double)n*100.0));
		StdDraw.filledRectangle(resetButton[0], resetButton[1], resetButton[2], resetButton[3]);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.rectangle(resetButton[0], resetButton[1], resetButton[2], resetButton[3]);
		
		StdDraw.text(resetButton[0], resetButton[1], "Restart");
	}
	
	private static boolean checkRoute(int[] pos) {
		if(pos[X] < size/4.0*3.0 && pos[X] > size/4.0 && pos[Y] < size/4.0*3.0 && pos[Y] > size/4.0) // Check the center square			
			return false;
		else if(pos[X] < 0 || pos[Y] < 0 || pos[X] > size || pos[Y] > size) // Check outer square			
			return false;
		return true;
	}
	
	private static void printMoves() { // Print number of moves
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(2.0/((double)size*10.0));
		StdDraw.textLeft(size/4.0+size/100.0, size/4.0*3.0-size/40.0, "Moves: " + moves);
	}
	
	private static void drawPossibleLocations(int[] pos, int[] distance) { // Draw the possible locations
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius(2.0/((double)size*10.0));
		
		for(Integer[] coordinates : getPossibleLocationsCoordinates(pos[X]+distance[X],pos[Y]+distance[Y]))
			StdDraw.point(coordinates[X], coordinates[Y]);
	}
	
	private static List<Integer[]> getPossibleLocationsCoordinates(int x, int y) { // This list is used to store the possible coordinates the car can be move
		List<Integer[]> coordinates = new ArrayList<Integer[]>();
		coordinates.add(new Integer[] {x,y});
		coordinates.add(new Integer[] {x+1,y});
		coordinates.add(new Integer[] {x+1,y+1});
		coordinates.add(new Integer[] {x,y+1});
		coordinates.add(new Integer[] {x-1,y+1});
		coordinates.add(new Integer[] {x-1,y});
		coordinates.add(new Integer[] {x-1,y-1});
		coordinates.add(new Integer[] {x,y-1});
		coordinates.add(new Integer[] {x+1,y-1});
		
		return coordinates;
	}
	
	private static void drawDot(int x, int y) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(4.0/((double)size*10.0));
		StdDraw.point(x, y);
	}
	
	private static void drawLines(int[] pos, int[] oldPos) {
		lineCoordinates.add(new Integer[] {oldPos[X], oldPos[Y], pos[X], pos[Y]}); // Add coordinates to list
		
		for(Integer[] line : lineCoordinates) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(3.0/((double)size*10.0));
			StdDraw.line(line[0], line[1], line[2], line[3]); // Draw lines between dots
			drawDot(line[0],line[1]); // Draw previous dots		
		}
		drawDot(pos[X],pos[Y]); // Draw dot at current position
	}
	
	private static void drawRoute(int n) { // Draw the route for the car
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(9.0/((double)n*100.0));
		StdDraw.square(n/2.0, n/2.0, n/2.0);
		
		StdDraw.setPenRadius(4.0/((double)n*100.0));
		for(int i = 1; i < n; i++) {
			StdDraw.line(i, 0, i, n); // Vertical line
			StdDraw.line(0, i, n, i); // Horizontal line
		}
		
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setPenRadius(8.0/((double)n*100.0));
		StdDraw.filledSquare(n/2.0, n/2.0, n/4.0);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(n/2.0, n/2.0, n/4.0);
		
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(1.5/((double)n*10.0));
		StdDraw.line(n/2.0, (3.0*n)/4.0, n/2.0, n);
	}
	
	private static String dialogInput() {
		JFrame parent = new JFrame();
		String initials = JOptionPane.showInputDialog(parent,"New high score! Please write your initials:","A-Z or numbers",JOptionPane.OK_CANCEL_OPTION);
		if (initials == null)
			return "";
		
		initials = initials.replaceAll("[^(A-Z|a-z|0-9)]", ""); // Removes everything that is not A-Z, a-z or 0-9
		return initials;
	}
	
	private static void newTopscore() throws FileNotFoundException {
		String initials = "";
		while(initials.equals("")) // Re-show the window until the user types something 
			initials = dialogInput();
		int rank = 0;	
		for (int i=0; i<topscore_length;i++) {
			if (topScores[i] > moves) {
				rank = i;
				break;
			}
		}
		
		for (int j=topscore_length-1; j>rank; j--){
			topScores[j]=topScores[j-1];
			topNames[j]=topNames[j-1];
		}
		if (moves <= 999)
			topScores[rank] = moves;
		else
			topScores[rank] = 999;
		topNames[rank] = initials;
		
		writeFile();
	}
	
	private static void writeFile() throws FileNotFoundException {
		PrintStream output = new PrintStream(new File("topscore.txt"));
		String outputPane = "<html><font face=\"Courier New\" size=\"5\">";
		for (int i=0; i<topscore_length; i++) {
			output.print(topNames[i] + "," + topScores[i] + ";");				
			if(topScores[i] != 999) // Don't write if it's just the default value
				outputPane += (i+1) + ". "+topNames[i]+" - " + topScores[i]+"<br>";
		}
		output.close();
		
		outputPane+= "</html></font>";
		JOptionPane.showMessageDialog(null,outputPane,"HIGH SCORES",JOptionPane.PLAIN_MESSAGE);
	}
	
	private static void loadFile() throws FileNotFoundException {
		File inputFile = new File("topscore.txt");
		Scanner inputScanner = new Scanner(inputFile);
		String topscore = "";
		while (inputScanner.hasNextLine())
			topscore += inputScanner.nextLine();
		
		// Separating high score file in names and scores
		boolean name = true;
		int nameIndex =0;
		int scoreIndex =0;
		String tempScore = "";
		
		for (int i=0; i<topscore.length(); i++) {
			if (name) {
				if (topscore.charAt(i) != ',')
					topNames[nameIndex] += topscore.charAt(i);
				else {
					name = false;
					nameIndex++;
				}
			} else {
				if (topscore.charAt(i) != ';')
					tempScore += topscore.charAt(i);
				else {
					topScores[scoreIndex] = Integer.parseInt(tempScore);
					name = true;
					scoreIndex ++;
					tempScore="";
				}
			}
		}
		
		for (int i=0; i<topscore_length; i++)
			System.out.println((i+1)+". "+topNames[i]+" - "+topScores[i]);
	}
}