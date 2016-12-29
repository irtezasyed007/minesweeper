package minesweeper;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


import javax.swing.ButtonModel;
import java.awt.event.*;

public class MineButton extends JToggleButton {
	private boolean isMine;
	private boolean canBeMine;
	private boolean isFlagged;
	private int adjMines;
	private int xPosition;
	private int yPosition;
	
	
	public MineButton(){
		
		isMine = false;
		canBeMine = true;
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
	
	public boolean canBeMine(){
		return canBeMine;
	}
	
	public boolean wasClicked(){//check if the buttons has been clicked
		if(this.getMouseListeners().length == 0){
			return true;
		}
		return false;
	}
	
	public void incAdjMines(){
		adjMines++;
	}
	
	public int toggleFlag(int remainingFlags){//returns the number of flags that haven't been placed
		Icon flag = new ImageIcon(getClass().getResource("MineFlag.png"));
		if(isFlagged){
			isFlagged = false;
			this.setIcon(null);
			return ++remainingFlags;
		}
		else{
			isFlagged = true;
			this.setIcon(flag);
			return --remainingFlags;
		}
	}
	
	public void setMine(){
		isMine = true;
	}
	
	public void lockMineState()
	{
		canBeMine = false;
	}
	public void setBounds(int x, int y){
		this.setBounds(x, y, 20, 20);
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MineButton mb = (MineButton)obj;
		if(this.xPosition == mb.xPosition && this.yPosition == mb.yPosition && this.isMine == mb.isMine){
			return true;
		}
		return false;
	}
}
