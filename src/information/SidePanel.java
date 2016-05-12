package information;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ship.Ship;

public class SidePanel extends JPanel {
   private JTextField thrustInput;
   private JTextArea jokeTextBox, infoTextBox;

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

   /**
    * @return the jokeTextBox
    */
   public JTextArea getJokeTextBox() {
      return jokeTextBox;
   }

   /**
    * @param jokeTextBox
    *           the jokeTextBox to set
    */
   public void setJokeTextBox(JTextArea jokeTextBox) {
      this.jokeTextBox = jokeTextBox;
   }

   /**
    * @return the infoTextBox
    */
   public JTextArea getInfoTextBox() {
      return infoTextBox;
   }

   /**
    * @param infoTextBox
    *           the infoTextBox to set
    */
   public void setInfoTextBox(JTextArea infoTextBox) {
      this.infoTextBox = infoTextBox;
   }
}
