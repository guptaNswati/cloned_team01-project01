package ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import celestial.Celestial;
//import ship.Arrow.ArrowKeyControl;

public class Ship {
   // End of list is current position
   private LinkedList<Point2D> coordinate = new LinkedList<Point2D>();
   // Head of list is current position + 1 frame
   private LinkedList<Point2D> history = new LinkedList<Point2D>();
   // dx, dy are stored in coordinate form
   private LinkedList<Point2D> momentum = new LinkedList<Point2D>();
   private double thrust;
   private double fuel;
   private double angle;
   private int radius;
   private boolean onCelestial;
   private Celestial attachedCelestial;
   private Arrow arrow;
   private ThrustBox thrustInput;

   public Ship() {
      coordinate.push(new Point2D.Double(0, 100));
      momentum.push(new Point2D.Double(0, 0));
      thrust = 1.6;
      fuel = 100;
      angle = 0;
      radius = 2;
      onCelestial = true;
      arrow = new Arrow();
      thrustInput = new ThrustBox(this);
   }

   public void draw(Graphics g) {
      g.setColor(Color.white);
      g.fillOval((int)coordinate.getLast().getX() - 2,
            (int)coordinate.getLast().getY() - 2, 4, 4);
      double alpha = 1;
      for (Point2D coord : coordinate) {
         g.setColor(new Color(0, 160, 255, (int)alpha));
         g.fillOval((int)coord.getX(), (int)coord.getY(), 1, 1);
         alpha = alpha + 0.5;
      }
      if (!onCelestial) {
         alpha = 255;
         for (Point2D hist : history) {
            g.setColor(new Color(255, 255, 255, (int)alpha));
            g.fillOval((int)hist.getX(), (int)hist.getY(), 1, 1);
            alpha--;
         }
      }
      arrow.draw(g, this);
   }

   public Point2D getCoordinateFirst() {
      return coordinate.getFirst();
   }

   public Point2D getCoordinateLast() {
      return coordinate.getLast();
   }
   
   public int getCoordinateSize() {
      return coordinate.size();
   }

   public double getFirstX() {
      return coordinate.getFirst().getX();
   }

   public double getLastX() {
      return coordinate.getLast().getX();
   }

   public double getFirstY() {
      return coordinate.getFirst().getY();
   }

   public double getLastY() {
      return coordinate.getLast().getY();
   }

   public void setFirstCoordinate(double x, double y) {
      coordinate.push(new Point2D.Double(x, y));
   }

   public void setLastCoordinate(double x, double y) {
      coordinate.add(new Point2D.Double(x, y));
   }

   public void shiftCoordinate() {
      history.push(coordinate.removeLast());
      
      if (!onCelestial)
         momentum.removeLast();

      if (!onCelestial && history.size() > 255)
         history.removeLast();
      else if(onCelestial && history.size() != 0) {
         // Remove all but first coordinate when on planet
         // This is so ship's previous positions on planet
         // aren't drawn in "position history line"
         history.clear(); 
      }
   }

   public void resetCoordinate() {
      Point2D lastElement = coordinate.getLast();
      coordinate.clear();
      coordinate.add(lastElement);
   }

   public Point2D getHistory() {
      return history.getFirst();
   }

   public double getDX() {
      return momentum.getFirst().getX();
   }

   public double getDY() {
      return momentum.getFirst().getY();
   }

   public void setFirstMomentum(double x, double y) {
      momentum.push(new Point2D.Double(x, y));
   }

   public void setLastMomentum(double x, double y) {
      momentum.add(new Point2D.Double(x, y));
   }

   public void resetMomentum() {
      Point2D lastElement = momentum.getLast();
      momentum.clear();
      momentum.add(lastElement);
   }

   public double getThrust() {
      return thrust;
   }

   public void setThrust(double thrust) {
      this.thrust = thrust > 0 ? thrust : 0;
      thrustInput.setText(this.thrust);
   }

   public void changeThrust(double thrust) {
      double newThrust = this.thrust + thrust;
      if (newThrust >= 0 && newThrust <= 2.8) {
         this.thrust = newThrust;
         this.resetCoordinate();
         this.resetMomentum();
         thrustInput.setText(this.thrust);
         if (!onCelestial)
            expendFuel();
      }
   }

   public double getFuel() {
      return fuel;
   }

   public void expendFuel() {
      fuel -= fuel > 0 ? 1 : 0;
   }
   
   public void resetFuel() {
      fuel = 100;
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
      if (onCelestial) {
         setLastMomentum(0, 0);
         setThrust(1.6);
         resetFuel();
      }
   }

   public Celestial getAttachedCelestial() {
      return attachedCelestial;
   }

   public void setAttachedCelestial(Celestial Celestial) {
      attachedCelestial = Celestial;
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
            changeAngle(-0.15);
         if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            changeAngle(0.15);
         if (e.getKeyCode() == KeyEvent.VK_UP) { // increase power
            if (onCelestial)
               changeThrust(0.4);
            else if (fuel > 0)
               changeThrust(0.01);
         }
         if (e.getKeyCode() == KeyEvent.VK_DOWN) // decrease power
            changeThrust(-0.4);
         if (e.getKeyCode() == KeyEvent.VK_SPACE) // launch from planet
            setOnCelestial(!onCelestial);
      }
   }
   
   /**
    * @return the thrustInput box
    */
   public ThrustBox getThrustInput() {
      return thrustInput;
   }
}
