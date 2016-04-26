package update;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import celestial.Celestial;
import celestial.Planet;
import physics.Constants;
import physics.Physics;

/**
 * A solar system object contains a sun and a number of planets. It contains a
 * paintComponent method necessary for GUI. A run method that updates planets
 * coordinates every time interval.
 */
public class Update extends JPanel {
   private Celestial sun;
   private Planet[] planets;
   // private Ship ship;

   public static final int NUM_OF_PLANETS = 8;

   public static final String[] PLANET_NAMES = { "Mercury", "Venus", "Earth",
         "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };
   public static final Color[] PLANET_COLORS = { Color.darkGray, Color.yellow,
         Color.blue, Color.green, Color.orange, Color.gray, Color.blue,
         Color.magenta };
   public static final int[] PLANET_SIZES = { 5, 7, 10, 8, 22, 20, 17, 15 };
   public static final int[] PLANET_PERIODS = { 7286, 20612, 37865, 58300,
         81435, 107127, 134883, 164790 };

   public Update() {
      super();
      sun = new Celestial(new Point(Constants.INIT_SUN_X, Constants.INIT_SUN_Y),
            Color.red, "Sun", 30);
      sun.setImage(new ImageIcon("image/MrSun-sample.png").getImage());
      planets = new Planet[NUM_OF_PLANETS];
      Random randGen = new Random();
      for (int i = 0; i < NUM_OF_PLANETS; i++) {
         planets[i] = new Planet(PLANET_COLORS[i], PLANET_NAMES[i],
               PLANET_SIZES[i], 50 * (i + 1),
               randGen.nextDouble() * 2 * Math.PI, PLANET_PERIODS[i]);
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      double scale = Math.min(getWidth() / 1682., getHeight() / 953.);
      g2d.scale(scale, scale);

      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      sun.draw(g, this);
      for (Planet planet : planets) {
         planet.draw(g, this);
         g2d.drawOval(sun.getX() - planet.getDistanceToSun(),
               sun.getY() - planet.getDistanceToSun(),
               planet.getDistanceToSun() * 2, planet.getDistanceToSun() * 2);
      }
   }

   public void run() {
      Timer timer = new Timer(Constants.TIME_INTERVAL, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for (Planet planet : planets)
               Physics.planetaryOrbit(sun, planet);
            repaint();
         }
      });
      timer.start();
   }
}
