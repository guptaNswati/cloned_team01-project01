package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Celestial {
   private Point coordinate;
   private Color color;
   private String name;
   private int radius;

   public Celestial() {}

   public Celestial(Point coordinate, Color color, String name, int radius) {
      this.name = name;
      this.color = color;
      this.coordinate = new Point();
      setCoordinate(coordinate.x, coordinate.y);
      setRadius(radius);
   }

   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval(coordinate.x - radius, coordinate.y - radius, radius * 2,
            radius * 2);
   }

   public void updateCoordinate() {
      coordinate.x++;
      coordinate.y++;
   }

   public void setCoordinate(int x, int y) {
      // TODO: validator
      coordinate.x = x;
      coordinate.y = y;
   }

   public void setRadius(int radius) {
      this.radius = radius > 0 ? radius : 10;
   }

   public Point getCoordinate() {
      return coordinate;
   }

   public int getX() {
      return coordinate.x;
   }

   public int getY() {
      return coordinate.y;
   }

   public double getRadius() {
      return radius;
   }
}
