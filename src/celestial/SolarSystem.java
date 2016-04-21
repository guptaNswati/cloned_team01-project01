package celestial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import physics.Constants;
import physics.OrbitCalculation;

public class SolarSystem extends JPanel {
   private Celestial sun;
   private Planet[] planets;

   public static final int NUM_OF_PLANETS = 8;

   public static final String[] PLANET_NAMES = { "Mercury", "Venus", "Earth",
         "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };
   public static final Color[] PLANET_COLORS = { Color.darkGray, Color.yellow,
         Color.blue, Color.green, Color.orange, Color.gray, Color.blue,
         Color.magenta };
   public static final int[] PLANET_SIZES = { 5, 7, 10, 8, 22, 20, 17, 15 };

   public SolarSystem() {
      super();
      sun = new Celestial(new Point(Constants.FRAME_WIDTH / 2,
            Constants.FRAME_HEIGHT / 2 - 40), Color.red, "Sun", 30);
      planets = new Planet[NUM_OF_PLANETS];
      for (int i = 0; i < NUM_OF_PLANETS; i++) {
         planets[i] = new Planet(PLANET_COLORS[i], PLANET_NAMES[i],
               PLANET_SIZES[i], 50 * (i + 1), 0, (i + 1) * 1000);
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      sun.draw(g);
      Graphics2D g2d = (Graphics2D)g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      for (Planet planet : planets) {
         planet.draw(g);
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
               OrbitCalculation.nextCoordinate(sun, planet);
            repaint();
         }
      });
      timer.start();
   }
}
