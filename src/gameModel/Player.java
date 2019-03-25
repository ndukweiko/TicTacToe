package gameModel;

public class Player {
	
	public Boolean hasMoved;
	
	public Player() {
		this.hasMoved = false;
	}
	public void WaitForTurn() {
		this.hasMoved = false;
	}
	public void ConsumeTurn() {
		this.hasMoved = true;
	}
}
