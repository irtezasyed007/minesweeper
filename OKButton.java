package minesweeper;

import javax.swing.JButton;

public class OKButton extends JButton{
	public int tmpWidth;
	public int tmpHeight;
	public int tmpMines;
	public OKButton(String okString){
		tmpWidth = 0;
		tmpHeight = 0;
		tmpMines = 0;
		this.setText(okString);
	}
}
