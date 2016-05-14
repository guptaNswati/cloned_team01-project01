package information;

import java.awt.BorderLayout;
import java.awt.Color;
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
   private JTextArea infoTextBox;
   private JScrollPane scrollPane;

   public SidePanel(Update update) {
      setLayout(new BorderLayout());
      thrustInput = update.getShip().getThrustInput();
      initInfoTextBox();
      initScrollPane();

      add(thrustInput, BorderLayout.SOUTH);
      add(scrollPane, BorderLayout.NORTH);
   }

   private void initScrollPane() {
      scrollPane = new JScrollPane(infoTextBox);
      scrollPane.setPreferredSize(new Dimension(150, 400));
      scrollPane.getViewport().setBackground(Color.black);
      scrollPane.setBorder(null);
   }

   private void initInfoTextBox() {
      infoTextBox = new JTextArea(8, 30);
      infoTextBox.setEditable(false);
      infoTextBox.setVisible(false);
      infoTextBox.setLineWrap(true);
      infoTextBox.setWrapStyleWord(true);
      infoTextBox.setMargin(new Insets(20, 0, 2, 20));
      infoTextBox.setBackground(Color.black);
      infoTextBox.setForeground(Color.white);
      infoTextBox.addKeyListener(new KeyListener() {
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
   public JTextArea getInfoTextBox() {
      return infoTextBox;
   }

   /**
    * @param jokeTextBox
    *           the jokeTextBox to set
    */
   public void setInfoTextBox(JTextArea infoTextBox) {
      this.infoTextBox = infoTextBox;
   }
}
