package ship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ThrustBox extends JTextField {
   public ThrustBox(Ship ship) {
      super(String.format("%.1f", ship.getThrust()), 7);
      setHorizontalAlignment(SwingConstants.CENTER);
      setBackground(Color.yellow);
      setPreferredSize(new Dimension(20, 50));
      setFont(new Font("Arial", Font.BOLD, 17));
      addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String newStr = ((ThrustBox)e.getSource()).getText();
            double newValue = -1;
            try {
               newValue = Double.parseDouble(newStr);
            } catch (NumberFormatException ex) {}
            if (newValue < 0)
               setText(ship.getThrust());
            transferFocusBackward();
         }
      });
   }

   public void setText(double thrust) {
      setText(String.format("%.1f", thrust));
   }
}
