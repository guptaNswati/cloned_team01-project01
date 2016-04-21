package physics;

import celestial.Celestial;
import celestial.Planet;

public class OrbitCalculation {
   public static void nextCoordinate(final Celestial sun, Planet planet) {
      double dTheta = 2 * Math.PI / planet.getPeriodInMS()
            * Constants.TIME_INTERVAL;
      planet.setAngleToSun(planet.getAngleToSun() + dTheta);
      // update (x,y) coordinate
      int dx, dy;
      dx = (int)(planet.getDistanceToSun() * Math.cos(planet.getAngleToSun()));
      dy = (int)(planet.getDistanceToSun() * Math.sin(planet.getAngleToSun()));
      int newX, newY;
      newX = sun.getX() + dx;
      newY = sun.getY() + dy;
      planet.setCoordinate(newX, newY);
   }
}
