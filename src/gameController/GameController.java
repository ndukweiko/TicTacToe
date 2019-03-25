package gameController;


import gameModel.*;

public class GameController {
	
	//GameModel
	public Game game;
	public State state;
	public Player player;
	public AI ai;
	public GameController(Game game) {
		this.game = game;
		this.state = State.STATE_START;
		this.player = new Player();
		this.ai = new AI();
	}
	
	
	public void changeState() {
		switch(this.state) {
		case STATE_START:
			this.state = gameModel.State.STATE_PLAYER;
			break;
		case STATE_PLAYER:
			this.state = gameModel.State.STATE_EVALUATE_PA;
			break;
		case STATE_EVALUATE_PA:
			this.state = gameModel.State.STATE_AI;
			break;
		case STATE_AI:
			this.state = gameModel.State.STATE_EVALUATE_AP;
			break;
		case STATE_EVALUATE_AP:
			this.state = gameModel.State.STATE_PLAYER;
			break;
		
			
			
		
		}
		
	
	}
	public void endGame(int result){
		this.state = gameModel.State.STATE_END;
		switch(result) {
		case 0:
			System.out.println("Player Won");
			break;
		case 1:
			System.out.println("AI Won");
			break;
		case 2:
			System.out.println("It's a tie!");
			break;
		
		}
			
		
	}
	public Boolean playerTurn(Move move, Boolean isPlayer) {
		//get input from player expressed as coordinates
		Boolean canMove = false;
	
			
			Move testMove = move;

			//repeat until the user enters a valid move
			canMove = this.game.board.canSetTile(testMove);
			//check if space unoccupied on board
			//raise move to board.
			if(canMove) {
				this.game.board.setTile(testMove, isPlayer);
			}
			
		

			
		
		
			return canMove;
		
		 
		
}
	public void MakeMove(MoveConfiguration move) throws IllegalMoveException {
		   //use AI's calculated MoveConfiguration object to mark the spot suggested on the board
		Boolean canMove = false;
		Move testMove = move.MoveForConfiguration;

		//repeat until the user enters a valid move
		canMove = this.game.board.canSetTile(testMove);
		//check if space unoccupied on board
		//raise move to board.
		if(canMove) {
			this.game.board.setTile(testMove, false);
		}
		else {
			System.out.println("Error " + testMove.x + " " + testMove.y);
			throw new IllegalMoveException("Illegal Move suggested by Algorithm. Please address");
			
		}

	}


	public int isGameOver() {
		int evaluateWinner = this.game.board.checkStatus();
		return evaluateWinner;
	
	}
	
	
	

}
