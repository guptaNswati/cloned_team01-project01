package ship;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Arrow {
   private Point coordinate;
   private Image image;
   private int width;
   private int height;

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
      g.drawImage(image, coordinate.x, coordinate.y - height / 2, width, height,
            imgOb);
   }
}
