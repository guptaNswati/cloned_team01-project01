import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.geom.Ellipse2D;
/**
   This class implements a circle and a boolean function to
   test if a user-given point is inside this circle.
*/
public class Circle extends JPanel
{
   private double xCenter;
   private double yCenter;
   private double radius;
   private Color color;
   /**
      Constructs a circle.
      @param x the x-coordinate of the center
      @param y the y-coordinate of the center
      @param r the radius
      @param aColor the color
   */
   public Circle(double x, double y, double r, Color aColor)
   {
      xCenter = x;
      yCenter = y;
      radius = r;
      color = aColor;
   }
   /**
      Draws a circle and a point.
      @param g2 the graphics content
   */
   public void draw(Graphics2D g2)
   {
      g2.setColor(color);
      Ellipse2D.Double circle
         = new Ellipse2D.Double(xCenter - radius, yCenter - radius,
            2 * radius, 2 * radius);
      g2.draw(circle);
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      this.draw(g2);
   }
}