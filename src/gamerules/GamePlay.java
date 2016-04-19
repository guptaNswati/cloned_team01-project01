package gamerules;

public class GamePlay
{
   public static void gameplay()
   {
      while(true)
      {
         int whichPlanet = PlayerLanding.onWhichPlanet(player);
         
         switch(whichPlanet)
         {
         case 3:   // earth
            startGame();
            break;
         case -1:   // in space
            break;
         default:
            planets[whichPlanet].showInfo();
         }
      // game over
      if(GameOver.isOutOfSolarSystem(player, sun))
         GameOver.showGameOver();
   }
}
