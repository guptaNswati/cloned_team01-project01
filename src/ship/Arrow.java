package ship;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * An arrow to draw on GUI. Also contains and inner private class for key
 * control.
 * 
 * @author Terry Tsao
 */
public class Arrow extends JComponent {
   private int size;

   /**
    * Constructor that sets arrow size.
    */
   public Arrow() {
      size = 4;
   }

   /**
    * Draw a vector arrow from the ship.
    * Magnitude and angle indicate ship thrust.
    * 
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g
    * @param ship
    */
   void draw(Graphics g, Ship ship) {
      Graphics2D g2d = (Graphics2D) g.create();

      int len = (int)(ship.getThrust() * 10 + size + 3);
      AffineTransform trans = AffineTransform.getTranslateInstance(ship.getLastX(), ship.getLastY());
      trans.concatenate(AffineTransform.getRotateInstance(ship.getAngle()));
      g2d.transform(trans);

      // Draw horizontal arrow starting at (6, 0)
      g2d.setColor(Color.red);
      g2d.setStroke(new BasicStroke(2));
      g2d.drawLine(6, 0, len, 0);
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {len+2, len-size+2, len-size+2, len+2},
                      new int[] {0, -size, size, 0}, 4);
  }
}
