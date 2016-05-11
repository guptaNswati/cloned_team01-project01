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
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.text.Caret;

import com.sun.xml.internal.ws.api.server.Container;

import javafx.scene.text.Font;
import physics.Constants;
import update.Update;

public class MainMenu implements ActionListener{
	private JFrame menuFrame;
	private JPanel menuPanel;
	private JPanel helpPanel;
	private JButton startButton;
	private JButton exitButton;
	private JButton helpButton;
	private JButton closeHelp;
	JTextArea instructionTextBox;
	
	MainMenu()
	{
		menuFrame = new JFrame("Main Menu");		
		menuFrame.setBounds(0, 0, 350, 360);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		java.awt.Container contentPane = menuFrame.getContentPane();
	    SpringLayout layout = new SpringLayout();
	    contentPane.setLayout(layout);
		
		JLabel label = new JLabel("Welcome to Planet Hopper!!");
		label.setForeground(Color.BLUE);
		contentPane.add(label);
		
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(menuPanel);

	    layout.putConstraint(SpringLayout.NORTH, label, 40, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

	    layout.putConstraint(SpringLayout.NORTH, menuPanel, 90, SpringLayout.NORTH, contentPane);
	    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, menuPanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
	    
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		menuPanel.add(startButton);	
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(this);
		menuPanel.add(helpButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);	
		menuPanel.add(exitButton);		
		
		helpPanel = new JPanel();
		helpPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		helpPanel.setBackground(Color.BLACK);
		helpPanel.setPreferredSize(new Dimension(200, 180));
		contentPane.add(helpPanel);	
		
		helpPanel.setLayout(new BorderLayout());
		
		layout.putConstraint(SpringLayout.NORTH, helpPanel, 150, SpringLayout.NORTH, contentPane);
	    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, helpPanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
			    
		instructionTextBox = new JTextArea();
		instructionTextBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));			
		instructionTextBox.setBackground(Color.BLACK);
		instructionTextBox.setForeground(Color.green);
		instructionTextBox.setText("Instructions: \n \n Left/Right Arrow: Ship angle "
				+ "\n \n Up/Down Arrow: Ship thrust \n \n Space: Ship launch/reset \n" );			
		instructionTextBox.setEditable(false);		
		instructionTextBox.setVisible(false);		
		helpPanel.add(instructionTextBox);		
					
		closeHelp = new JButton("Close");
		closeHelp.addActionListener(this);
		helpPanel.add(closeHelp, "South");		
		
		helpPanel.setVisible(false);
		
		menuFrame.setResizable(true);
		menuFrame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.startButton)
		{
			JFrame frame = new JFrame("Game Window");
		      frame.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      
		      Update update = new Update();
		      frame.add(update);

		      frame.setResizable(true);
		      frame.setVisible(true);

		      frame.add(update);
		      update.setBackground(Color.black);
		      frame.add(BorderLayout.CENTER, update);

		      update.run();
			 menuFrame.setVisible(false);
		}
		
		if (e.getSource() == this.exitButton)
		{
			menuFrame.dispose();			
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
