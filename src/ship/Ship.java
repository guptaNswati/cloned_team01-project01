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
   private double thrust;
   private double fuel;
   private double angle;
   private boolean onCelestial;
   private Celestial attachedCelestial;

   public Ship() {
      coordinate.push(new Point2D.Double(0, 0));
      thrust = 0;
      fuel = 100;
      angle = 0;
      onCelestial = true;
   }

   public void draw(Graphics g) {
      g.setColor(Color.white);
      g.fillOval((int)coordinate.getFirst().getX() - 2,
            (int)coordinate.getFirst().getY() - 2, 4, 4);

      //draw position history line behind ship
      if(!onCelestial)
      {
         for(Point2D coord : coordinate)
         {
            g.fillOval((int)coord.getX(),
                  (int)coord.getY(), 2, 2);
         }
      }
      
      
         
      
   }

   public Point2D getCoordinate() {
      return coordinate.getFirst();
   }

   public void setCoordinate(double x, double y) {
      coordinate.push(new Point2D.Double(x, y));
      
      if (!onCelestial && coordinate.size() > 100) {
         coordinate.removeLast();
      }
      else if(onCelestial && coordinate.size() > 1) {
         //remove all but first coord when on planet
         //this is so ship's previous positions on planet aren't drawn in "position history line"
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

   public double getThrust() {
      return thrust;
   }

   public void setThrust(double thrust) {
      this.thrust = thrust >= 0 ? thrust : 0;
   }

   public void increaseThrust(double thrust) {
      this.thrust += thrust > 0 ? thrust : 0;
   }

   public void decreaseThrust(double thrust) {
      this.thrust -= thrust > 0 ? thrust : 0;
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

   public void increaseAngle(double angle) {
      this.angle += angle % (2 * Math.PI);
   }

   public void decreaseAngle(double angle) {
      this.angle -= angle % (2 * Math.PI);
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
   
   
   public KeyListener getShipKeyControl() {
      return new ShipKeyControl();
   }

   private class ShipKeyControl extends KeyAdapter {
      private int keyStrokePerPI = 30;

      @Override
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_LEFT && onCelestial)
            angle -= Math.PI / keyStrokePerPI;
         if (e.getKeyCode() == KeyEvent.VK_RIGHT && onCelestial)
            angle += Math.PI / keyStrokePerPI;
         if(e.getKeyCode() == KeyEvent.VK_UP && onCelestial) //increase power
            increaseThrust(1);
         if(e.getKeyCode() == KeyEvent.VK_DOWN && onCelestial) //decrease power
            decreaseThrust(1);
         if(e.getKeyCode() == KeyEvent.VK_SPACE ) { //launch from planet
            if(onCelestial){
               onCelestial = false;
            }
            else {
               onCelestial = true;
               setThrust(1);
               resetFuel();
            }
         }
      }
   }
}