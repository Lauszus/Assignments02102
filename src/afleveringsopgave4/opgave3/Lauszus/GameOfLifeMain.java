package afleveringsopgave4.opgave3.Lauszus;

public class GameOfLifeMain {
	private static int[][] initialState = {
		{1,0,1},
		{0,1,1},
		{0,1,0},
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameOfLife gol = new GameOfLife(initialState);		
		for(int i = 0; i < 6; i++) {
			System.out.print("\n" + gol);
			gol.nextState();
		}
		/*
		GameOfLife gol = new GameOfLife(3);
		System.out.print(gol);
		System.out.println(gol.liveNeighbours(0,1));
		*/
	}
}
