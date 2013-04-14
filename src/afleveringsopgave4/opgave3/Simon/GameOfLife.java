package afleveringsopgave4.opgave3.Simon;
import java.util.*;

public class GameOfLife {
	int m;
	int[][] life = new int[m][m];

	public GameOfLife(int n){
		m= n;
		Random r = new Random();

		for(int i= 0; i<n; i++){
			for(int j = 0; j<n; j++){
				life[i][j] = r.nextInt(2);
			}
		}
	}
	public GameOfLife(int[][] initialState){
		life= initialState;
	}
}
