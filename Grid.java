package Project2;

// https://youtu.be/tN7x1D3Eigc
import java.util.Random;

public class Grid {

	private boolean[][] bombGrid;
	private int[][] countGrid;	
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}

	public Grid(int rows, int columns) {
		numBombs = 25;
		if((rows*columns) > numBombs) {
		numRows = rows;
		numColumns = columns;
		}
		else {
		numRows = 10;
		numColumns = 10;
		}
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns, int bombs) {
		if((rows*columns) > bombs) {
		numRows = rows;
		numColumns = columns;
		numBombs = bombs;
		}
		else {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		}
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean [][] getBombGrid(){
		boolean [][] bombGridCopy =  new boolean[numRows][numColumns];
		for(int i=0; i< bombGrid.length; i++) {
			for(int j=0; j<bombGrid[i].length; j++) {
				bombGridCopy[i][j] = bombGrid[i][j];
			}
		}
		return bombGridCopy;
	}
	public int[][] getCountGrid(){
		int [][] CountGridCopy =  new int[numRows][numColumns];
		for(int i=0; i< countGrid.length; i++) {
			for(int j=0; j<countGrid[i].length; j++) {
				CountGridCopy[i][j] = countGrid[i][j];
			}
		}
		return CountGridCopy;
	}
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}	
	
	private void createCountGrid() {
		int count=0;
		for(int row=0; row< countGrid.length; row++) {
			for(int col=0; col<countGrid[row].length; col++) {
				for(int i=-1; i < 2; i++) {
					for(int j=-1; j < 2; j++) {
						if(isValid(row+i,col+j)) {
							if(bombGrid[row+i][col+j] == true) {
							count++;
							}
							}
						}	
					}
				countGrid[row][col] = count;
				count=0;
			}
		}
	}

	private void createBombGrid() {
		int i,j,l=0;
		boolean[] list = new boolean[numRows*numColumns];
		for( i=0; i < numBombs; i++) {
			list[i] = true;
		}
		shuffle(list);
		for( i=0; i < bombGrid.length; i++) {
			for(j=0; j<bombGrid[i].length; j++ ) {
				bombGrid[i][j] = list[l++];
			}
		}
	}
	
	private void shuffle(boolean[] arr) {
		int index;
		boolean temp;
		Random rand = new Random();
		for(int i = arr.length-1; i>0 ;i--) {
			index = rand.nextInt(i+1);
			temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
		}
	}
	private boolean isValid(int a, int b) {
		if((a >= 0) && (a < numRows)) {
			if((b >= 0) && (b < numColumns)) {
				return true;
			}
		}
			return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
