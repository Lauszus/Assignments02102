package afleveringsopgave4.opgave3.Lauszus;

import java.util.Random;

public class GameOfLife {
	private static Random random = new Random();
	private static int[][] state;	
	private static int gridSize;
	
	
	public GameOfLife(int n) {
		gridSize = n;
		state = new int[n][n];
		for(int y = 0; y < gridSize; y++) {
			for(int x = 0; x < gridSize; x++)
				state[y][x] = random.nextInt(2); // Returns a value from 0-1
		}
	}
	
	public GameOfLife(int[][] initialState) {
		gridSize = initialState[0].length; // We will assume that it's symmetrical 
		state = initialState.clone();
	}
	
	public void nextState() {
		int[][] newState = new int[gridSize][gridSize];
		for(int y = 0; y < gridSize; y++) {
			for(int x = 0; x < gridSize; x++) {
				int liveNeighbours = liveNeighbours(x,y);
				if(liveNeighbours < 2 || liveNeighbours > 3)
					newState[y][x] = 0;
				else if(liveNeighbours == 3)
					newState[y][x] = 1;
				else
					newState[y][x] = getState(x, y);
			}
		}
		state = newState.clone();
	}
	
	private int getState(int x, int y) {
		return state[y][x];		
	}
	
	public int liveNeighbours(int x, int y) {
		int liveNeighbours = 0;
		
		for(int i = -1; i <= 1; i++) {
			int corY = y+i;
			for(int j = -1; j <= 1; j++) {
				int corX = x+j;
				
				if(corX >= 0 && corY >= 0 && corX < gridSize && corY < gridSize && (corX != x || corY != y)) {
					if(getState(corX,corY) == 1)
						liveNeighbours++;
				}
			}
		}
		return liveNeighbours;
	}
	
	public String toString() {
		String output = "";
		for(int y = 0; y < gridSize; y++) {
			for(int x = 0; x < gridSize; x++) {
				output += getState(x,y) + " ";
			}
			output += "\n";
		}
		return output;
	}
}