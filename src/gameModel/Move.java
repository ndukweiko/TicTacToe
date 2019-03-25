package gameModel;

public class Move {
	public final int x;
	public final int y;
	public Move(int x, int y) {
		//construct a move object to be pushed to the board and mark it for the player or the AI
		this.x = x;
		this.y = y;
	}

}
