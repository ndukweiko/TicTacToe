package gameModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import gameController.GameController;
import gameController.IllegalMoveException;
import gameView.GameView;
public class main {

	public static void main(String[] args) throws IllegalMoveException {
		//initialize GameModel
		
		Board board = new Board();
		Player player = new Player();
		AI ai = new AI();
		Game game = new Game(board, player, ai);
		//initialize GameController
		GameController gc = new GameController(game);
		GameView gv = initializeGUI(gc);

		
		
		System.out.println(gv.gc.state);
		while(!gv.gc.state.equals(State.STATE_END)) {
			//while the win condition is not met
			
			//get next state
			gv.gc.changeState();
			System.out.println(gv.gc.state);
			showDataRepresentation(gc);
			switch(gv.gc.state) {
			case STATE_PLAYER:
				
				//call code for player's turn
				while(gv.gc.player.hasMoved == false) {
					//wait here
				    try {
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
					System.out.println("waiting");
				}
				//reset for next turn -- set flag to false
				gv.gc.player.WaitForTurn();
				
				
				break;
			case STATE_EVALUATE_PA:
				//code to check if game is over
				int result = gv.gc.isGameOver();
				if(result != 3) {
					gv.gc.endGame(result);
				}
				break;
			
			
			case STATE_EVALUATE_AP:
				//code to check if game is over
				int res = gv.gc.isGameOver();
				if(res != 3) {
					gv.gc.endGame(res);
				}
			
				break;
			case STATE_AI:
				//code to define AI's moves for that turn 
				//copy current board to avoid editing it by mistake
		
				Board copy = Board.copyBoard(gv.gc.game.board);
				MoveConfiguration bestMove = ai.FindBestMove(copy);
				int targetNdx = ai.translateArray(bestMove);
				//simulate click to update view
				gv.Buttons[targetNdx].doClick();
				//write to data model
				
				break;
			
			}
		}
		
		
	}


	private static void showDataRepresentation(GameController gc) {
		// TODO Auto-generated method stub
		int [][] arr = gc.game.board.board;
		System.out.println(Arrays.deepToString(arr).replace("], ", "]\n"));
	}


	public static GameView initializeGUI(GameController gc) {
        JFrame window = new JFrame("Tic-Tac-Toe");
        GameView gv = new GameView(gc);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(gv);
        window.setBounds(300,200,300,300);
        window.setVisible(true);
        return gv;
	}
}
