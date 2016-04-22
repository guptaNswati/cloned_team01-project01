package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An object of class Celestial has 4 members, coordinate, color, name, radius.
 * It has a default and a 4 parameter constructor, and getters and setters. It
 * has also a draw method to draw the celestial on GUI.
 */
public class Celestial {
   private Point coordinate;
   private Color color;
   private String name;
   private int radius;

   /**
    * A default parameter that does nothing.
    */
   public Celestial() {}

   /**
    * A 4-parameter constructor that initializes Celestial class.
    * 
    * @param coordinate
    *           Coordinate of a celestial
    * @param color
    *           Color of a celestial
    * @param name
    *           The name of a celestial
    * @param radius
    *           The radius of a celestial
    */
   public Celestial(Point coordinate, Color color, String name, int radius) {
      this.name = name;
      this.color = color;
      this.coordinate = new Point();
      setCoordinate(coordinate.x, coordinate.y);
      setRadius(radius);
   }

   /**
    * Sets the color and draw a circle according to celestial members.
    * 
    * @param g
    *           A Graphics object that is passed in by paintComponent method.
    */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval(coordinate.x - radius, coordinate.y - radius, radius * 2,
            radius * 2);
   }

   /**
    * Setter for coordinate.
    * 
    * @param x
    *           x coordinate.
    * @param y
    *           y coordinate.
    */
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
