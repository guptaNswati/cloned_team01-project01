package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.text.Caret;

import javafx.scene.text.Font;
import update.Update;

public class MainMenu implements ActionListener{
	private JFrame menuFrame;
	private JPanel menuPanel;
	private JPanel helpPanel;
	private JButton startButton;
	private JButton exitButton;
	private JButton rulesButton;
	private JButton helpButton;
	private JButton closeHelpButton;
	
	MainMenu()
	{
		menuFrame = new JFrame("Main Menu");		
		menuFrame.setBounds(0, 0, 400, 380);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		menuFrame.setLayout(new FlowLayout());
		
		 JLabel label = new JLabel("Welcome to Planet Hopper!!");
		label.setForeground(Color.BLUE);
		 menuFrame.add(label);
	
		menuPanel = new JPanel();
		menuPanel.setSize(new Dimension(menuFrame.getWidth(), menuFrame.getHeight()));	
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuPanel.setLayout(new GridLayout(1, 1));
		

		startButton = new JButton("Start");
		startButton.addActionListener(this);
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(this);

		rulesButton = new JButton("Rules");
		rulesButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		
		menuPanel.add(startButton);				
		menuPanel.add(helpButton);
		menuPanel.add(rulesButton);	
		menuPanel.add(exitButton);
		
		menuFrame.add(menuPanel);
		
		helpPanel = new JPanel();
		helpPanel.setPreferredSize(new Dimension(menuFrame.getWidth() - 100 , menuFrame.getHeight() - 140));
		helpPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		closeHelpButton = new JButton("Close");
		closeHelpButton.addActionListener(this);
		helpPanel.add(closeHelpButton, BorderLayout.SOUTH);
		
		menuFrame.add(helpPanel, BorderLayout.PAGE_END);			
		helpPanel.setVisible(false);
		
		menuFrame.setResizable(true);
		menuFrame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.startButton)
		{
			 Update update = new Update();	
			 update.run();
			 menuFrame.setVisible(false);
		}
		
		if (e.getSource() == this.exitButton)
		{
			menuFrame.dispose();			
		}
		
		if (e.getSource() == this.rulesButton)
		{
			System.out.println("rules");
			
		}
		
		if (e.getSource() == this.helpButton)
		{
			JTextArea instructionBox = new JTextArea();
			instructionBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));			
			instructionBox.setBackground(Color.BLACK);
			instructionBox.setForeground(Color.green);
			instructionBox.setText("Instructions: \n \n Press spacebar to launch ship and reset \n \n "
					+ "Press left key for moving left \n \n "
					+ "Press right key for moving right \n \n "
					+ "Press up key for moving up \n \n "
					+ "Press down key for moving down \n" );			
			instructionBox.setEditable(false);	
			
			helpPanel.add(instructionBox);						
			helpPanel.setVisible(true);		
			}
		
		if(e.getSource() == closeHelpButton)
		{
			helpPanel.setVisible(false);
			
		}		
	}
	
	public static void main(String[] args)
	{
		MainMenu menu = new MainMenu();
	}

}
