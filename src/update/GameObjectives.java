package update;
import celestial.*;


public class GameObjectives{
   
   private static int [] planetNumber = {5, 4, 8, 2, 0, 1, 6, 7};
   private static int currentPlanetObjective;
   private static String [] jokes;
   
   public GameObjectives() {
      currentPlanetObjective = planetNumber[0];
      
      jokes[0] = "Uranus is gassy and dark";
      jokes[1] = "Humans have never attempted to land on Uranus, let alone colonize it. (Must not be oily enough for 'murica";
      jokes[2] = "Q: What is big, round, and blue? A: Uranus";
      jokes[3] = "From Earth, Uranus can only be observed through a telescope, though some claim that the best way to study it is without one.";
      jokes[4] = "Uranus is about 4 times wider than Earth. In fact, 63 Earths can fit in Uranus.";
      jokes[5] = "Sir William Herschel discovered Uranus in 1781";
      jokes[6] = "A human cannot survive the noxious gasses of Uranus";   
      jokes[7] = "Uranus has more moons than Earth. In fact, 27 of them!";
   }
   
   public static boolean nextObjective() {
      if(currentPlanetObjective < 7 )
      {
         currentPlanetObjective++;
         return true;
      }
      
      currentPlanetObjective = 0;
      return false;
      
   }
   
   public static int getPlanetObjective() {
      return planetNumber[currentPlanetObjective];
   }
   
   public boolean setObjective(int objNum) {
      if(objNum >= 0 && objNum <= 7) {
         currentPlanetObjective = objNum;
         return true;
      }
      return false;
   }
   
   public static String getJoke(int jokeNum) {
      if(jokeNum >= 0 && jokeNum < jokes.length) {
         return jokes[jokeNum];
      }
      return "Invalid joke number";
   }
   
}
