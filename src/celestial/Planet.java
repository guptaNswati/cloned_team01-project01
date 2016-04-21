package celestial;

import java.awt.Color;
import java.awt.Point;

public class Planet extends Celestial {
   public Planet() {
      super();
   }

   public Planet(Point coordinate, Color color, String name, double radius) {
      super(coordinate, color, name, radius);
   }

}
