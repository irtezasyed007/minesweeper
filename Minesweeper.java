package minesweeper;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.io.*;
import sun.audio.*;
import java.util.*;

public class Minesweeper{
	
	
	private JFrame frame;
	private MineButton[][] mineField;//the matrix of MineButtons which serves as the actual mine field
	private boolean hasClicked;		//true if the user has begun the game
	private int width=10;			//the width (in columns) of the mine field
	private int height=10;			//the height (in rows) of the mine field
	private int numOfMines = 10;	//number of mines in the mine field
	private int minesLeft = 10;		//the remaining flags the user has not placed
	private JLabel lblTime;
	private JLabel lblMines;
	private Timer timer = new Timer(1000, timerAction());
	private Integer time=0;			//used to keep track of game time
	private JMenuItem newGame;
	private JMenuItem quitGame;
	private JMenuItem options;
	private OKButton ok;
	
	private Icon mine = new ImageIcon(getClass().getResource("MineIcon.png"));
	private Icon explosion = new ImageIcon(getClass().getResource("Explosion.png"));
	private Icon smiley=new ImageIcon(getClass().getResource("Smiley.png"));
	
	
	//**************************************************
	//PRIMARY ACTION LISTENER
	//used to handle click events on the mine field
	//*************************************************
	private class ButtonClickListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			MineButton mButton = (MineButton)e.getSource();
			//RIGHT CLICK
			if(SwingUtilities.isRightMouseButton(e)){
				if(hasClicked && mButton.isSelected()==false){
				minesLeft=mButton.toggleFlag(minesLeft);
				lblMines.setText("Mines: "+minesLeft);
				}
			}
			//LEFT CLICK
			else{
				mButton.setSelected(true);
				if(!hasClicked){//if this is the first click
					hasClicked = true;
					generateMines(mButton);//generate the mines around the clicked button
					cascade(mButton);
				}
				else if(mButton.isMine()){//if a mine is clicked
					mButton.setOpaque(true);
					mButton.setBorderPainted(false);
					mButton.setBackground(Color.red);
					endGame();//end the game
					lost();//display lost message
				}
				else{//if a non-mine is clicked
					if(mButton.getAdjMines() == 0){//if there are no surrounding mines
						cascade(mButton);
					}
					else{
						mButton.setText(mButton.getAdjMines() + "");
					}
				}
				if(checkForWin()){//check if all non-mines are uncovered 
					endGame();//end the game
					won();//display won message
				}
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
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

	//**************************************
	//TIMER ACTION LISTENER
	//keeps track of game time
	//************************************
	private ActionListener timerAction(){
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

	//******************************************
	//INITIALIZATION
	//Initialize the contents of the frame.
	//*******************************************
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Minesweeper");
		frame.setJMenuBar(topMenu());
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		lblMines = new JLabel("Mines: " + minesLeft);
		lblMines.setBounds(120, 0, 120, 30);
		frame.add(lblMines);
		
		hasClicked = false;
		createField(width, height);
	}
	
	
	//*************************************************
	//MENU BAR
	//The top menu and it's components.
	//************************************************
	private JMenuBar topMenu()
	{
		//Menu bar's action listener
		ActionListener click = new ActionListener(){
			public void actionPerformed (ActionEvent e){
				Object x = e.getSource();
				if(x == quitGame){
					System.exit(0);
				}
				else if (x == options){	//creates Options instance. Uses OKButton to communicate between Options and Minesweeper.
					ok=new OKButton("Ok");
					ok.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(ok.tmpWidth!=0 && ok.tmpHeight != 0 && ok.tmpMines!=0){
								width=ok.tmpWidth;
								height=ok.tmpHeight;
								numOfMines=ok.tmpMines;
								resetField(width,height);
									
							}
						}
					});
					Options optionsMenu=new Options(ok);
					optionsMenu.setVisible(true);			
				}
				else if (x==newGame){
					resetField(width,height);
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

	//*******************************************
	//CREATEFIELD METHOD
	//Creates a mine field of MineButtons of dimensions w by h. 
	//*********************************************
	public void createField(int w, int h){
		minesLeft=numOfMines;
		
		mineField=new MineButton[w][h];
		
		frame.setBounds(100,100, w*20+12, h*20+82);
		
		for (int i = 0; i < w; i++){
			for (int j=0;j<h;j++){
				mineField[i][j]=new MineButton();
				mineField[i][j].setCoordinates(i, j);
				mineField[i][j].setBounds(i*20+6,j*20+32);
				frame.getContentPane().add(mineField[i][j]);
				mineField[i][j].addItemListener(new ButtonStateChanged());
				mineField[i][j].addMouseListener(new ButtonClickListener());
			}
		}
	}
	
	//************************************************
	//RESETFIELD METHOD
	//Recreates the mine field and resets all variables to their default states.
	//*************************************************
	public void resetField(int w, int h){
		timer.stop();
		frame.getContentPane().removeAll();
		frame.repaint();
		createField(w,h);
		
		//time label
		JLabel timeLabel = new JLabel ("Time:");
		timeLabel.setBounds(6,0,50,30);
		timeLabel.setVisible(true);
		frame.add(timeLabel);
				
		//timer
		time=0;
		lblTime = new JLabel("0");
		lblTime.setBounds(45,0,50,30);
		lblTime.setForeground(Color.red);
		lblTime.setFont(new Font("DialogInput", Font.BOLD, 18));
		frame.add( lblTime );
				
		//mines label
		lblMines = new JLabel("Mines: " + minesLeft);
		lblMines.setBounds(120, 0, 120, 30);
		frame.add(lblMines);
		
		hasClicked = false;
	}

	//***********************************************
	//GENERATEMINES METHOD
	//Randomly sets MineButtons of mineField as mines based on the user's initial click
	//************************************************
	public void generateMines(MineButton mButton){
		int x = mButton.getXPosition(), y = mButton.getYPosition(), mines = 0;
		//ensure that the 8 squares around the initial click can't be mines
		for(int i = x - 1; i < x + 2; ++i){
			for(int j = y - 1; j < y + 2; ++j){
				if(i >= 0 && i <= width - 1 && j >= 0 && j <= height - 1)
					mineField[i][j].lockMineState();
			}
		}
		
		Randomizer randomizer = new Randomizer();
		while(mines < numOfMines){
			int i = randomizer.generateInt(0, width -1);
			int j = randomizer.generateInt(0, height -1);
			if(mineField[i][j].canBeMine()){
				mineField[i][j].setMine();
				mineField[i][j].lockMineState();
				for(int k = -1; k < 2; ++k){//increment adjacent mines for surrounding mines
					for(int l = -1; l < 2; ++l){
						if(i + k >= 0 && i + k <= width - 1 && j + l >= 0 && j + l <= height - 1){
							mineField[i + k][j + l].incAdjMines();
						}
					}
				}
				++mines;
			}
		}timer.start();

	}
	
	//*******************************************************
	//CASCADE METHOD
	//Uncovers all surrounding mines of adjMines==0.
	//****************************************************
	private void cascade(MineButton mButton){
		boolean moreToOpen=true;
		
		while(moreToOpen){
			moreToOpen=false;
			for (int i=0;i<width;i++){//for the entire mine field,
				for(int j=0;j<height;j++){
					if(mineField[i][j].getAdjMines()==0 && mineField[i][j].isSelected()){//if adjMines==0 and button has been selected
						for(int k = -1; k < 2; ++k){//for the 8 surrounding buttons
							for(int l = -1; l < 2; ++l){
								if(i + k >= 0 && i + k <= width - 1 && j + l >= 0 && j + l <= height - 1){//provided the buttons don't leave the mine field's bounds
									if(mineField[i+k][j+l].isSelected()==false){
										moreToOpen=true;
										mineField[i+k][j+l].setSelected(true);
										if(mineField[i+k][j+l].getAdjMines()!=0){
											mineField[i+k][j+l].setText(mineField[i+k][j+l].getAdjMines()+"");
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//*****************************************
	//DISPLAYMESSAGE METHOD
	//displays a message dialog with the given message and icon
	//*****************************************
	private void displayMessage(String message, Icon icon){
		JOptionPane.showMessageDialog(frame, message, "Minesweeper", 1 , icon);
	}
	
	//*********************************************
	//CHECKFORWIN METHOD
	//checks if the game has been won by checking if every button that isn't a mine has been selected
	//**********************************************
	private boolean checkForWin(){
		for(int i=0;i<width;i++){
			for (int j=0;j<height;j++){
				if(mineField[i][j].isSelected()==false && mineField[i][j].isMine()==false){
					return false;
				}
			}
		}return true;
	}
	
	//********************************************
	//ENDGAME METHOD
	//ends the game (i.e. show all mines, stop timer)
	//********************************************
	private void endGame(){
		for(int i = 0; i < width; ++i){//show all mines
			for(int j = 0; j < height; ++j){
				if(mineField[i][j].isMine()){
					mineField[i][j].setText(null);
					mineField[i][j].setSelected(true);
					mineField[i][j].setIcon(mine);
					MouseListener[] m=mineField[i][j].getMouseListeners();
					mineField[i][j].removeMouseListener(m[0]);
				}
				else{
					frame.getContentPane().remove(mineField[i][j]);
				}
			}
		}timer.stop();
	}
	
	//****************************************
	//OTHER METHODS
	//minor, self-explanatory methods
	//**************************************
	
	//WON
	private void won(){
		playWinSound();
		displayMessage("You won!",smiley);
	}
	
	//LOST
	private void lost(){		
		playMineSound();
		displayMessage("You lost!",explosion);

	}
	
	//PLAYMINESOUNDS
	private void playMineSound() 
    {
      try
      {
        InputStream inputStream = getClass().getResourceAsStream("Bomb.wav");
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
      }
      catch (Exception e)
      {
      }
    }
	
	//PLAYWINSOUND
	private void playWinSound() 
    {
      try
      {
        InputStream inputStream = getClass().getResourceAsStream("TaDa.wav");
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
      }
      catch (Exception e)
      {
      }
    }
}

