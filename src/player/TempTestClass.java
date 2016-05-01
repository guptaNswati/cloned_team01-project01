package player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import physics.Constants;


/**
 * Testing class that tests Sprint 1 which is a running solar system.
 */
public class TempTestClass {
   public static void main(String[] args) {
       JFrame frame = new JFrame("Solar System");
       frame.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       GameFrame game = new GameFrame();
       frame.add(game);
       game.setBackground(Color.black);
       frame.add(BorderLayout.CENTER, game);    
       frame.setResizable(true);
       frame.setVisible(true);

       game.run();
   }
}