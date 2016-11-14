package edu.cuny.csi.csc330.project;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MineButton {
	private JButton button;
	private boolean isMine;
	private boolean isFlagged;
	private int adjMines;
	private Icon mine = new ImageIcon("unnamed.png");
	
	public MineButton(){
		button = new JButton("");
		isMine=false;
		isFlagged=false;
		adjMines=0;
		
		button.setBounds(0, 0, 20, 20);
	}
	
	public void incAdjMines(){
		adjMines++;
	}
	
	public void toggleFlag(){
		isFlagged = isFlagged == true ? false : true;
		button.setText("F");
	}
	
	public void setMineIcon(){
		Icon mine = new ImageIcon(getClass().getResource("unnamed1.png"));
		button.setIcon(mine);
	}
	
	public void makeMine(){
		isMine = true;
	}
	
	public void setBounds(int x, int y){
		button.setBounds(x, y, 20, 20);
	}
	
	public JButton getButton(){
		return button;
	}
}
