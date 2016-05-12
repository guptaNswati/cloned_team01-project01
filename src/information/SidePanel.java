package information;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import update.Update;

public class SidePanel extends JPanel {
   private JTextField thrustInput;
   private JTextArea jokeTextBox, infoTextBox;
   private JScrollPane scrollPane;

   public SidePanel(Update update) {
      setLayout(new BorderLayout());
      thrustInput = update.getShip().getThrustInput();
      initJokeTextBox();
      initScrollPane();

      add(thrustInput, BorderLayout.SOUTH);
      add(scrollPane, BorderLayout.NORTH);
   }

   private void initScrollPane() {
      scrollPane = new JScrollPane(jokeTextBox);
      scrollPane.setPreferredSize(new Dimension(150, 200));
   }

   private void initJokeTextBox() {
      jokeTextBox = new JTextArea(8, 30);
      jokeTextBox.setEditable(false);
      jokeTextBox.setVisible(false);
      jokeTextBox.setLineWrap(true);
      jokeTextBox.setWrapStyleWord(true);
      jokeTextBox.setMargin(new Insets(2, 2, 2, 2));
      jokeTextBox.addKeyListener(new KeyListener() {
         @Override
         public void keyTyped(KeyEvent e) {
            transferFocusBackward();
         }
         @Override
         public void keyReleased(KeyEvent e) {
            transferFocusBackward();
         }
         @Override
         public void keyPressed(KeyEvent e) {
            transferFocusBackward();
         }
      });
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
