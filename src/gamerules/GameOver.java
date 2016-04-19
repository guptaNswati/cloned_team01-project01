package gamerules;

public class GameOver
{
   public static final double CRITICAL_DISTANCE = 1000;

   public static boolean isOutOfSolarSystem(Player player, Sun sun)
   {
      return calcDistance(player, sun) > CRITICAL_DISTANCE;
   }

   public static void showGameOver()
   {
      // TODO
   }
}
