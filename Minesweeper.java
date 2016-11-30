package edu.cuny.csi.csc330.minesweeper;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.*;
import edu.cuny.csi.csc330.lib.*;
=======
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
//import edu.cuny.csi.csc330.lib.*;
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7

public class Minesweeper{
	// Protected Constants
	protected static final int INITIAL_AREA = 100;
	protected static final int MIN_MINES = 5;
	
	private JFrame frame;
	private MineButton[][] mineField;
	private boolean hasClicked;
<<<<<<< HEAD
=======
	private JLabel lblTime;
	private JLabel lblMines;
	private Timer timer = new Timer(1000, getAction());
	private Integer time=0;
	private JMenuItem newGame;
	private JMenuItem quitGame;
	private JMenuItem options;
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7
	
	private class ButtonClickListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			MineButton mButton = (MineButton)e.getSource();
<<<<<<< HEAD
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
=======
			if(SwingUtilities.isRightMouseButton(e) && hasClicked){
				mButton.toggleFlag();
			}
			if(!hasClicked){
				hasClicked = true;
				generateMines(mButton);
				cascade(mButton);
			}
			else if(mButton.isMine()){
				lost();
			}
			else{
				
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7
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
	
<<<<<<< HEAD
=======
	private ActionListener getAction(){
		ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	time++;
            	if (time<1000){
            		lblTime.setText(time.toString());
            	}
            }
        };
        return action;
	}
	
	
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7
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
		frame.setTitle("Minesweeper");
		frame.setJMenuBar(topMenu());
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
<<<<<<< HEAD
		hasClicked = false;
		createField("10","10");
	}
=======
		//time label
		JLabel timeLabel = new JLabel ("Time:");
		timeLabel.setBounds(6,0,50,30);
		timeLabel.setVisible(true);
		frame.add(timeLabel);
		
		//timer
		lblTime = new JLabel("0");
		lblTime.setBounds(45,0,50,30);
		lblTime.setForeground(Color.red);
		lblTime.setFont(new Font("DialogInput", Font.BOLD, 18));
		frame.add( lblTime );
		
		//mines label
		lblMines = new JLabel("Mines:");
		lblMines.setBounds(120, 0, 120, 30);
		frame.add(lblMines);
		
		hasClicked = false;
		createField("10","10");
	}
	
	private JMenuBar topMenu()
	{
		ActionListener click = new ActionListener(){
			public void actionPerformed (ActionEvent e){
				Object x = e.getSource();
				if(x == quitGame){
					System.exit(0);
				}
				else if (x == options){
					Options options=new Options();
					options.setVisible(true);
					//width=Integer.parseInt(options.txtWidth.getText());
				}
			}
		};
		
		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Menu");
			newGame = new JMenuItem("New Game");
			newGame.addActionListener(click);
			quitGame = new JMenuItem("Quit");
			quitGame.addActionListener(click);
			options = new JMenuItem("Options");
			options.addActionListener(click);
			gameMenu.add(newGame);
			gameMenu.add(options);
			gameMenu.add(quitGame);		

		menuBar.add(gameMenu);		

		return menuBar;
	}
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7

	public void createField(String width, String length){
		mineField=new MineButton[Integer.parseInt(width)][Integer.parseInt(length)];
		
		frame.setBounds(100, 100, Integer.parseInt(width)*20+12, Integer.parseInt(length)*25+35);

		for (int i=0;i<Integer.parseInt(width);i++){
			for(int j=0;j<Integer.parseInt(length);j++){
				mineField[i][j]=new MineButton();
<<<<<<< HEAD
				mineField[i][j].setBounds(i*20+6,j*20+6);
=======
				mineField[i][j].setBounds(i*20+6,j*20+35);
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7
				mineField[i][j].setCoordinates(i, j);
				frame.getContentPane().add(mineField[i][j]);
				mineField[i][j].addItemListener(new ButtonStateChanged());
				mineField[i][j].addMouseListener(new ButtonClickListener());
			}
		}
	}
	

	public void generateMines(MineButton mButton){
		int x = mButton.getX(), y = mButton.getY(), mines = 0;
<<<<<<< HEAD
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
=======
		int startPos[] = {x, y};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(startPos);
		Randomizer randomizer = new Randomizer();
		
		while(mines < 10){
			int i = randomizer.generateInt(0, 9);
			int j = randomizer.generateInt(0, 9);
			int arr[] = {i, j};
			if(!list.contains(arr) && x != i + 1 && x != i - 1 && y != j + 1 && y != j - 1 ){
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
		timer.start();
		
		
>>>>>>> 99f3dbb7274f686d7809baad64ba6a8505ac4da7
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
		timer.stop();
	}
}