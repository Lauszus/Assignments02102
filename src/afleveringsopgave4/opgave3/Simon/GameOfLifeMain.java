package afleveringsopgave4.opgave3.Simon;

import afleveringsopgave3.StdDraw;

public class GameOfLifeMain {
	final static int n = 100;

	public static void main(String[] args) {
		GameOfLife x = new GameOfLife(n);

		startGameOfLife(true, x);

	}
	public static void startGameOfLife(boolean drawPoints, GameOfLife game){
		StdDraw.setCanvasSize(512, 512); // Adjust the size of the window
		StdDraw.setBorder(0); // We added this function ourself in order to adjust the border size
		StdDraw.setPenRadius(1/n);

		int livecells = 0;
		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				if(game.toArray()[i][j]==1){
					StdDraw.point(i,j);
					game.toArray()[i][j] += livecells; 
				}
			}
		}
		
		//int[][] a=  nextState(game.toArray());
		for( int k = 1; k <500; k++  ){
			StdDraw.show(0);
			StdDraw.clear();
			int[] [] a = nextState(game.toArray());
		
			for(int i = 0; i<n; i++){
				for(int j = 0; j<n; j++){
					if(a[i][j]==1){
						StdDraw.point(i,j);
					}
				}
			}
			StdDraw.show(0);
			
		}
	}
	public static int[] [] nextState(int [] [] currentCells){

		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				int neighbourcells = 0;
				neighbourcells = currentCells[i+1][j] + currentCells[i+1][j+1]+ currentCells[i+1][j-1]+ currentCells[i][j-1]+ currentCells[i-1][j-1]+ currentCells[i-1][j]+ currentCells[i-1][j+1]+ currentCells[i][j+1];
				if(currentCells[i][j]==1){
					if(neighbourcells < 2 ){
						currentCells[i][j]=0;
					} else if(neighbourcells > 3){
						currentCells[i][j]=0;
					}else if(neighbourcells == 2 || neighbourcells == 3 ){

					}
				}
				else{ 
					if(neighbourcells == 3){
						currentCells[i][j]=1;
					}

				}

			}
		}
		return currentCells;
	}
}
