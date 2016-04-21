package testers;

import javax.swing.JFrame;

import celestial.SolarSystem;
import physics.Constants;;

public class TestSolarSystem {
   public static void main(String[] args) {
      JFrame frame = new JFrame("Simple Window");
      frame.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SolarSystem solarSystem = new SolarSystem();
      frame.add(solarSystem);
      frame.setResizable(false);
      frame.setVisible(true);

      solarSystem.run();
   }
}
