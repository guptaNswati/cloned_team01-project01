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
	private JButton closeHelp;
	JTextArea instructionTextBox;
	
	MainMenu()
	{
		menuFrame = new JFrame("Main Menu");		
		menuFrame.setBounds(0, 0, 400, 380);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		menuFrame.setLayout(new GridLayout(0, 1));
		
		 JLabel label = new JLabel("Welcome to Planet Hopper!!");
		label.setForeground(Color.BLUE);
		 menuFrame.add(label, BorderLayout.NORTH);
	
		menuPanel = new JPanel();
//		menuPanel.setSize(new Dimension(100, 50));	
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuPanel.setLayout(new FlowLayout());
		

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
		
		menuFrame.add(menuPanel, BorderLayout.CENTER);
		
		helpPanel = new JPanel();
//		helpPanel.setPreferredSize(new Dimension(menuFrame.getWidth()/2 , menuFrame.getHeight() - 200));
		helpPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		instructionTextBox = new JTextArea();
		instructionTextBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));			
		instructionTextBox.setBackground(Color.BLACK);
		instructionTextBox.setForeground(Color.green);
		instructionTextBox.setText("Instructions: \n \n Left/Right Arrow: Ship angle "
				+ "\n \n Up/Down Arrow: Ship thrust \n \n Space: Ship launch/reset\n" );			
		instructionTextBox.setEditable(false);		
		instructionTextBox.setVisible(false);		
		helpPanel.add(instructionTextBox);
		
		closeHelp = new JButton("Close");
		closeHelp.addActionListener(this);
		helpPanel.add(closeHelp, BorderLayout.PAGE_END);
		
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
			instructionTextBox.setVisible(true);
			helpPanel.setVisible(true);		
			}
		
		if(e.getSource() == closeHelp)
		{
			instructionTextBox.setVisible(false);
			helpPanel.setVisible(false);						
		}		
	}
	
	public static void main(String[] args)
	{
		MainMenu menu = new MainMenu();
	}

}
