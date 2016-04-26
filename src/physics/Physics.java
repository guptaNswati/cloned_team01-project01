package physics;

import celestial.Celestial;
import celestial.Planet;
import ship.Ship;

/**
 * Has static method to update planets' coordinations.
 */
public class Physics {
   public static void planetaryOrbit(final Celestial sun, Planet planet) {
      double dTheta = 2 * Math.PI / planet.getPeriodInMS()
            * Constants.TIME_INTERVAL;
      planet.setAngleToSun((planet.getAngleToSun() + dTheta) % (2 * Math.PI));
      // update (x,y) coordinate
      int newX, newY;
      newX = (int)(sun.getX()
            + planet.getDistanceToSun() * Math.cos(planet.getAngleToSun()));
      newY = (int)(sun.getY()
            + planet.getDistanceToSun() * Math.sin(planet.getAngleToSun()));
      planet.setCoordinate(newX, newY);
   }
   
   public static void shipFlight(Ship ship, Planet[] planets) {
      int newX, newY;
      if (ship.getOnCelestial()) {
         newX = (int) (ship.getAttachedCelestial().getX()
               + (ship.getAttachedCelestial().getRadius() + 2) * Math.cos(ship.getAngle()));
         newY = (int) (ship.getAttachedCelestial().getY()
               + (ship.getAttachedCelestial().getRadius() + 2) * Math.sin(ship.getAngle()));
      }
      else {
         newX = (int) (ship.getCoordinate().getX());
         newY = (int) (ship.getCoordinate().getY());
         for (Planet planet : planets) {
            newX -= (int) (Constants.GRAVITATIONAL_CONSTANT * planet.getMass()
                  / Math.pow(ship.getCoordinate().getX() - planet.getX(), 2));
            newY -= (int) (Constants.GRAVITATIONAL_CONSTANT * planet.getMass()
                  / Math.pow(ship.getCoordinate().getY() - planet.getY(), 2));
         }
      }
      ship.setCoordinate(newX, newY);
   }
}
