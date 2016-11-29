package edu.cuny.csi.csc330.minesweeper;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.*;
import edu.cuny.csi.csc330.lib.*;

public class Minesweeper{
	// Protected Constants
	protected static final int INITIAL_AREA = 100;
	protected static final int MIN_MINES = 5;
	
	private JFrame frame;
	private MineButton[][] mineField;
	private boolean hasClicked;
	
	private class ButtonClickListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			MineButton mButton = (MineButton)e.getSource();
			if(SwingUtilities.isRightMouseButton(e)){
				if(hasClicked){
				mButton.toggleFlag();
				}
			}
			else{
				if(!hasClicked){
					hasClicked = true;
					generateMines(mButton);
					cascade(mButton);
					mButton.setText(mButton.getAdjMines() + "");
				}
				else if(mButton.isMine()){
					lost();
				}
				else{
					if(mButton.getAdjMines() == 0){
						cascade(mButton);
					}
					else{
						mButton.setText(mButton.getAdjMines() + "");
					}
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class ButtonStateChanged implements ItemListener{
		public void itemStateChanged(ItemEvent ev){
			MineButton mButton = (MineButton)ev.getSource();
			if(ev.getStateChange() == ItemEvent.SELECTED){
				MouseListener mouseArray[] = mButton.getMouseListeners();
				mButton.removeMouseListener(mouseArray[0]);
			}
		}
	}
	//private Options optionsMenu;
	//private PlayStatistics userStats;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minesweeper window = new Minesweeper();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Minesweeper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		hasClicked = false;
		createField("10","10");
	}

	public void createField(String width, String length){
		mineField=new MineButton[Integer.parseInt(width)][Integer.parseInt(length)];
		
		frame.setBounds(100, 100, Integer.parseInt(width)*20+12, Integer.parseInt(length)*20+35);

		for (int i=0;i<Integer.parseInt(width);i++){
			for(int j=0;j<Integer.parseInt(length);j++){
				mineField[i][j]=new MineButton();
				mineField[i][j].setBounds(i*20+6,j*20+6);
				mineField[i][j].setCoordinates(i, j);
				frame.getContentPane().add(mineField[i][j]);
				mineField[i][j].addItemListener(new ButtonStateChanged());
				mineField[i][j].addMouseListener(new ButtonClickListener());
			}
		}
	}
	

	public void generateMines(MineButton mButton){
		int x = mButton.getX(), y = mButton.getY(), mines = 0;
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i = -1; i < 2; ++i){
			for(int j = -1; j < 2; ++j){
				int arr[] = new int[2];
				arr[0] = x + i;
				arr[1] = y + j;
				list.add(arr);
			}
		}
		Randomizer randomizer = new Randomizer();
		
		while(mines < 10){
			int i = randomizer.generateInt(0, 9);
			int j = randomizer.generateInt(0, 9);
			int arr[] = new int[2];
			arr[0] = i;
			arr[1] = j;
			if(!list.contains(arr)){
				list.add(arr);
				mineField[i][j].setMine();
				for(int k = -1; k < 2; ++k){
					for(int l = -1; l < 2; ++l){
						if(i + k >= 0 && i + k <= 9 && j + l >= 0 && j + l <= 9){
							mineField[i + k][j + l].incAdjMines();
						}
					}
				}
				++mines;
			}
		}
	}
	
	private void cascade(MineButton mButton){
		boolean triggered = false;
		if(mButton.getAdjMines() == 0){
			mButton.setSelected(true);
			triggered = true;
		}
		int x = mButton.getX(), y = mButton.getY();
		if(triggered){
			for(int i = -1; i < 2; ++i){
				for(int j = -1; j < 2; ++j){
					if(i + x >= 0 && i + x <= 9 && j + y >= 0 && j + y <= 9){
						cascade(mineField[i][j]);
					}
				}
			}
		}
	}
	
	private void lost(){
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				if(mineField[i][j].isMine()){
					mineField[i][j].setMineIcon();
				}
			}
		}
	}
}
