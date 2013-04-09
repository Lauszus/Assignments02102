package afleveringsopgave4.opgave3.Lauszus;

import java.awt.Color;

public class GameOfLifeMain {
	private static int gridSize = 100;
	private static int[][] initialState = {
		{1,0,1},
		{0,1,1},
		{0,1,0},
	};
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		GameOfLife gol = new GameOfLife(initialState);
		gridSize = initialState[0].length;
		//GameOfLife gol = new GameOfLife(gridSize);		
		
		StdDraw.setCanvasSize(700, 700); // Adjust the size of the window
		StdDraw.setBorder(0.20); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, gridSize-1); // Set size of the coordinate system
		StdDraw.setPenRadius(1.0/(double)gridSize);
		StdDraw.setPenColor(Color.BLACK);
		
		for(int i = 1; i <= 100; i++) {
			StdDraw.show(0);
			StdDraw.clear();
			//StdDraw.textLeft(-1.5/(double)gridSize, gridSize+10.0/(double)gridSize, Integer.toString(i));
			
			for(int y = 0; y < gridSize; y++) {
				for(int x = 0; x < gridSize; x++) {
					if(gol.getState(x, y) == 1)
						StdDraw.point(x, y);
				}
			}			
			StdDraw.show(500); // Sleep for 500ms		
			gol.nextState();
		}
	}
}