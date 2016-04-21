package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Celestial {
   private Point coordinate;
   private Color color;
   private String name;
   private double radius;

   public Celestial() {}

   public Celestial(Point coordinate, Color color, String name, double radius) {
      this.name = name;
      this.color = color;
      setCoordinate(coordinate);
      setRadius(radius);
   }

   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval(coordinate.x, coordinate.y, (int)radius * 2, (int)radius * 2);
   }

   public void updateCoordinate() {
      coordinate.x++;
      coordinate.y++;
   }

   public void setCoordinate(Point coordinate) {
      // TODO: validator
      this.coordinate = new Point(coordinate.x, coordinate.y);
   }

   public void setRadius(double radius) {
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
