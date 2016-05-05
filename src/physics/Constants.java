package physics;

/**
 * A class that contains constants that will be used in calculations.
 */
public class Constants {
   public static final int TIME_INTERVAL = 25;

   public static final int FRAME_WIDTH = 1000;
   public static final int FRAME_HEIGHT = 1000;
   
   public static final int INIT_SUN_X = 952 / 2;
   public static final int INIT_SUN_Y = 952 / 2;
   
   // Strength of force of gravity
   public static final double GRAV_CONSTANT = 0.8;
   
   // Lower value means force of gravity decreases less with distance
   // 0.5 is constant force to infinite distance
   public static final double GRAV_FALLOFF = 0.65;
}
