package afleveringsopgave4.opgave3.Mads;

public class GameOfLife {
	private int[][] state;
	
	public GameOfLife(int n){
		this.state = new int[n][n];
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				this.state[i][j]=(int) Math.round(Math.random());
			}
		}
	}
	
	public GameOfLife(int[][] initialState){
		this.state = new int[initialState.length][initialState.length];
		this.state = initialState;
	}
	
	public int state(int x, int y){
		return this.state[x][y];
	}
	
	public GameOfLife nextState(){
		int [][] nextState = new int[this.state.length][this.state[0].length]; //Går ud fra at den er kvadratisk
		int liveNeighbours;
		
		for (int i=0; i<this.state.length; i++){
			for (int j=0; j<this.state[i].length; j++){
				liveNeighbours = liveNeighbours(i,j);
				
				if (this.state[i][j] == 0){
					if(liveNeighbours==3){
						nextState[i][j]=1;
					}
				}else if(this.state[i][j] == 1){
					if(liveNeighbours==2 || liveNeighbours==3){
						nextState[i][j]=1;
					}else {
						nextState[i][j]=0;
					}
				}
			}
		}
		
		return new GameOfLife(nextState);
	}
	
	private int liveNeighbours(int x, int y){
		int liveNeighbours=0-this.state[x][y];
		
		for (int i=x-1; i<=x+1; i++){
			for (int j=y-1; j<=y+1; j++){
				if (i>=0 && j>=0 && i<this.state.length && j < this.state[i].length){
					if (this.state[i][j]==1){
						liveNeighbours++;
					}
				}
				
			}
		}
		return liveNeighbours;
		
	}
}
