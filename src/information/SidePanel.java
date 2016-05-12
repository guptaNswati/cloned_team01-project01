package information;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ship.Ship;

public class SidePanel extends JPanel {
   private JTextField thrustInput;

   public SidePanel(Ship ship) {
      setLayout(new BorderLayout());
      setThrustInput(ship.getThrustInput());
      add(thrustInput, BorderLayout.SOUTH);
   }

   /**
    * @return the thrustInput
    */
   public JTextField getThrustInput() {
      return thrustInput;
   }

   /**
    * @param thrustInput
    *           the thrustInput to set
    */
   public void setThrustInput(JTextField thrustInput) {
      this.thrustInput = thrustInput;
   }
}
