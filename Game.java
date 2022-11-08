package main;
import java.util.Random;

public class Game {
	private static final int SIZE = 4;
	private int gameBoard[][];
	private boolean checkOver;
	
	public Game() {
		gameBoard = new int[SIZE][SIZE];
		checkOver = false;
		generate();
		generate();
	}
	
	public int getSize() {
		return SIZE;
	}
	public int[][] getGameBoard(){
		return gameBoard;
	}
	
	public int getScore() {
		int score = 0;
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				score += gameBoard[i][j];
			}
		}
		
		return score;
	}
	
	public String toString(int n) {
		if (n == 0) return "0";
		String tempStr = "";
		int temp = 0;
		for (char tempChar = '0'; n > 0; n /= 10) {
			temp = n % 10;
			tempChar += temp;
			tempStr = tempStr + tempChar;
		};
		return tempStr;
	}
	public void printBoard(){
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				System.out.print(gameBoard[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean isFull() {
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				if(gameBoard[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean getGO() {
		return checkOver;
	}
	
	public boolean getVic() {
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				if(gameBoard[i][j] == 2048) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void generate() {
		if (this.isFull()) {
			checkOver = true;
			return;
		}
		Random rand = new Random();
		boolean cond = false;
		int tempVal = (rand.nextInt(2) + 1) * 2;
		while(cond == false) {
			int tempx = rand.nextInt(SIZE);
			int tempy = rand.nextInt(SIZE);
			if (gameBoard[tempx][tempy] == 0) {
				gameBoard[tempx][tempy] = tempVal;
				cond = true;
			}
		}
	}

	public void up() {
		for (int j = 0; j < SIZE; ++j) {
			for (int i = 0; i < SIZE - 1; ++i) {
				int k = i + 1;
				for(; k < SIZE - 1 && gameBoard[k][j] == 0; ++k);
				if(gameBoard[k][j] == gameBoard[i][j]) {
					gameBoard[i][j] *= 2;
					gameBoard[k][j] = 0;
				}
				else if(gameBoard[k][j] != gameBoard[i][j] && gameBoard[i][j] != 0) {
					if (k != i + 1) {
						gameBoard[i + 1][j] = gameBoard[k][j];
						gameBoard[k][j] = 0;
					}
				}
				else if(gameBoard[k][j] != gameBoard[i][j] && gameBoard[i][j] == 0) {
					gameBoard[i][j] = gameBoard[k][j];
					gameBoard[k][j] = 0;
					--i;
				}
			}
		}
	}
	public void down() {
		for (int j = 0; j < SIZE; ++j) {
			for (int i = SIZE - 1; i > 0; --i) {
				int k = i - 1;
				for(; k > 0 && gameBoard[k][j] == 0; --k);
				if(gameBoard[k][j] == gameBoard[i][j]) {
					gameBoard[i][j] *= 2;
					gameBoard[k][j] = 0;
				}
				else if(gameBoard[k][j] != gameBoard[i][j] && gameBoard[i][j] != 0) {
					if (k != i - 1) {
						gameBoard[i - 1][j] = gameBoard[k][j];
						gameBoard[k][j] = 0;
					}
				}
				else if(gameBoard[k][j] != gameBoard[i][j] && gameBoard[i][j] == 0) {
					gameBoard[i][j] = gameBoard[k][j];
					gameBoard[k][j] = 0;
					++i;
				}
			}
		}
	}
	public void left() {
		for (int j = 0; j < SIZE; ++j) {
			for (int i = 0; i < SIZE - 1; ++i) {
				int k = i + 1;
				for(; k < SIZE - 1 && gameBoard[j][k] == 0; ++k);
				if(gameBoard[j][k] == gameBoard[j][i]) {
					gameBoard[j][i] *= 2;
					gameBoard[j][k] = 0;
				}
				else if(gameBoard[j][k] != gameBoard[j][i] && gameBoard[j][i] != 0) {
					if (k != i + 1) {
						gameBoard[j][i + 1] = gameBoard[j][k];
						gameBoard[j][k] = 0;
					}
				}
				else if(gameBoard[j][k] != gameBoard[j][i] && gameBoard[j][i] == 0) {
					gameBoard[j][i] = gameBoard[j][k];
					gameBoard[j][k] = 0;
					--i;
				}
			}
		}
	}
	public void right() {
		for (int j = 0; j < SIZE; ++j) {
			for (int i = SIZE - 1; i > 0; --i) {
				int k = i - 1;
				for(; k > 0 && gameBoard[j][k] == 0; --k);
				if(gameBoard[j][k] == gameBoard[j][i]) {
					gameBoard[j][i] *= 2;
					gameBoard[j][k] = 0;
				}
				else if(gameBoard[j][k] != gameBoard[j][i] && gameBoard[j][i] != 0) {
					if (k != i - 1) {
						gameBoard[j][i - 1] = gameBoard[j][k];
						gameBoard[j][k] = 0;
					}
				}
				else if(gameBoard[j][k] != gameBoard[j][i] && gameBoard[j][i] == 0) {
					gameBoard[j][i] = gameBoard[j][k];
					gameBoard[j][k] = 0;
					++i;
				}
			}
		}
	}
}
