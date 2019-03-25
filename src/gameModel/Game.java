package gameModel;

public class Game {	

	//Game has a board
	public Board board;
	public Player player;
	public AI ai;
	
	

	
	public Game(Board board, Player player, AI ai){
		this.board = board;
		this.player = player;
		this.ai = ai;
	
		
	}

}
