package gameModel;

public class Board {
	public int [][] board;
	public Board() {
		this.board = new int [3][3];
		
	}
	
	public static Board copyBoard(Board board) {
		Board copy = new Board();
		for(int i = 0; i <= 2;  i++) {
			for(int j = 0; j <= 2; j++) {
				copy.board[i][j] = board.board[i][j];
			}
		}
		return copy;
	}
	
	public Boolean canSetTile(Move testMove) {
		// TODO Auto-generated method stub
		
		int x = testMove.x;
		int y = testMove.y;
		for(int i =0; i <= 2; i++) {
			for(int j=0; j <=2; j++) {
				if(i == x && j == y) {
					if(this.board[i][j] == 0) {
						//the tile is free;
						return true;
					}
					else {
						return false;
					}
				}
			
		
			}
		}
		//no match/or illegal request
		return false;
		
	}
	
	public void setTile(Move testMove, boolean isPlayer) {
		// TODO Auto-generated method stub
		int x = testMove.x;
		int y = testMove.y;
		for(int i =0; i <= 2; i++) {
			for(int j=0; j <=2; j++) {
				if(x == i && y == j && isPlayer == true) {
					//match -- set tile to value of Move for Player
					this.board[i][j] = 1;
				}
				else if(x == i && y == j && isPlayer == false) {
					//set tile to value of move for AI
					this.board[i][j] = 2;
				}
			}
		}
		
	}

	public Boolean checkVertical(int search) {
		
		int colNdx = 0;
		Boolean done = false;
		Boolean foundWin = false;
		while(!done) {
			//do for each column
			int search_count = 0;
			for(int i =0; i <= 2; i++) {
				if(this.board[i][colNdx] == search) {
					search_count++;
				}
			}
			if(search_count == 3) {
				foundWin = true;
				done = true;
			}
			else if(search_count < 3 && done == false && colNdx < 2) {
				//didn't find a win condition this column -- next
				colNdx++;
			}
			else {
				//we've evaluated all three verticals 
				done = true;
			}
		}
		return foundWin;
		
	}
	public Boolean checkHorizontal(int search) {
		
		int RowNdx = 0;
		Boolean done = false;
		Boolean foundWin = false;
		while(!done) {
			//do for each column
			int search_count = 0;
			for(int j =0; j <= 2; j++) {
				if(this.board[RowNdx][j] == search) {
					search_count++;
				}
			}
			if(search_count == 3) {
				foundWin = true;
				done = true;
			}
			else if(search_count < 3 && done == false && RowNdx < 2) {
				//didn't find a win condition this column -- next
				RowNdx++;
			}
			else {
				//we've evaluated all three verticals 
				done = true;
			}
		}
		return foundWin;
		
	}
	public Boolean checkDiag(int search) {
		Boolean done = false;
		Boolean foundWin = false;
		while(!done) {
			//do for each column
			int search_count = 0;
			for(int i =0; i <= 2; i++) {
				if(this.board[i][i] == search) {
					search_count++;
				}
			}
			if(search_count == 3) {
				foundWin = true;
			}
			done = true;
			
		}
		return foundWin;
	}
	
	public Boolean checkDiag_two(int search) {
		int colNdx = 0;
		Boolean done = false;
		Boolean foundWin = false;
		while(!done) {
			//do for each column
			int search_count = 0;
			for(int i =2; i >= 0; i--) {
				if(this.board[i][colNdx] == search) {
					search_count++;
				}
				colNdx++;
			}
			if(search_count == 3) {
				foundWin = true;
			}
			done = true;
			
		}
		return foundWin;
	}
	
	public Boolean isAtCapacity() {
		int nonZero = 0;
		for(int i=0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				if(this.board[i][j] != 0) {
					nonZero++;
				}
			}
		}
		if(nonZero == 9) {
			
			return true;
		}
		else {
			//found zeroes in the 2d array
			return false;
		}
	}
		
	public Boolean didPlayerWin() {
		Boolean PWinH = checkHorizontal(1);
		Boolean PWinV = checkVertical(1);
		Boolean PWinD_one = checkDiag(1);
		Boolean PWinD_two = checkDiag_two(1);
		if(PWinH || PWinV || PWinD_one || PWinD_two) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean didAIWin() {
		Boolean AWinH = checkHorizontal(2);
		Boolean AWinV = checkVertical(2);
		Boolean AWinD_one = checkDiag(2);
		Boolean AWinD_two = checkDiag_two(2);
		if(AWinH || AWinV || AWinD_one || AWinD_two) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	public int checkStatus() {
		//for the game to be over -- either we have a winner or a tie with all the spaces filled
		
		Boolean playerWin = didPlayerWin();
		Boolean AIWin = didAIWin();
		if(playerWin) {
			return 0;
		}
		else if(AIWin) {
			return 1;
		}
		else if(isAtCapacity()) {
			//if the AI and the Player haven't achieved the win condition and the board is
			//at capacity, we have a tie
			return 2;
		}
		
		else {
			//Not at capacity and no one won yet. Continue the game.
			return 3;
		}

		
	}
}
