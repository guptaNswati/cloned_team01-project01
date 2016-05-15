/**
 * Creates stars in the universe in the GUI background.
 */

package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Starfield {
   private static final int NUM_OF_STARS = 1000;
   private static final int MAX_STAR_RADIUS = 3;
   private static final int STAR_HORIZONTAL_AREA = 1000;
   private static final int STAR_VERTICAL_AREA = 1000;
   private Point[] coordinate;
   private int[] radius;
   private Color[] color;

   /**
    * Initialize coordinates, radii, and color of stars.
    */
   public Starfield() {
      coordinate = new Point[NUM_OF_STARS];
      radius = new int[NUM_OF_STARS];
      color = new Color[NUM_OF_STARS];
      Random rand = new Random();
      for (int i = 0; i < NUM_OF_STARS; i++) {
         coordinate[i] = new Point(rand.nextInt(STAR_HORIZONTAL_AREA), rand.nextInt(STAR_VERTICAL_AREA));
         radius[i] = rand.nextInt(MAX_STAR_RADIUS);
         color[i] = new Color(rand.nextInt(127) + 128, rand.nextInt(127) + 128,
                              rand.nextInt(127) + 128, rand.nextInt(143) + 32);
      }
   }

   public void draw(Graphics g) {
      for (int i = 0; i < NUM_OF_STARS; i++) {
         g.setColor(color[i]);
         g.fillOval(coordinate[i].x, coordinate[i].y, radius[i], radius[i]);
      }
   }
}
