package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import physics.Constants;
import update.Update;

public class Menu extends JPanel implements ActionListener{
	
	private JButton startButton;
	private JButton exitButton;
	private JButton rulesButton;
	private JButton helpButton;
	private boolean helpButtonBool;
	private JPanel helpPanel;
	
	Menu()
	{
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Welcome to Planet Hopper!!");
		 label.setBounds(50, 30, this.getWidth()/2, this.getHeight()/4);
		this.add(label);
	
		startButton = new JButton("Start");
		startButton.setPreferredSize(new Dimension(30, 25));
		startButton.addActionListener(this);
		
		helpButton = new JButton("Help");
		helpButton.setPreferredSize(new Dimension(20, 25));
		helpButton.addActionListener(this);

		 rulesButton = new JButton("Rules");
		rulesButton.setPreferredSize(new Dimension(20, 25));
		rulesButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(20, 25));
		exitButton.addActionListener(this);
		
	
		helpButtonBool = false;
		
		this.add(startButton);				
		this.add(helpButton);
		this.add(rulesButton);	
		this.add(exitButton);
		
		helpPanel = new JPanel();
		helpPanel.setPreferredSize(new Dimension(this.getWidth()/4, this.getHeight()/2));
		this.add(helpPanel);
		helpPanel.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.startButton)
		{
			
		}
		
		if (e.getSource() == this.exitButton)
		{
			System.out.println("bye");
			
			this.setVisible(false);
			
		}
		
		if (e.getSource() == this.rulesButton)
		{
			System.out.println("rules");
			
		}
		
		if (e.getSource() == this.helpButton)
		{
//			helpButtonBool = true;
			JTextArea textBox = new JTextArea();
			
			textBox.setBackground(Color.cyan);
			textBox.setText("Instructions: \n \n"  +  "Press spacebar to launch ship \n \n" 
			+ "Press left key for moving left \n \n" + "Press right key for moving right \n \n" 
					+ "Press up key for moving up \n \n" + "Press down key for moving down \n" );
			
			textBox.setEditable(false);
			this.helpPanel.add(textBox);
			this.helpPanel.setVisible(true);

		}		
		
	}
	
	public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      Graphics2D g2d = (Graphics2D)g;
	      
	      if(helpButtonBool == true)
	      {
	    	  g2d.drawRect(50, 50, 20, 20);
	    	  
	    	  g2d.drawString("Heloo", 50, 50);
	    	  
	    	 
	    	    java.awt.Font font = g2d.getFont();
	    	    FontRenderContext context = g2d.getFontRenderContext();
	    	    g2d.setFont(font);
	    	    int textWidth = (int) font.getStringBounds("Hello", context).getWidth();
	    	    LineMetrics ln = font.getLineMetrics(TOOL_TIP_TEXT_KEY, context);
	    	    int textHeight = (int) (ln.getAscent() + ln.getDescent());
	    	    int x1 = 50 + (40 - textWidth)/2;
	    	    int y1 = (int)(40 + (20 + textHeight)/2 - ln.getDescent());

	    	    g2d.setColor(Color.red);

	    	    g2d.drawString(TOOL_TIP_TEXT_KEY, (int) x1, (int) y1);
	    	
	      }
	      
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Main Menu");
	      frame.setBounds(0, 0, 450, 400);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
		Menu menu = new Menu();
		menu.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
	      
	      frame.add(menu);

	      frame.setResizable(true);
	      frame.setVisible(true);

	      
	      frame.add(BorderLayout.CENTER, menu);
	}

}
