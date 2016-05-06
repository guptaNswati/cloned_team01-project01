package physics;

import celestial.Celestial;
import celestial.Planet;
import ship.Ship;

/**
 * Methods to calculate planet and ship coordinates.
 */
public class Physics {
   public static void planetaryOrbit(final Celestial sun, Planet planet) {
      double dTheta = 2 * Math.PI / planet.getPeriodInMS() * Constants.TIME_INTERVAL;
      planet.setAngleToSun((planet.getAngleToSun() + dTheta) % (2 * Math.PI));
      
      // update (x,y) coordinate
      double newX, newY;
      newX = sun.getX()
            + planet.getDistanceToSun() * Math.cos(planet.getAngleToSun());
      newY = sun.getY()
            + planet.getDistanceToSun() * Math.sin(planet.getAngleToSun());
      planet.setCoordinate(newX, newY);
   }
   
   public static void shipGuideline(Ship ship, Planet[] planets, int frame) {
      
   }

   /**
    * Calculates ship momentum and coordinates while on planet and in flight.
    * <p>
    * On planet:
    * Coordinates are relative to planet, offset by the radius of the planet
    * in relation to the angle of the ship.
    * <p>
    * In flight:
    * Coordinates are 
    * 
    * @param ship
    * @param sun
    * @param planets
    */
   public static void shipFlight(Ship ship, Celestial sun, Planet[] planets) {
      if (ship.getOnCelestial()) {
         double newX, newY;
         newX = ship.getAttachedCelestial().getX()
               + (ship.getAttachedCelestial().getRadius() + ship.getRadius()) * Math.cos(ship.getAngle());
         newY = ship.getAttachedCelestial().getY()
               + (ship.getAttachedCelestial().getRadius() + ship.getRadius()) * Math.sin(ship.getAngle());
         ship.setCoordinate(newX, newY);
      }
      else {
         double addX, addY;
         double angleToSun = Math.atan2(ship.getY() - sun.getY(), ship.getX() - sun.getX());
         double gravityForceOfSun = Constants.GRAV_CONSTANT * sun.getMass()
               / Math.pow(Math.pow(ship.getX() - sun.getX(), 2) + Math.pow(ship.getY() - sun.getX(), 2), Constants.GRAV_FALLOFF);
         addX = ship.getThrust() * Math.cos(ship.getAngle()) + ship.getDX()
            - Math.cos(angleToSun) * gravityForceOfSun;
         addY = ship.getThrust() * Math.sin(ship.getAngle()) + ship.getDY()
            - Math.sin(angleToSun) * gravityForceOfSun;
         for (Planet planet : planets) {
            double angleToPlanet = Math.atan2(ship.getY() - planet.getY(), ship.getX() - planet.getX());
            double gravityForceOfPlanet = Constants.GRAV_CONSTANT * planet.getMass()
                  / Math.pow(Math.pow(ship.getX() - planet.getX(), 2) + Math.pow(ship.getY() - planet.getX(), 2), Constants.GRAV_FALLOFF);
            addX -= Math.cos(angleToPlanet) * gravityForceOfPlanet;
            addY -= Math.sin(angleToPlanet) * gravityForceOfPlanet;
         }
         ship.setMomentum(addX, addY);
         ship.setCoordinate(ship.getX() + addX, ship.getY() + addY);
         ship.setThrust(0);
      }
   }

   /**
    * Calculates the distance between a planet and a ship.
    * 
    * @param celestial
    * @param ship
    * @return
    */
   public static double distanceToPlanet(Celestial celestial, Ship ship) {
      return Math.sqrt(Math.pow(celestial.getX() - ship.getX(), 2) + Math.pow(celestial.getY() - ship.getY(), 2)
         - celestial.getRadius() - ship.getRadius());
  }
}
