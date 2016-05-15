package update;

import java.util.Random;

/**
 * Store data on and control game objectives.
 */
public class GameObjectives{
   /**
    * Stores the sequence of game objective regarding which planet to go to.
    */
   private static int[] objectivePlanet = {5, 3, 7, 4, 0, 6, 1, 2};
   /**
    * The current game objective.
    */
   private static int currentObjectiveIndex = 0;
   /**
    * A collection of jokes.
    */
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

   /**
    * Changes current objective to next one, or go back to first if hits the
    * end.
    * 
    * @return false if hits the end and loops around; true if otherwise.
    */
   public static boolean nextObjective() {
      if (currentObjectiveIndex < 7 ) {
         currentObjectiveIndex++;
         return true;
      }

      currentObjectiveIndex = 0;
      return false;
   }

   /**
    * Get the index of the planet of game objective.
    * 
    * @return The index of targeted planet.
    */
   public static int getPlanetObjective() {
      return objectivePlanet[currentObjectiveIndex];
   }

   /**
    * Sets the index of objective.
    * 
    * @param objNum
    *           Objective index number.
    * @return true if valid argument, false otherwise.
    */
   public boolean setObjective(int objNum) {
      if (objNum >= 0 && objNum <= objectivePlanet.length) {
         currentObjectiveIndex = objNum;
         return true;
      }
      return false;
   }

   /**
    * Gets a random joke.
    * 
    * @return a random joke.
    */
   public static String getJoke() {
      Random rand = new Random();
      return jokes[rand.nextInt(7)];
   }
}
