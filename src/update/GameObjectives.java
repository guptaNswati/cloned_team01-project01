package update;

import java.util.Random;

public class GameObjectives{
   private static int[] objectivePlanet = {5, 3, 7, 4, 0, 5, 1, 2};
   private static int currentObjectiveIndex = 0;
   private static String[] jokes = {
      "Q: What is big, round, and blue? A: Uranus",
      "Did you know Uranus is dark and gassy?",
      "Humans have never attempted to colonize Uranus",
      "From Earth, Uranus can only be observed through a telescope, though some claim that the best way to study it is without one.",
      "Did you know that 63 Earths can fit inside Uranus?",
      "Sir William Herschel discovered Uranus in 1781",
      "A human cannot survive the noxious gasses of Uranus",
      "Uranus has more moons than Earth. In fact, 27 of them!"
   };

   public static boolean nextObjective() {
      if (currentObjectiveIndex < 7 ) {
         currentObjectiveIndex++;
         return true;
      }

      currentObjectiveIndex = 0;
      return false;
   }

   public static int getPlanetObjective() {
      return objectivePlanet[currentObjectiveIndex];
   }

   public boolean setObjective(int objNum) {
      if (objNum >= 0 && objNum <= objectivePlanet.length) {
         currentObjectiveIndex = objNum;
         return true;
      }
      return false;
   }

   public static String getJoke() {
      Random rand = new Random();
      return jokes[rand.nextInt(7)];
   }
}
