package ship;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import celestial.Celestial;
//import ship.Arrow.ArrowKeyControl;
import physics.Constants;

/**
 * The ship class contains data used for physics calculations on itself,
 * and methods to manipulate that data.
 * 
 * @author Joshua Fan
 */
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

   /**
    * Draw ship, guideline, history line,
    * thrust arrow, and off-screen arrow.
    * 
    * @param g
    */
   public void draw(Graphics g) {
      g.setColor(Color.white);
      g.fillOval((int)coordinate.getLast().getX() - 2,
            (int)coordinate.getLast().getY() - 2, 4, 4);
      double alpha = 1;
      for (Point2D coord : coordinate) {
         g.setColor(new Color(0, 255, 255, (int)alpha));
         g.fillOval((int)coord.getX(), (int)coord.getY(), 1, 1);
         alpha = alpha + 0.5;
      }
      if (!onCelestial) {
         alpha = 255;
         for (Point2D hist : history) {
            g.setColor(new Color(255, 255, 255, (int)alpha));
            g.fillOval((int)hist.getX(), (int)hist.getY(), 1, 1);
            alpha = alpha - 0.2;
         }
      }
      arrow.draw(g, this);
      if (distanceFromSun() > 470)
         drawOffScreenArrow(g);
   }

   /**
    * Head of guideline
    */
   public Point2D getCoordinateFirst() {
      return coordinate.getFirst();
   }

   /**
    * Tail of guideline, which is ship's current coordinate
    */
   public Point2D getCoordinateLast() {
      return coordinate.getLast();
   }
   
   public int getCoordinateSize() {
      return coordinate.size();
   }

   /**
    * @return x coordinate farthest in the future
    */
   public double getFirstX() {
      return coordinate.getFirst().getX();
   }

   /**
    * @return current ship x coordinate
    */
   public double getLastX() {
      return coordinate.getLast().getX();
   }

   /**
    * @return y coordinate farthest in the future
    */
   public double getFirstY() {
      return coordinate.getFirst().getY();
   }

   /**
    * @return current ship y coordinate
    */
   public double getLastY() {
      return coordinate.getLast().getY();
   }

   public void setFirstCoordinate(double x, double y) {
      coordinate.push(new Point2D.Double(x, y));
   }

   public void setLastCoordinate(double x, double y) {
      coordinate.add(new Point2D.Double(x, y));
   }

   /**
    * Shift last coordinate of guideline into history.
    * Trim guideline, momentum, and history linked lists.
    */
   public void shiftCoordinate() {
      history.push(coordinate.removeLast());
      
      if (!onCelestial)
         momentum.removeLast();

      if (!onCelestial && history.size() > 1275)
         history.removeLast();
      else if(onCelestial && history.size() != 0) {
         // Clear history when on planet
         // so ship's previous positions on planet
         // aren't drawn in "position history line"
         history.clear(); 
      }
   }

   /**
    * Clear guideline, leaving only the current coordinate.
    */
   public void resetCoordinate() {
      Point2D lastElement = coordinate.getLast();
      coordinate.clear();
      coordinate.add(lastElement);
   }

   public Point2D getHistory() {
      return history.getFirst();
   }

   /**
    * @return x component of momentum farthest in the future
    */
   public double getDX() {
      return momentum.getFirst().getX();
   }

   /**
    * @return y component of momentum farthest in the future
    */
   public double getDY() {
      return momentum.getFirst().getY();
   }

   /**
    * Set momentum farthest in the future.
    * 
    * @param x
    * @param y
    */
   public void setFirstMomentum(double x, double y) {
      momentum.push(new Point2D.Double(x, y));
   }

   /**
    * Set current momentum.
    * 
    * @param x
    * @param y
    */
   public void setLastMomentum(double x, double y) {
      momentum.add(new Point2D.Double(x, y));
   }

   /**
    * Clear momentum prediction, leaving only the current momentum.
    */
   public void resetMomentum() {
      Point2D lastElement = momentum.getLast();
      momentum.clear();
      momentum.add(lastElement);
   }

   public double getThrust() {
      return thrust;
   }

   public void setThrust(double thrust) {
      if (thrust >= 0 && thrust <= 2.8) {
         this.thrust = thrust;
         thrustInput.setText(thrust);
      }
   }

   /**
    * If thrust changes, clear future coordinates and momentum
    * so guideline can be rebuilt. Expend fuel if in flight.
    * 
    * @param thrust
    */
   public void changeThrust(double thrust) {
      double newThrust = this.thrust + thrust;
      if (newThrust >= 0 && newThrust <= 2.8) {
         this.thrust = newThrust;
         this.resetCoordinate();
         this.resetMomentum();
         thrustInput.setText(newThrust);
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

   /**
    * When ship "lands" (collides) on planet,
    * reinitialize ship state and clear momentum.
    * 
    * @param onCelestial
    */
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

   private double distanceFromSun() {
      return Math.sqrt(Math.pow(getLastX() - Constants.INIT_SUN_X, 2)
                     + Math.pow(getLastY() - Constants.INIT_SUN_Y, 2));
   }

   /**
    * Draw arrow that points to ship while it is off screen.
    * 
    * @param g
    */
   private void drawOffScreenArrow(Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();

      AffineTransform trans = AffineTransform.getTranslateInstance(
            Constants.INIT_SUN_X, Constants.INIT_SUN_Y);
      trans.concatenate(AffineTransform.getRotateInstance(
            Math.atan2(getLastY() - Constants.INIT_SUN_Y, getLastX() - Constants.INIT_SUN_X)));
      g2d.transform(trans);

      g2d.setColor(Color.white);
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {470, 464, 464, 470},
                      new int[] {0, -6, 6, 0}, 4);
   }

   public ThrustBox getThrustInput() {
      return thrustInput;
   }
}
