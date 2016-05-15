package update;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import celestial.*;
import information.*;
import menu.Menu;
import physics.*;
import ship.Ship;

/**
 * An update object contains all dynamic graphical elements. 
 * It contains a paintComponent method necessary for GUI. 
 * The run method updates physical coordinates and GUI elements.
 */
public class Update extends JPanel {
   private Celestial sun;
   private Planet[] planets;
   private Starfield stars;
   private Ship ship;

   // adding info_panel
   private JTextArea infoTextBox;
   private JTextField fuelBox;
   private JTextField thrustBox;

   // information data 
   private Information info = new Information();

   private Menu menu;

   private Target target;
   
   private double scale;

   public static final int NUM_OF_PLANETS = 8;

   public static final String[] PLANET_NAMES = { "Mercury", "Venus",
         "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

   public static final Color[] PLANET_COLORS = {
         new Color(125, 125, 125, 64), // Mercury
         new Color(194, 124, 39, 64),  // Venus
         new Color(64, 99, 245, 64),   // Earth
         new Color(142, 96, 71, 64),   // Mars
         new Color(188, 172, 157, 64), // Jupiter
         new Color(217, 183, 122, 64), // Saturn
         new Color(189, 227, 230, 64), // Uranus
         new Color(37, 162, 204, 64)   // Neptune
   };

   public static final int [] PLANET_SIZES = {
         7,  //Mercury
         9,  //Venus
         10, //Earth
         8,  //Mars
         22, //Jupiter
         20, //Saturn
         14, //Uranus
         13  //Neptune
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
      sun.setImage("resources/planets/sun.png");

      planets = new Planet[NUM_OF_PLANETS];
      stars = new Starfield();
      Random randGen = new Random();

      //initialize planets
      for (int i = 0; i < NUM_OF_PLANETS; i++) {
         planets[i] = new Planet(
               PLANET_COLORS[i],
               PLANET_NAMES[i],
               PLANET_SIZES[i],
               PLANET_MASSES[i],
               50 * (i + 1),
               randGen.nextDouble() * 2 * Math.PI,
               PLANET_PERIODS[i]);
         planets[i].setImage(String.format("resources/planets/%s.png",
               planets[i].getName()));
      }

      menu = new Menu();

      ship = new Ship();
      ship.setAttachedCelestial(planets[2]);

      addInputListener();

      target = new Target();
   }

   /**
    * Draw solar system and ship and arrow.
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      // Keep solar system centered regardless of aspect ratio
      scale = Math.min(getWidth() / 952., getHeight() / 952.);
      g2d.scale(scale, scale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

      stars.draw(g);
      sun.draw(g, this);

      // Draw all planets
      for (int planetIndex = 0; planetIndex < NUM_OF_PLANETS; planetIndex++) {

         Planet planet = planets[planetIndex];
         g2d.setColor(planet.getColor());
         // Draws planet orbit path
         g2d.drawOval((int)(sun.getX() - planet.getDistanceToSun()),
                      (int)(sun.getY() - planet.getDistanceToSun()),
                      planet.getDistanceToSun() * 2, planet.getDistanceToSun() * 2);
         planet.draw(g, this); //draws planet

         // Checks for collision and displays information appropriately
         if (Physics.detectCollision(planet, ship)) {
            ship.setOnCelestial(true);
            ship.setAttachedCelestial(planet);

            if (planetIndex == GameObjectives.getPlanetObjective()) {
               //landed on right planet
               GameObjectives.nextObjective();
               infoTextBox.setText(info.getCelestial(planet.getName())
                     + "\n\n\n\nGOOD JOB!\nNow, go to this planet next: "
                     + PLANET_NAMES[GameObjectives
                                    .getPlanetObjective()]);
            } else {
               //landed on wrong planet
               infoTextBox.setText("Go to this planet: "
                     + PLANET_NAMES[GameObjectives.getPlanetObjective()]
                     + "\n\nImportant: " + GameObjectives.getJoke());
            }
         }
      }

      ship.draw(g);
      target.draw(g, this);
      menu.draw(g);
   }

   /**
    * Update every object every time interval.
    */
   public void run() {
      Timer timer = new Timer(Constants.TIME_INTERVAL, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for (Planet planet : planets) {
               planet.incrementAngleToSun();
               planet.setCoordinate(Physics.planetaryOrbit(sun, planet, 1));
            }
            Physics.shipFlight(ship, sun, planets);
            target.resetTarget(planets[GameObjectives.getPlanetObjective()]);
            fuelBox.setText(String.format("%d", (int)ship.getFuel()));
            thrustBox.setText(String.format("%.1f", ship.getThrust()));

            repaint();
         }
      });
      timer.start();
   }

   public Menu getMenu() {
      return menu;
   }

   public Ship getShip() {
      return ship;
   }

   /**
    * @return planets array
    */
   public Planet[] getPlanets() {
      return planets;
   }

   /**
    * @param the planets to set
    */
   public void setPlanets(Planet[] planets) {
      this.planets = planets;
   }

   public void linkWithSidePanel(SidePanel sidePanel) {
      infoTextBox = sidePanel.getInfoTextBox();
      fuelBox = sidePanel.getFuelBox();
      thrustBox = sidePanel.getThrustBox();
   }

   /**
    * Called by constructor to enable JPanel to listen to key listener.
    */
   private void addInputListener() {
      setFocusable(true);
      requestFocusInWindow();
      addKeyListener(new KeyControl());
      addMouseMotionListener(new MouseControl());
   }

   /**
    * Allows angle and thrust control by keyboard.
    */
   class KeyControl extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         if (menu.getIsShown())
            menu.toggleIsShown();
         else {
            int key = e.getKeyCode();
            switch (key) {
            case KeyEvent.VK_LEFT: // rotate angle counter-clockwise
            case KeyEvent.VK_A:
               ship.changeAngle(-0.15);
               break;
            case KeyEvent.VK_RIGHT: // rotate angle clockwise
            case KeyEvent.VK_D:
               ship.changeAngle(0.15);
               break;
            case KeyEvent.VK_UP: // increase thrust
            case KeyEvent.VK_W:
               if (ship.getOnCelestial())
                  ship.changeThrust(0.4);
               else if (ship.getFuel() > 0)
                  ship.changeThrust(0.01);
               break;
            case KeyEvent.VK_DOWN: // decrease thrust
            case KeyEvent.VK_S:
               ship.changeThrust(-0.4);
               break;
            case KeyEvent.VK_ENTER: // launch from planet
            case KeyEvent.VK_SPACE:
               ship.setOnCelestial(!ship.getOnCelestial());
               break;
            case KeyEvent.VK_ESCAPE: // show menu
               menu.toggleIsShown();
            }
         }
      }
   }

   /**
    * Allows on-planet angle and thrust control by mouse, and
    * in-flight angle and thrust control by dragging mouse.
    */
   class MouseControl implements MouseMotionListener {
      public void mouseMoved(MouseEvent e) {
         ship.setAngle(Math.atan2(e.getY() / scale - ship.getLastY(),
                                  e.getX() / scale - ship.getLastX()));
         if (ship.getOnCelestial())
            ship.setThrust(Math.sqrt(Math.pow(e.getX() / scale - ship.getLastX(), 2)
                                   + Math.pow(e.getY() / scale - ship.getLastY(), 2)) * 0.015);
      }
      
      public void mouseDragged(MouseEvent e) {
         ship.setAngle(Math.atan2(e.getY() / scale - ship.getLastY(),
                                  e.getX() / scale - ship.getLastX()));
         if (!ship.getOnCelestial() && ship.getFuel() > 0)
            ship.changeThrust(0.01);
      }
   }
}
