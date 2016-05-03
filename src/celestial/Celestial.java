package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * An object of class Celestial has 4 members, coordinate, color, name, radius.
 * It has a default and a 4 parameter constructor, and getters and setters. It
 * has also a draw method to draw the celestial on GUI.
 */
public class Celestial {
   private Point2D coordinate;
   private Color color;
   private String name;
   private int radius;
   private double mass;

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
   public Celestial(Point2D.Double coordinate, Color color, String name, int radius, double mass) {
      this.setName(name);
      this.color = color;
      this.coordinate = new Point2D.Double();
      setCoordinate(coordinate.getX(), coordinate.getY());
      setRadius(radius);
      setMass(mass);
   }

   /**
    * Sets the color and draw a circle according to celestial members.
    * 
    * @param g
    *           A Graphics object that is passed in by paintComponent method.
    */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)coordinate.getX() - radius, (int)coordinate.getY() - radius, radius * 2,
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
   public void setCoordinate(double x, double y) {
      // TODO: validator
      coordinate.setLocation(x, y);
   }

   public double getRadius() {
      return radius;
   }

   public void setRadius(int radius) {
      this.radius = radius > 0 ? radius : 10;
   }

   public double getMass() {
      return mass;
   }

   public void setMass(double mass) {
      this.mass = mass > 0 ? mass : 1;
   }

   public Point2D getCoordinate(){
      return coordinate;
   }

   public double getX() {
      return coordinate.getX();
   }

   public double getY() {
      return coordinate.getY();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}