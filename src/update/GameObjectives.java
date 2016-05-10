package update;
import celestial.*;


public class GameObjectives{
   
   private static int [] planetNumber = {5, 4, 8, 2, 0, 1, 6, 7};
   private static int currentPlanetObjective;
   private static String [] jokes;
   
   public GameObjectives() {
      currentPlanetObjective = planetNumber[0];
      
      jokes[0] = "Uranus is gassy and dark";
      jokes[1] = "Humans have never attempted to land on Uranus";
      jokes[2] = "Uranus is blue";
      jokes[3] = "Uranus can only be observed from Earth through a telescope";
      jokes[4] = "Uranus is bigger than Earth";
      jokes[5] = "To the best of our knowledge, nothing can survive on Uranus.";
      jokes[6] = "A human cannot survive on Uranus";   
      //jokes[7] = "Uranus has more moons than Earth. In fact, 27 of them!";
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
   
   public static int getObjective() {
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
