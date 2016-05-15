package celestial;

import java.awt.Color;
import java.awt.geom.Point2D;

import physics.Constants;

/**
 * An object of a Planet class inherits from Celestial. 
 * Three additional members, distanceToSun, periodInMS, angleToSun.
 */

public class Planet extends Celestial {
   private final int distanceToSun;
   private int periodInMS;
   private double angleToSun;

   /**
    * Dummy default constructor just in case.
    */
   public Planet() {
      super();
      distanceToSun = 1;
      setPeriodInMS(1);
   }

   /**
    * A constructor that initialize members.
    * 
    * @param color
    *           Color of planet.
    * @param name
    *           Name of planet.
    * @param radius
    *           Radius of planet.
    * @param mass
    *           Mass of planet.
    * @param distanceToSun
    *           Distance to Sun of planet.
    * @param angleToSun
    *           Angle to Sun of planet.
    * @param periodInMS
    *           Period of planet.
    */
   public Planet(Color color, String name, int radius, double mass,
         int distanceToSun, double angleToSun, int periodInMS) {
      super(new Point2D.Double(), color, name, radius, mass);
      this.distanceToSun = distanceToSun > 0 ? distanceToSun : 10;
      this.angleToSun = angleToSun;
      this.periodInMS = periodInMS > 1000 ? periodInMS : 1000;
      // set Planet's initial x and y
   }

   public int getDistanceToSun() {
      return distanceToSun;
   }

   public double getAngleToSun() {
      return angleToSun;
   }

   public void incrementAngleToSun() {
      double dTheta = 2 * Math.PI / periodInMS * Constants.TIME_INTERVAL;
      angleToSun = (angleToSun + dTheta) % (2 * Math.PI);
   }

   public int getPeriodInMS() {
      return periodInMS;
   }

   public void setPeriodInMS(int periodInMS) {
      this.periodInMS = periodInMS > 1000 ? periodInMS : 1000;
   }
}
