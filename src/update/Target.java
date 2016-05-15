package update;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import celestial.Planet;

public class Target {
   /**
    * Rotation speed of target icon.
    */
   private static final double ROT_SPEED = 0.03;

   /**
    * Stores the image of target.
    */
   private BufferedImage image;
   /**
    * Size of the icon.
    */
   private double size;
   /**
    * Coordinate of the icon.
    */
   private Point2D coordinate;
   /**
    * Angle of the icon.
    */
   private double angle;

   /**
    * Initialize all members with default value.
    */
   public Target() {
      coordinate = new Point2D.Double();
      size = 1;
      angle = 0;
      try {
         image = ImageIO.read(new File("resources/Target.png"));
      } catch (IOException ex) {}
   }

   /**
    * Reset target member values according to the planet's relevant value.
    * 
    * @param planet
    *           The planet the target is on.
    */
   public void resetTarget(Planet planet) {
      size = planet.getRadius() * 3;
      coordinate = planet.getCoordinate();
      angle -= ROT_SPEED; // rotation speed
   }

   /**
    * Draw the target on GUI.
    * 
    * @param g
    *           Graphics object to draw on.
    * @param imgOb
    *           Image observer.
    */
   public void draw(Graphics g, ImageObserver imgOb) {
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform trans = new AffineTransform();
      trans.translate(coordinate.getX() - size / 2,
                      coordinate.getY() - size / 2);
      trans.rotate(angle, size / 2, size / 2);
      trans.scale(size / image.getWidth(),
                  size / image.getHeight());
      AffineTransformOp op =
            new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
      g2d.drawImage(image, op, 0, 0);
   }
}
