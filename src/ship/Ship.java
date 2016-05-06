package ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.ListIterator;

import celestial.Celestial;
//import ship.Arrow.ArrowKeyControl;

public class Ship {
   // Head of list is current position + 1
   private LinkedList<Point2D> guideline = new LinkedList<Point2D>();
   // Head of list is current position
   private LinkedList<Point2D> coordinate = new LinkedList<Point2D>();
   private ListIterator<Point2D> coordIter = coordinate.listIterator();
   // dx, dy are stored in coordinate form
   private Point2D momentum;
   private double thrust;
   private double fuel;
   private double angle;
   private int radius;
   private boolean onCelestial;
   private Celestial attachedCelestial;
   private Arrow arrow;

   public Ship() {
      coordinate.push(new Point2D.Double(0, 0));
      momentum = new Point2D.Double(0, 0);
      thrust = 1.2;
      fuel = 100;
      angle = 0;
      radius = 2;
      onCelestial = true;
      arrow = new Arrow("image/arrow-sample.png", coordinate.peekFirst());
   }

   public void draw(Graphics g) {
      g.setColor(Color.white);
      g.fillOval((int)coordinate.getFirst().getX() - 2,
            (int)coordinate.getFirst().getY() - 2, 4, 4);
      if(!onCelestial) {
         int alpha = 255;
         for(Point2D coord : coordinate) {
            g.setColor(new Color(255, 255, 255, alpha));
            g.fillOval((int)coord.getX(), (int)coord.getY(), 1, 1);
            alpha--;
         }
      }
      arrow.draw(g);
   }

   public Point2D getCoordinate() {
      return coordinate.getFirst();
   }

   public void setCoordinate(double x, double y) {
      coordinate.push(new Point2D.Double(x, y));
      
      if (!onCelestial && coordinate.size() > 255) {
         coordinate.removeLast();
      }
      else if(onCelestial && coordinate.size() > 1) {
         // Remove all but first coordinate when on planet
         // This is so ship's previous positions on planet
         // aren't drawn in "position history line"
         Point2D tempFirstCoord = coordinate.getFirst();
         coordinate.clear(); 
         coordinate.add(tempFirstCoord);
      }
   }

   public double getX() {
      return coordinate.getFirst().getX();
   }

   public double getY() {
      return coordinate.getFirst().getY();
   }

   public void setMomentum(double x, double y) {
      momentum.setLocation(x, y);
   }

   public double getDX() {
      return momentum.getX();
   }

   public double getDY() {
      return momentum.getY();
   }

   public double getThrust() {
      return thrust;
   }

   public void setThrust(double thrust) {
      this.thrust = thrust > 0 ? thrust : 0;
   }

   public void changeThrust(double thrust) {
      double newThrust = this.thrust + thrust;
      this.thrust = newThrust > 0 ? newThrust : 0;
   }

   public double getFuel() {
      return fuel;
   }

   public void expendFuel() {
      if (this.fuel > 0) {
         this.fuel -= this.thrust * 0.01;
         if (this.fuel <= 0) {
            this.fuel = 0;
            this.thrust = 0;
         }
      }
   }
   
   public void resetFuel() {
      this.fuel = 100;
   }

   public double getAngle() {
      return angle;
   }

   public void setAngle(double angle) {
      this.angle = angle % (2 * Math.PI);
   }

   public void changeAngle(double angle) {
      this.angle += angle % (2 * Math.PI);
   }

   public int getRadius() {
      return radius;
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
   
   public Arrow getArrow() {
      return arrow;
   }
   
   public KeyListener getShipKeyControl() {
      return new ShipKeyControl();
   }

   private class ShipKeyControl extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_LEFT)
            changeAngle(-0.3);
         if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            changeAngle(0.3);
         if (e.getKeyCode() == KeyEvent.VK_UP) // increase power
            changeThrust(0.1);
         if (e.getKeyCode() == KeyEvent.VK_DOWN) // decrease power
            changeThrust(-0.1);
         if (e.getKeyCode() == KeyEvent.VK_SPACE) { // launch from planet
            if (onCelestial) {
               onCelestial = false;
            }
            else {
               momentum.setLocation(0, 0);
               setThrust(1.2);
               resetFuel();
               onCelestial = true;
            }
         }
         arrow.setAngle(angle);
      }
   }
}