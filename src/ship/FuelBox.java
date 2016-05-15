package ship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FuelBox extends JTextField {
   public FuelBox(Ship ship) {
      super(String.format("%.1f", ship.getThrust()), 7);
      setHorizontalAlignment(SwingConstants.CENTER);
      setBackground(Color.yellow);
      setPreferredSize(new Dimension(20, 50));
      setFont(new Font("Arial", Font.BOLD, 17));
      setFocusable(false);
      setEditable(false);
      addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            transferFocusBackward();
         }
      });
   }

   public void setText(double thrust) {
      setText(String.format("%.1f", thrust));
   }
}
