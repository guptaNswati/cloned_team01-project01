package update;
import celestial.*;


public class GameObjectives{
   
   private static int [] planetNumber = {5, 4, 3, 2, 0, 1, 6, 7};
   private static int currentPlanetObjective = 0;   
   private static String [] jokes ={
      "Q: What is big, round, and blue? A: Uranus",
      "Did you know Uranus is dark and gassy?", 
      "Humans have never attempted to colonize Uranus",
      "From Earth, Uranus can only be observed through a telescope, though some claim that the best way to study it is without one.",
      "Did you know that 63 Earths can fit inside Uranus.",
      "Sir William Herschel discovered Uranus in 1781",
      "A human cannot survive the noxious gasses of Uranus",
      "Uranus has more moons than Earth. In fact, 27 of them!"
   };
   
   public static boolean nextObjective() {
      if(currentPlanetObjective < 7 ){
         currentPlanetObjective++;
         System.out.println("Set next objective!"+ currentPlanetObjective);
         return true;
      }
      
      currentPlanetObjective = 0;
      return false;
      
   }
   
   public static int getPlanetObjective() {
      return planetNumber[currentPlanetObjective];
   }
   
   public boolean setObjective(int objNum) {
      if(objNum >= 0 && objNum <= planetNumber.length) {
         currentPlanetObjective = objNum;
         return true;
      }
      return false;
   }
   
   public static String getJoke() {
      return jokes[currentPlanetObjective];
   }
   
}
