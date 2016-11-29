package edu.cuny.csi.csc330.minesweeper;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Minesweeper {

	private JFrame frame;
	private MineButton[][] mineField;
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

		createField("10","10");
	}
	
	public void createField(String width, String length){
		mineField=new MineButton[Integer.parseInt(width)][Integer.parseInt(length)];
		
		frame.setBounds(100, 100, Integer.parseInt(width)*20+12, Integer.parseInt(length)*20+35);

		for (int i=0;i<Integer.parseInt(width);i++){
			for(int j=0;j<Integer.parseInt(length);j++){
				mineField[i][j]=new MineButton();
				mineField[i][j].setBounds(i*20+6,j*20+6);
				frame.getContentPane().add(mineField[i][j].getButton());
			}
		}
		
		mineField[0][0].toggleFlag();
		mineField[5][5].setMineIcon();
	}
}
