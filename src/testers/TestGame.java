package testers;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import physics.Constants;
import update.Update;

/**
 * Testing class that tests Sprint 1 which is a running solar system.
 */
public class TestGame {
   public static void main(String[] args) {
      JFrame frame = new JFrame("Simple Window");
      frame.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Update update = new Update();
      frame.add(update);
      update.setBackground(Color.black);
      frame.add(BorderLayout.CENTER, update);
      frame.setVisible(true);

      update.run();
   }
}
