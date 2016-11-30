package edu.cuny.csi.csc330.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Options extends JDialog {
	
		private final JPanel contentPanel = new JPanel();
		private JTextField txtHeight;
		private JTextField txtWidth;
		private JTextField txtMines;
		private JRadioButton easy = new JRadioButton("Easy");
		private JRadioButton medium = new JRadioButton("Medium");
		private JRadioButton hard = new JRadioButton("Hard");
		private JRadioButton custom = new JRadioButton("Custom");
		private ButtonGroup group = new ButtonGroup();
		private JButton OK;
		
		/**
		 * Create the dialog.
		 */
		public Options(OKButton ok) {
			setAlwaysOnTop(true);
			setResizable(false);
			setBounds(100, 100, 235, 230);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			JLabel lblLength = new JLabel("Height");
			lblLength.setBounds(20, 90, 61, 16);
			contentPanel.add(lblLength);
			
			JLabel lblWidth = new JLabel("Width");
			lblWidth.setBounds(20, 120, 61, 16);
			contentPanel.add(lblWidth);
			
			JLabel numMines = new JLabel("Mines");
			numMines.setBounds(20, 150, 61, 16);
			contentPanel.add(numMines);	
		
			txtHeight = new JTextField();
			txtHeight.setBounds(79, 84, 134, 28);
			txtHeight.setEnabled(false);
			txtHeight.setBackground(Color.GRAY);
			contentPanel.add(txtHeight);
		
			txtWidth = new JTextField();
			txtWidth.setBounds(79, 114, 134, 28);
			txtWidth.setEnabled(false);
			txtWidth.setBackground(Color.GRAY);
			contentPanel.add(txtWidth);
			
			txtMines = new JTextField();
			txtMines.setBounds(79, 144, 134, 28);
			txtMines.setEnabled(false);
			txtMines.setBackground(Color.GRAY);
			contentPanel.add(txtMines);
			
			OK = ok;
			
			ActionListener click = new ActionListener(){
				public void actionPerformed (ActionEvent e){
					Object x = e.getSource();
					if(x == easy){
						txtHeight.setEnabled(false);
						txtWidth.setEnabled(false);
						txtMines.setEnabled(false);
						txtHeight.setBackground(Color.GRAY);
						txtWidth.setBackground(Color.GRAY);
						txtMines.setBackground(Color.GRAY);
					}
					else if (x == medium){
						txtHeight.setEnabled(false);
						txtWidth.setEnabled(false);
						txtMines.setEnabled(false);
						txtHeight.setBackground(Color.GRAY);
						txtWidth.setBackground(Color.GRAY);
						txtMines.setBackground(Color.GRAY);
					}
					else if (x == hard){
						txtHeight.setEnabled(false);
						txtWidth.setEnabled(false);
						txtMines.setEnabled(false);
						txtHeight.setBackground(Color.GRAY);
						txtWidth.setBackground(Color.GRAY);
						txtMines.setBackground(Color.GRAY);
					}
					else if (x == custom){
						txtHeight.setEnabled(true);
						txtWidth.setEnabled(true);
						txtMines.setEnabled(true);
						txtHeight.setBackground(Color.WHITE);
						txtWidth.setBackground(Color.WHITE);
						txtMines.setBackground(Color.WHITE);
					}
				}
			};
			
			easy.setBounds(25, 17, 100, 28);
			medium.setBounds(25, 45, 100, 28);
			hard.setBounds(125, 17, 100, 28);
			custom.setBounds(125, 45, 100, 28);
			easy.addActionListener(click);
			medium.addActionListener(click);
			hard.addActionListener(click);
			custom.addActionListener(click);
			group.add(easy);
			group.add(medium);
			group.add(hard);
			group.add(custom);
			contentPanel.add(easy);
			contentPanel.add(medium);
			contentPanel.add(hard);
			contentPanel.add(custom);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				OK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						OKButton okay = (OKButton)e.getSource();
						if (custom.isSelected()==true){
							if(txtWidth.getText() != null && txtHeight.getText() != null && txtMines.getText() != null){
								okay.tmpWidth=Integer.parseInt(txtWidth.getText());
								okay.tmpHeight=Integer.parseInt(txtHeight.getText());
								okay.tmpMines=Integer.parseInt(txtMines.getText());
							}
						}
						else if (easy.isSelected()==true){
							okay.tmpWidth=10;
							okay.tmpHeight=10;
							okay.tmpMines=10;
						}
						else if (medium.isSelected()==true){
							okay.tmpWidth=16;
							okay.tmpHeight=16;
							okay.tmpMines=40;
						}
						else if (hard.isSelected()==true){
							okay.tmpWidth=32;
							okay.tmpHeight=16;
							okay.tmpMines=99;
						}
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(OK);
				getRootPane().setDefaultButton(OK);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(cancelButton);			
			}		
		}
	}
	