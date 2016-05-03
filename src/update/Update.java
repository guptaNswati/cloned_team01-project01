package update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import celestial.Celestial;
import celestial.Planet;
import physics.Constants;
import physics.Physics;
import ship.Arrow;
import ship.Ship;
import ship.Ship;

/**
 * An update object contains all dynamic graphical elements.
 * It contains a paintComponent method necessary for GUI.
 * The run method updates physical coordinates and GUI elements at regular intervals.
 */
public class Update extends JPanel {
   private Celestial sun;
   private Planet [] planets;
   private Ship ship;
   private Arrow arrow;
   
   public static final int NUM_OF_PLANETS = 8;

   public static final String[] PLANET_NAMES = { "Mercury", "Venus", "Earth",
         "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };
   
   public static final Color[] PLANET_COLORS = { 
         Color.pink, //Mercury
         Color.yellow, //Venus
         Color.blue, //Earth
         Color.green, //Mars
         Color.orange, //Jupiter
         Color.gray, //Saturn
         Color.blue, //Uranus
         Color.magenta //Neptune
         };
   
   public static final int [] PLANET_SIZES = { 
         5, //Mercury
         7, //Venus
         10, //Earth
         8,  //Mars
         22, //Jupiter
         20, //Saturn
         17, //Uranus
         15 //Neptune
         };

   public static final int [] PLANET_PERIODS = {
         7286, //Mercury
         20612, //Venus
         37865, //Earth
         58300, //Mars
         81435, //Jupiter
         107127, //Saturn
         134883, //Uranus
         164790 //Neptune
         };
   public static final double[] PLANET_MASSES = {
         5.8, //Mercury
         8.5, //Venus
         8.7, //Earth
         6.5, //Mars
         14.5, //Jupiter
         13.2, //Saturn
         11.4, //Uranus
         11.5 //Neptune
         };

   public Update() {
      super();
      sun = new Celestial(new Point2D.Double(Constants.INIT_SUN_X,
            Constants.INIT_SUN_Y), Color.red, "Sun", 30, 21.4);
      planets = new Planet[NUM_OF_PLANETS];
      ship = new Ship();
      Random randGen = new Random();
      
      //initialize planets
      for (int i = 0; i < NUM_OF_PLANETS; i++) {
         planets[i] = new Planet(
               PLANET_COLORS[i], 
               PLANET_NAMES[i], 
               PLANET_SIZES[i], 
               PLANET_MASSES[i], 
               50 * (i + 1), randGen.nextDouble() * 2 * Math.PI, 
               PLANET_PERIODS[i]);
       }

      ship.setAttachedCelestial(planets[2]);
      arrow = new Arrow("image/arrow-sample.png", planets[2].getCoordinate());
      toggleKeyListener();

      // for testing
      add(new TesterButton("tester"));
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      // Keep solar system centered regardless of aspect ratio
      double scale = Math.min(getWidth() / 952., getHeight() / 952.);
      g2d.scale(scale, scale);
      
      //anti alising
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      
      
      sun.draw(g);
      
      if(ship.getOnCelestial()) {
         arrow.draw(g, this);
      }
      
      //draw all planets
      for (Planet planet : planets) {
         planet.draw(g); //draws planet
         
         //draws planet orbit path
         g2d.drawOval((int)sun.getX() - planet.getDistanceToSun(), 
               (int)sun.getY() - planet.getDistanceToSun(),
               planet.getDistanceToSun() * 2, planet.getDistanceToSun() * 2);
      }
      
      ship.draw(g);
   }

   public void run() {
      Timer timer = new Timer(Constants.TIME_INTERVAL, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for (Planet planet : planets) {
               Physics.planetaryOrbit(sun, planet);
            }
            Physics.shipFlight(ship, planets);

            arrow.setCoordinate(ship.getAttachedCelestial().getCoordinate()); //places arrow on ship's planet
            

            repaint();
         }
      });
      timer.start();
   }

   private void toggleKeyListener() {
      setFocusable(true);
      requestFocusInWindow();
      addKeyListener(arrow.getArrowKeyControl());
      addKeyListener(ship.getShipKeyControl());
   }
   
   // for testing
   private void toggleTestingPanel() {
      JFrame tester = new JFrame("Tester");
      tester.setBounds(getWidth() - 100, 200, 500, 500);
      // add(BorderLayout.SOUTH, new SliderPanel("gr"));
      tester.setVisible(true);
      tester.requestFocus();
      tester.add(new SliderPanel("dsaflsadf"));
   }
   
   class TesterButton extends JButton {
      public TesterButton(String name) {
         super(name);
         setVisible(true);
         addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               toggleTestingPanel();
            }
         });
      }
   }
   
   class SliderPanel extends JPanel {
      public SliderPanel(String label) {
         this.add(BorderLayout.NORTH,
               new JLabel(label, SwingConstants.CENTER));
         JPanel panel = new JPanel();
         this.add(BorderLayout.CENTER, panel);
         panel.setLayout(new GridLayout(3, 1));
         for (int i = 0; i < 3; i++)
            panel.add(new JSlider(0, 5, 3));
         this.setVisible(true);
      }
   }
}
