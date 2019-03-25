package gameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gameController.IllegalMoveException;
import gameModel.Move;
import gameModel.MoveConfiguration;
import gameModel.State;
import gameModel.State;
public class GameListener implements ActionListener {

	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		ArrayButton buttonPressed = (ArrayButton)e.getSource();
		//get parent
		GameView gv = (GameView) buttonPressed.getParent();
		if(gv.gc.state.equals(State.STATE_PLAYER)) {
			Boolean success = false;
			int x = buttonPressed.row;
			int y = buttonPressed.col;
			buttonPressed.setText("x");
			Move testMove = new Move(x,y);
	
			success = gv.gc.playerTurn(testMove, true);
			if(success == true) {
				gv.gc.player.ConsumeTurn();
			}
			
			
		}
		else if(gv.gc.state.equals(State.STATE_AI)) {
			buttonPressed.setText("o");
			//write to data model
			MoveConfiguration bestMove = new MoveConfiguration();
			Move move = new Move(buttonPressed.row ,buttonPressed.col);
			bestMove.MoveForConfiguration = move;
			try {
				gv.gc.MakeMove(bestMove);
			} catch (IllegalMoveException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else {
			//do nothing
		}

	
		
		
	}
}