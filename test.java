import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JToggleButton;

import java.awt.Button;
import java.awt.Canvas;

import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.JRadioButton;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Random;
import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

public class test {

	private JFrame frame;
	private JTextField txtWidth;
	private JTextField txtLength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
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
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final String DEFAULT_WIDTH = new String("10");
		final String DEFAULT_LENGTH= new String("1");
		final int NUM_OF_MINES=10;
		
		
		//*************************************
		//FRAME
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 369, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//*************************************
		//MENU BAR
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//menu
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
			
		//options
		final JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Options options=new Options();
				options.setVisible(true);
			}
		});
		mnMenu.add(mntmOptions);
	
	/*	
		//****************************************
		//DYNAMIC JBUTTONS
		final JButton[][] buttons=new JButton[Integer.parseInt(WIDTH)][LENGTH];
		
		for (int i=0;i<Integer.parseInt(WIDTH);i++){
			for(int j=0;j<LENGTH;j++){
				buttons[i][j]=new JButton("");
				buttons[i][j].setBounds(i*20+6,j*20+6,20,20);
				frame.getContentPane().add(buttons[i][j]);
			}
		}
		
		*/
		
		
		createButtons(DEFAULT_WIDTH,DEFAULT_LENGTH);

	
	}
	private void createButtons(String width,String length){
		final JButton[][] buttons=new JButton[Integer.parseInt(width)][Integer.parseInt(length)];
		
		frame.setBounds(100, 100, Integer.parseInt(width)*20+12, Integer.parseInt(length)*20+65);

		for (int i=0;i<Integer.parseInt(width);i++){
			for(int j=0;j<Integer.parseInt(length);j++){
				buttons[i][j]=new JButton("");
				buttons[i][j].setBounds(i*20+6,j*20+6,20,20);
				frame.getContentPane().add(buttons[i][j]);
			}
		}
		
	}
}
