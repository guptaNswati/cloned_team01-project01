/**
 * GUI side panel.
 */

package information;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import update.Update;

public class SidePanel extends JPanel {
   /**
    * Scrollable container for infoTextBox.
    */
   private JScrollPane scrollPane;
   /**
    * Text box for info.
    */
   private JTextArea infoTextBox;
   /**
    * Container for fuel and thrust boxes.
    */
   private JPanel statPane;
   /**
    * A button to go to menu.
    */
   private JButton menuButton;
   /**
    * Text box to show fuel level.
    */
   private JTextField fuelBox;
   /**
    * Text box for thrust control pre-launch.
    */
   private JTextField thrustBox;

   /**
    * Initialize all members.
    * 
    * @param update
    *           The object where everything runs.
    */
   public SidePanel(Update update) {
      setLayout(new BorderLayout());
      initInfoTextBox();
      initStatPane();
      initMenuButton(update);
      initFuelBox();
      initThrustBox();
      initScrollPane();

      add(statPane, BorderLayout.SOUTH);
      statPane.add(menuButton, BorderLayout.NORTH);
      statPane.add(fuelBox, BorderLayout.WEST);
      statPane.add(thrustBox, BorderLayout.EAST);
      add(scrollPane, BorderLayout.NORTH);
   }

   /**
    * Initialize and stylize scroll pane.
    */
   private void initScrollPane() {
      scrollPane = new JScrollPane(infoTextBox);
      scrollPane.setPreferredSize(new Dimension(150, 600));
      scrollPane.getViewport().setBackground(Color.black);
      scrollPane.setBorder(null);
   }

   /**
    * Initialize and stylize text box.
    */
   private void initInfoTextBox() {
      infoTextBox = new JTextArea(8, 30);
      infoTextBox.setEditable(false);
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
    * Initialize and stylize stat pane.
    */
   private void initStatPane() {
      statPane = new JPanel(new BorderLayout());
      statPane.setBorder(BorderFactory.createLineBorder(Color.black, 3));
   }

   /**
    * Initialize and stylize menu button.
    * 
    * @param update
    *           The object where everything runs.
    */
   private void initMenuButton(Update update) {
      menuButton = new JButton("MENU");
      menuButton.setBorder(BorderFactory.createLineBorder(Color.black, 3));
      menuButton.setBackground(Color.white);
      menuButton.setOpaque(true);

      menuButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            update.getMenu().toggleIsShown();
            transferFocusBackward();
         }
      });
   }

   /**
    * Initialize and stylize fuel box.
    */
   private void initFuelBox() {
      fuelBox = new JTextField();
      fuelBox.setHorizontalAlignment(SwingConstants.CENTER);
      fuelBox.setBackground(Color.yellow);
      fuelBox.setPreferredSize(new Dimension(75, 50));
      fuelBox.setBorder(BorderFactory.createLineBorder(Color.black, 3));
      fuelBox.setFont(new Font("Arial", Font.BOLD, 22));
   }

   /**
    * Initialize and stylize thrust box.
    */
   private void initThrustBox() {
      thrustBox = new JTextField();
      thrustBox.setHorizontalAlignment(SwingConstants.CENTER);
      thrustBox.setBackground(Color.yellow);
      thrustBox.setPreferredSize(new Dimension(75, 50));
      thrustBox.setBorder(BorderFactory.createLineBorder(Color.black, 3));
      thrustBox.setFont(new Font("Arial", Font.BOLD, 22));
   }

   public JTextField getFuelBox() {
      return fuelBox;
   }

   public JTextField getThrustBox() {
      return thrustBox;
   }

   public JTextArea getInfoTextBox() {
      return infoTextBox;
   }
}
