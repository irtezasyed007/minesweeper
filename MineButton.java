package edu.cuny.csi.csc330.minesweeper;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.ButtonModel;
import java.awt.event.*;

public class MineButton extends JToggleButton {
	private boolean isMine;
	private boolean isFlagged;
	private int adjMines;
	private int xPosition;
	private int yPosition;
	
	
	public MineButton(){
		
		isMine = false;
		isFlagged = false;
		adjMines = 0;
		xPosition = 0;
		yPosition = 0;
		
		this.setBounds(0, 0, 20, 20);
	}
	
	public void setCoordinates(int newXPosition, int newYPosition){
		xPosition = newXPosition;
		yPosition = newYPosition;
	}
	
	public int getXPosition(){
		return xPosition;
	}
	
	public int getYPosition(){
		return yPosition;
	}
	
	public int getAdjMines(){
		return adjMines;
	}
	
	public boolean isMine(){
		return isMine;
	}
	
	public boolean wasClicked(){
//		ButtonModel model = this.getModel();
//		if(model.isSelected())
//			return true;
//		return false;
		if(this.getMouseListeners().length == 0){
			return true;
		}
		return false;
	}
	
	public void incAdjMines(){
		adjMines++;
	}
	
	public void toggleFlag(){
		Icon flag = new ImageIcon(getClass().getResource("MineFlag.png"));
		if(isFlagged){
			isFlagged = false;
			this.setIcon(null);
		}
		else{
			isFlagged = true;
			this.setIcon(flag);
		}
	}
	
	public void setMineIcon(){
		Icon mine = new ImageIcon(getClass().getResource("MineIcon.png"));
		this.setIcon(mine);
	}
	
	public void setMine(){
		isMine = true;
	}
	
	public void setBounds(int x, int y){
		this.setBounds(x, y, 20, 20);
	}
	
}