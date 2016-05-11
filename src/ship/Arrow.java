package ship;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * An arrow to draw on GUI. Also contains and inner private class for key
 * control.
 * 
 * @author TerryTsao
 */
public class Arrow extends JComponent {
   /**
    * Arrow's coordinate.
    */
   private Point2D coordinate;
   /**
    * Arrow's image.
    */
   private BufferedImage image;
   /**
    * Arrow's width.
    */
   private int width;
   /**
    * Arrow's height.
    */
   private int height;
   /**
    * Arrow's angle with respect to horizontal x to the right.
    */
   private double angle;
   /**
    * Arrow's thrust, which determine how long to draw.
    */
   private double thrust;

   /**
    * Constructor that sets angle's position and image.
    * 
    * @param filename
    * @param coordinate
    */
   public Arrow(String filename, Ship ship) {
      try {
         image = ImageIO.read(new File(filename));
      } catch (IOException ex) {}
      width = 50;
      height = 6;
      thrust = ship.getThrust();
   }

   @Override
   public int getX() {
      return (int)coordinate.getX();
   }

   @Override
   public int getY() {
      return (int)coordinate.getY();
   }

   public void setCoordinate(Point2D coordinate) {
      this.coordinate = coordinate;
   }

   public Point2D getCoordinate() {
      return coordinate;
   }

   /**
    * Transform the image with translation, rotation, and scale operations to
    * draw on screen.
    * 
    * @param g
    *           Graphics object for drawing.
    */
   public void draw(Graphics g) {
      final double ratio = 0.75;
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform trans = new AffineTransform();
      trans.translate(getX(), getY() - height / 2);
      trans.rotate(angle, 0, height / 2);
      trans.scale(width * thrust * ratio / image.getWidth(),
            height / (double)image.getHeight());
      AffineTransformOp op = new AffineTransformOp(trans,
            AffineTransformOp.TYPE_BILINEAR);
      g2d.drawImage(image, op, 0, 0);
   }

   public double getAngle() {
      return angle;
   }

   public void setAngle(double angle) {
      this.angle = angle;
   }

   /**
    * @return the thrust
    */
   public double getThrust() {
      return thrust;
   }

   /**
    * @param thrust
    *           the thrust to set
    */
   public void setThrust(double thrust) {
      final double min = 0.6;
      final double max = 3;
      if (thrust <= min)
         this.thrust = min;
      else if (thrust >= max)
         this.thrust = max;
      else
         this.thrust = thrust;
   }
}
