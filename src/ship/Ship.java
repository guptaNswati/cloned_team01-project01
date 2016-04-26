package ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import celestial.Celestial;

public class Ship {
   private LinkedList<Point> coordinate = new LinkedList<Point>();
   private double speed;
   private double angle;
   private boolean onCelestial;
   private Celestial attachedCelestial;

   public Ship() {
      coordinate.push(new Point(0, 0));
      speed = 0;
      angle = 0;
      onCelestial = true;
   }

   public void draw(Graphics g) {
      g.setColor(Color.black);
      g.fillOval(coordinate.getFirst().x - 2,
            coordinate.getFirst().y - 2, 4, 4);
   }

   public Point getCoordinate() {
      return coordinate.getFirst();
   }

   public void setCoordinate(int x, int y) {
      // TODO: validator
      coordinate.push(new Point(x, y));
      coordinate.removeLast();
   }

   public double getSpeed() {
      return speed;
   }

   public void setSpeed(double speed) {
      this.speed = speed > 0 ? speed : 0;
   }

   public double getAngle() {
      return angle;
   }

   public void setAngle(double angle) {
      this.angle = angle % (2 * Math.PI);
   }

   public boolean getOnCelestial() {
      return onCelestial;
   }
   
   public void setOnCelestial(boolean onCelestial) {
      this.onCelestial = onCelestial;
   }

   public Celestial getAttachedCelestial() {
      return attachedCelestial;
   }

   public void setAttachedCelestial(Celestial Celestial) {
      this.attachedCelestial = Celestial;
   }
}
