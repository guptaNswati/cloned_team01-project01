package gamerules;

public class PlayerLanding
{
   public static int onWhichPlanet(Player player)
   {
      if (!player.getIsPlayerOnPlanet())
         return -1;
      return indexOfPlanet;
   }
}
