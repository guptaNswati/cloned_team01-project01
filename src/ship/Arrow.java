package ship;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Arrow {
   private Point coordinate;
   private Image image;
   private int width;
   private int height;
   private double angle;

   public Arrow(String filename, Point coordinate) {
      setCoordinate(coordinate);
      image = new ImageIcon(filename).getImage();
      width = 50;
      height = 10;
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
      g2d.rotate(angle, coordinate.x, coordinate.y);
      g2d.drawImage(image, coordinate.x, coordinate.y - height / 2, width,
            height, imgOb);
   }

   public double getAngle() {
      return angle;
   }

   public void setAngle(double angle) {
      this.angle = angle;
   }

}
