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

import ship.Ship;

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
    * Constructor that sets angle's position and image.
    * 
    * @param filename
    * @param coordinate
    */
   public Arrow(String filename, Ship ship) {
      coordinate = new Point2D.Double();
      try {
         image = ImageIO.read(new File(filename));
      } catch (IOException ex) {}
      width = 50;
      height = 6;
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
   public void draw(Graphics g, Ship ship) {
      final double ratio = 0.3;
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform trans = new AffineTransform();
      trans.translate(getX(), getY() - height / 2);
      trans.rotate(ship.getAngle(), 0, height / 2);
      trans.scale(width * (ship.getThrust() + 1.6) * ratio / image.getWidth(),
            height / (double)image.getHeight());
      AffineTransformOp op = new AffineTransformOp(trans,
            AffineTransformOp.TYPE_BILINEAR);
      g2d.drawImage(image, op, 0, 0);
   }
}
