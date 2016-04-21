package celestial;

import java.awt.Color;
import java.awt.Point;

public class Planet extends Celestial {
   private final int distanceToSun;
   private final int periodInMS;
   private double angleToSun;

   public Planet() {
      super();
      distanceToSun = 1;
      periodInMS = 1;
   }

   public Planet(Color color, String name, int radius, int distanceToSun,
         double angleToSun, int periodInMS) {
      super(new Point(), color, name, radius);
      this.distanceToSun = distanceToSun > 0 ? distanceToSun : 10;
      this.setAngleToSun(angleToSun);
      this.periodInMS = periodInMS > 1000 ? periodInMS : 1000;
      // set Planet's initial x and y
   }

   public int getDistanceToSun() {
      return distanceToSun;
   }

   public double getAngleToSun() {
      return angleToSun;
   }

   public void setAngleToSun(double angleToSun) {
      this.angleToSun = angleToSun;
   }

   public int getPeriodInMS() {
      return periodInMS;
   }
}
