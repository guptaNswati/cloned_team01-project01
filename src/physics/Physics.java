package physics;

import java.awt.geom.Point2D;

import celestial.Celestial;
import celestial.Planet;
import ship.Ship;

/**
 * Methods to calculate planet coordinates,
 * and ship coordinates, momentum, and collisions.
 * 
 * @author Joshua Fan
 */
public class Physics {
   /**
    * Calculates the coordinates of a planet at a given number of frames
    * into the future.
    * 
    * @param sun
    * @param planet
    * @param frame
    * @return
    */
   public static Point2D planetaryOrbit(final Celestial sun, Planet planet, int frame) {
      double dTheta = 2 * Math.PI / planet.getPeriodInMS() * Constants.TIME_INTERVAL;
      double angleToSun = (planet.getAngleToSun() + dTheta * frame) % (2 * Math.PI);

      // update (x,y) coordinate
      double newX, newY;
      newX = sun.getX() + planet.getDistanceToSun() * Math.cos(angleToSun);
      newY = sun.getY() + planet.getDistanceToSun() * Math.sin(angleToSun);
      return new Point2D.Double(newX, newY);
   }

   /**
    * Calculates ship momentum and coordinates while on planet and in flight.
    * <p>
    * On planet:
    * Coordinates are relative to planet, offset by the radius of the planet
    * in relation to the angle of the ship.
    * <p>
    * Build guideline and pass current coordinates to history.
    * 
    * @param ship
    * @param sun
    * @param planets
    */
   public static void shipFlight(Ship ship, Celestial sun, Planet[] planets) {
      if (ship.getOnCelestial()) {
         double newX, newY;
         newX = ship.getAttachedCelestial().getX()
               + (ship.getAttachedCelestial().getRadius() + ship.getRadius() + 1) * Math.cos(ship.getAngle());
         newY = ship.getAttachedCelestial().getY()
               + (ship.getAttachedCelestial().getRadius() + ship.getRadius() + 1) * Math.sin(ship.getAngle());
         ship.setLastCoordinate(newX, newY);
         ship.resetCoordinate();
         ship.resetMomentum();
      }
      Physics.shipGuideline(ship, sun, planets);
      ship.shiftCoordinate();
      if (!ship.getOnCelestial())
         ship.setThrust(0);
   }

   /**
    * Calculates the ship guideline's next coordinates and momentum.
    * Calculations for the future accommodate the future positions of planets.
    * Recurses until guideline is full.
    * <p>
    * Momentum is an accumulation of previous momentum and all forces on the
    * ship. Coordinates are previous coordinates + momentum.
    * 
    * @param ship
    * @param sun
    * @param planets
    */
   public static void shipGuideline(Ship ship, Celestial sun, Planet[] planets) {
      double addX, addY;
      double angleToSun = Math.atan2(ship.getFirstY() - sun.getY(), ship.getFirstX() - sun.getX());
      double gravityForceOfSun = Constants.GRAV_CONSTANT * sun.getMass()
            / Math.pow(Math.pow(ship.getFirstX() - sun.getX(), 2)
                     + Math.pow(ship.getFirstY() - sun.getY(), 2), Constants.GRAV_FALLOFF);
      addX = (ship.getCoordinateSize() > 2 ? 0 : ship.getThrust()) * Math.cos(ship.getAngle())
         + ship.getDX() - Math.cos(angleToSun) * gravityForceOfSun;
      addY = (ship.getCoordinateSize() > 2 ? 0 : ship.getThrust()) * Math.sin(ship.getAngle())
         + ship.getDY() - Math.sin(angleToSun) * gravityForceOfSun;
      for (Planet planet : planets) {
         Point2D planetCoord = Physics.planetaryOrbit(sun, planet, ship.getCoordinateSize());
         double angleToPlanet = Math.atan2(ship.getFirstY() - planetCoord.getY(), ship.getFirstX() - planetCoord.getX());
         double gravityForceOfPlanet = Constants.GRAV_CONSTANT * planet.getMass()
               / Math.pow(Math.pow(ship.getFirstX() - planetCoord.getX(), 2)
                        + Math.pow(ship.getFirstY() - planetCoord.getY(), 2), Constants.GRAV_FALLOFF);
         addX -= Math.cos(angleToPlanet) * gravityForceOfPlanet;
         addY -= Math.sin(angleToPlanet) * gravityForceOfPlanet;
      }
      ship.setFirstMomentum(addX, addY);
      ship.setFirstCoordinate(ship.getFirstX() + addX, ship.getFirstY() + addY);
      if (ship.getCoordinateSize() < 510) {
         Physics.shipGuideline(ship, sun, planets);
      }
   }

   /**
    * Detects collision between a celestial object and a ship by checking
    * the distance between the edges of 2 circles.
    * 
    * @param celestial
    * @param ship
    * @return boolean
    */
   public static boolean detectCollision(Celestial celestial, Ship ship) {
      return Math.sqrt(Math.pow(celestial.getX() - ship.getLastX(), 2) + Math.pow(celestial.getY() - ship.getLastY(), 2))
            - celestial.getRadius() - ship.getRadius() < 0 && !ship.getOnCelestial();
   }
}
