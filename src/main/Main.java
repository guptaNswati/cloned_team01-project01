package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import information.SidePanel;
import physics.Constants;
import update.Update;

/**
 * Main class that initializes game panes.
 */
public class Main {
   public static void main(String[] args) {
      JFrame frame = new JFrame("Planet Hopper");
      frame.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
      frame.setMinimumSize(new Dimension(500, 500));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Update update = new Update();
      SidePanel sidePanel = new SidePanel(update);
      update.linkWithSidePanel(sidePanel);

      frame.setResizable(true);
      frame.setVisible(true);

      frame.add(update, BorderLayout.CENTER);
      update.setBackground(Color.black);
      frame.add(sidePanel, BorderLayout.EAST);
      sidePanel.setBackground(Color.black);

      update.run();
   }
}
