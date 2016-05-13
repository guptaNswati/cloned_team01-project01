package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Starfield {
   private static final int NUM_OF_STARS = 1000;
   private static final int MAX_STAR_RADIUS = 3;
   private static final int STAR_HORIZONTAL_AREA = 1000;
   private static final int STAR_VERTICAL_AREA = 1150;
   private int[] xCoord;
   private int[] yCoord;
   private int[] radius;
   private Color[] color;

   /**
    * Initialize coordinates, radii, and color of stars.
    */
   public Starfield() {
      xCoord = new int[NUM_OF_STARS];
      yCoord = new int[NUM_OF_STARS];
      radius = new int[NUM_OF_STARS];
      color = new Color[NUM_OF_STARS];
      Random rand = new Random();
      for (int i = 0; i < NUM_OF_STARS; i++) {
         xCoord[i] = rand.nextInt(STAR_HORIZONTAL_AREA);
         yCoord[i] = rand.nextInt(STAR_VERTICAL_AREA);
         radius[i] = rand.nextInt(MAX_STAR_RADIUS);
         color[i] = new Color(rand.nextInt(127) + 128, rand.nextInt(127) + 128,
                                  rand.nextInt(127) + 128, rand.nextInt(143) + 32);
      }
   }

   public void draw(Graphics g) {
      for (int i = 0; i < NUM_OF_STARS; i++) {
         g.setColor(color[i]);
         g.fillOval(xCoord[i], yCoord[i], radius[i], radius[i]);
      }
   }
}
