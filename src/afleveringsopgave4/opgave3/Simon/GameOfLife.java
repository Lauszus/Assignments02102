package afleveringsopgave4.opgave3.Simon;
import java.util.*;

public class GameOfLife {
	int[][] life;

	public GameOfLife(int n){
		life = new int[n][n];
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
	public int[][] toArray(){
			return life;
		}
		
	}
	

