package gameView;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import gameController.*;
import gameModel.Move;
public class GameView extends JPanel {

	
	public ArrayButton[] Buttons = new ArrayButton[9];
	public GameController gc;
	
	public GameView(GameController gc) {
		setLayout(new GridLayout(3,3));
		init();
		this.gc = gc;
	}
	public void init() {
		int col = 0;
		int row = 0;
		
		for(int i=0; i <= 8 ; i++) {
			Buttons[i] =  new ArrayButton();
			
			Buttons[i].setText("");
			Buttons[i].addActionListener(new GameListener());
			//row---x
			Buttons[i].row = i / 3;
			//column---y
			Buttons[i].col = i % 3;
			add(Buttons[i]);
		}
	}
	
	
	
}
