package update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import celestial.Celestial;
import celestial.Planet;
import information.CSVReader;
import information.Information;
import physics.Constants;
import physics.Physics;
import ship.Arrow;
import ship.Ship;
import ship.Ship;

/**
 * An update object contains all dynamic graphical elements. It contains a
 * paintComponent method necessary for GUI. The run method updates physical
 * coordinates and GUI elements at regular intervals.
 */
public class Update extends JPanel {
   private Celestial sun;
   private Planet [] planets;
   private Ship ship;
   private Arrow arrow;

   // information data 
   private ArrayList<Information> info;

   // keeps track of players last planet 
   String planetWithPlayer;

   public static final int NUM_OF_PLANETS = 8;

   public static final String[] PLANET_NAMES = { "Mercury", "Venus",
         "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

   public static final Color[] PLANET_COLORS = { 
         Color.pink,   //Mercury
         Color.yellow, //Venus
         Color.blue,   //Earth
         Color.green,  //Mars
         Color.orange, //Jupiter
         Color.gray,   //Saturn
         Color.blue,   //Uranus
         Color.magenta //Neptune
   };

   public static final int [] PLANET_SIZES = { 
         5,  //Mercury
         7,  //Venus
         10, //Earth
         8,  //Mars
         22, //Jupiter
         20, //Saturn
         17, //Uranus
         15  //Neptune
   };

   public static final int [] PLANET_PERIODS = {
         7286,   //Mercury
         20612,  //Venus
         37865,  //Earth
         58300,  //Mars
         81435,  //Jupiter
         107127, //Saturn
         134883, //Uranus
         164790  //Neptune
   };

   public static final double[] PLANET_MASSES = {
         5.8,  //Mercury
         8.5,  //Venus
         8.7,  //Earth
         6.5,  //Mars
         14.5, //Jupiter
         13.2, //Saturn
         11.4, //Uranus
         11.5  //Neptune
   };

   /**
    * Instantiate and initialize all members.
    */
   public Update() {

      super();
      sun = new Celestial(new Point2D.Double(Constants.INIT_SUN_X,
            Constants.INIT_SUN_Y), Color.red, "Sun", 30, 21.4);
      sun.setImage("image/MrSun-sample.png");

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

      toggleKeyListener();

      CSVReader csv = new CSVReader();
      info = csv.getInfoData();
      planetWithPlayer = "";
   }

   /**
    * Draw solar system and ship and arrow.
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      // Keep solar system centered regardless of aspect ratio
      double scale = Math.min(getWidth() / 952., getHeight() / 952.);
      g2d.scale(scale, scale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      ship.draw(g);
      sun.draw(g, this);

      // Draw all planets
      for (Planet planet : planets) {
         planet.draw(g, this); //draws planet

         // Checks the distance between planets and player and displays information appropriately
         if (Physics.distanceToPlanet(planet, ship) < 2)
         {
            for(int i = 1; i < info.size(); i++)
            {
               if (info.get(i).getName().equals(planet.getName())
                     && planetWithPlayer != info.get(i).getName())
               {                        
                  JOptionPane.showMessageDialog(null, this.info.get(i),
                        "Did you know!", JOptionPane.INFORMATION_MESSAGE);
                  planetWithPlayer = info.get(i).getName();
               }
            }
         }

         // Draws planet orbit path
         g2d.drawOval((int) (sun.getX() - planet.getDistanceToSun()), 
               (int) (sun.getY() - planet.getDistanceToSun()),
               planet.getDistanceToSun() * 2, planet.getDistanceToSun() * 2);
      }
   }

   /**
    * Update every object every time interval.
    */
   public void run() {
      Timer timer = new Timer(Constants.TIME_INTERVAL, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for (Planet planet : planets) {
               Physics.planetaryOrbit(sun, planet);
            }
            Physics.shipFlight(ship, sun, planets);

            // places arrow on ship's planet
            ship.getArrow().setCoordinate(
                  ship.getAttachedCelestial().getCoordinate());

            repaint();
         }
      });
      timer.start();
   }

   /**
    * Called by constructor to enable JPanel to listen to key listener.
    */
   private void toggleKeyListener() {
      setFocusable(true);
      requestFocusInWindow();
      addKeyListener(ship.getShipKeyControl());
   }
   
   // for testing
   class TesterButton extends JButton {
      public TesterButton(String name) {
         super(name);
         setVisible(true);
         addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setEnabled(false);
               toggleTestingPanel();
               Update.this.requestFocus();
            }
         });
      }

      private void toggleTestingPanel() {
         JFrame tester = new JFrame("Tester");
         tester.setBounds(Constants.FRAME_WIDTH, 200, 700, 500);
         tester.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               setEnabled(true);
            }
         });
         tester.setVisible(true);
         tester.requestFocus();
         PeriodSliders periodSliders = new PeriodSliders();
         tester.add(BorderLayout.WEST, periodSliders);
      }
   }
   
   class PeriodSliders extends JPanel {
      public PeriodSliders() {
         this.add(BorderLayout.NORTH,
               new JLabel("Period", SwingConstants.CENTER));
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(NUM_OF_PLANETS, 3));
         JLabel[] labels = new JLabel[NUM_OF_PLANETS];
         JLabel[] values = new JLabel[NUM_OF_PLANETS];
         JSlider[] sliders = new JSlider[NUM_OF_PLANETS];
         for (int i = 0; i < NUM_OF_PLANETS; i++) {
            labels[i] = new JLabel(planets[i].getName());
            values[i] = new JLabel(
                  Integer.toString(planets[i].getPeriodInMS()));
            sliders[i] = new JSlider(1000, 200000,
                  planets[i].getPeriodInMS());
            sliders[i].setMinorTickSpacing(500);
            sliders[i].setMajorTickSpacing(2000);
            panel.add(labels[i]);
            panel.add(values[i]);
            panel.add(sliders[i]);
            Planet planet = planets[i];
            JLabel value = values[i];
            sliders[i].addChangeListener(new ChangeListener() {
               @Override
               public void stateChanged(ChangeEvent e) {
                  planet.setPeriodInMS(((JSlider)e.getSource()).getValue());
                  value.setText(Integer.toString(planet.getPeriodInMS()));
               }
            });
         }
         this.add(BorderLayout.SOUTH, panel);
      }
   }
}
