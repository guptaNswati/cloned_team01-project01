package ship;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arrow {
   private Point coordinate;
   private BufferedImage image;
   private int width;
   private int height;
   private double angle;

   public Arrow(String filename, Point coordinate) {
      setCoordinate(coordinate);
      try {
         image = ImageIO.read(new File(filename));
      } catch (IOException ex) {}
      width = 50;
      height = 8;
   }

   public int getX() {
      return coordinate.x;
   }

   public int getY() {
      return coordinate.y;
   }

   public void setCoordinate(Point coordinate) {
      this.coordinate = coordinate;
   }

   public Point getCoordinate() {
      return coordinate;
   }

   public void draw(Graphics g, ImageObserver imgOb) {
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform trans = new AffineTransform();
      trans.translate(getX(), getY() - height / 2);
      trans.rotate(angle, 0, height / 2);
      trans.scale(width / (double)image.getWidth(),
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

}
