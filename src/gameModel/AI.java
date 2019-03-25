package gameModel;

import java.util.ArrayList;

public class AI {
	Boolean hasMoved;
	public AI() {
		this.hasMoved = false;
	}

	public MoveConfiguration FindBestMove(Board board) {
		//scan for list of all available moves for the AI]
		ArrayList<MoveConfiguration> mcf_list = new ArrayList<MoveConfiguration>();
		int bestMoveValue = Integer.MIN_VALUE;
		MoveConfiguration mcf = new MoveConfiguration();
		
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				Move testMove = new Move(i,j);
				if(board.canSetTile(testMove)) {
					//if the space is available, configure the board that models this scenario
					Board copy = Board.copyBoard(board);
					copy.board[i][j]= 2;
					//and pass it to the minmax function
					int result = MinMax(copy, 0, false);
					if(result > bestMoveValue) {
						//replace with better solution
						bestMoveValue = result;
						//mcf.board = board;
						mcf.MoveForConfiguration = testMove;
						mcf.ValueForConfiguration = result;
						mcf_list.add(mcf);
					}
				}
			}
		}
		//System.out.println(mcf.MoveForConfiguration.x + " " + mcf.MoveForConfiguration.y);
		process_list(mcf_list);
		return mcf;
		
	}
	private void process_list(ArrayList<MoveConfiguration> mcf_list) {
		// TODO Auto-generated method stub
		for(MoveConfiguration x : mcf_list) {
			System.out.println(x.ValueForConfiguration + " " + "(" + x.MoveForConfiguration.x + " " + x.MoveForConfiguration.y + ")");
		}
		
	}

	public int MinMax(Board board, int depth, Boolean isMaximizingPlayer) {
		//if board is in a terminal state, return it's value
		if(board.checkStatus() != 3) {
			//return value of board
			if(board.checkStatus() == 2) {
				//tie
				return 0;
			}
			else if(board.checkStatus() == 1) {
				//AI achieved win condition
				return 10;
			}
			else {
				//Player achieved win condition
				return -10;
			}
		}
		
		if(isMaximizingPlayer) {
			//mark for player
			int bestValue = Integer.MIN_VALUE;
			for(int i = 0; i <= 2; i++) {
				for(int j=0; j<=2; j++) {
					Move testMove = new Move(i, j);
					if(board.canSetTile(testMove)) {
						Board copy = Board.copyBoard(board);
						copy.board[i][j] = 2;
						bestValue =  Math.max(bestValue, MinMax(copy, depth + 1, !isMaximizingPlayer));
					}

				}
			}
			return bestValue;
		}
		else {
			//mark for opponent
			int bestValue = Integer.MAX_VALUE;
			for(int i = 0; i <= 2; i++) {
				for(int j=0; j<=2; j++) {
					Move testMove = new Move(i, j);
					if(board.canSetTile(testMove)) {
						Board copy = Board.copyBoard(board);
						copy.board[i][j] = 1;
						bestValue =  Math.min(bestValue, MinMax(copy, depth + 1, !isMaximizingPlayer));
					}
				}
			}
			
			return bestValue;
		}
	}

	public int translateArray(MoveConfiguration bestMove) {
		// TODO Auto-generated method stub
		int x = bestMove.MoveForConfiguration.x;
		int y = bestMove.MoveForConfiguration.y;
		int result = y+ (3*x);
		//the index of the JButton we need to click.
		return result;
	}
}
