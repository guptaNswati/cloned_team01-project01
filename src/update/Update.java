package update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import celestial.Celestial;
import celestial.Planet;
import information.CSVReader;
import information.Information;
import information.SidePanel;
import menu.Menu;
import physics.Constants;
import physics.Physics;
import ship.Arrow;
import ship.Ship;

/**
 * An update object contains all dynamic graphical elements. It contains a
 * paintComponent method necessary for GUI. The run method updates physical
 * coordinates and GUI elements at regular intervals.
 */
public class Update extends JPanel {
   private Celestial sun;
   private Planet[] planets;
   private Ship ship;
   private Arrow arrow;
   //private GameObjectives;

   // adding info_panel
   private JTextArea jokeTextBox;
   
   //private GameObjectives 

   // information data 
   private ArrayList<Information> info;
   
   private Menu menu;

   private Target target;

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
      // sun.setImage("image/MrSun-sample.png");
      sun.setImage("resources/planets/sun.png");

      planets = new Planet[NUM_OF_PLANETS];
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

      toggleKeyListener();

      CSVReader csv = new CSVReader();
      info = csv.getInfoData();

      target = new Target();
      // for period testing
      // add(new TesterButton("Test Period"));
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
      target.draw(g, this);
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

         // Checks the distance between planets and player and displays information appropriately
         if (Physics.detectCollision(planet, ship)) {
            ship.setOnCelestial(true);
            ship.setAttachedCelestial(planet);

            if (planetIndex == GameObjectives.getPlanetObjective()){

               System.out.println("Landed on right planet!");

               //landed on right planet
               for(int i = 1; i < info.size(); i++) {
                  //display info about planet
                  if (info.get(i).getName().equals(planet.getName())
                        && ship.getAttachedCelestial().getName() != info.get(i).getName()) { 
                     GameObjectives.nextObjective();
                     jokeTextBox.setText(info.get(i).toString()
                           + "\n\nGOOD JOB!\nNow, go to this planet next: "
                           + PLANET_NAMES[GameObjectives
                                 .getPlanetObjective()]);
                     jokeTextBox.setVisible(true);
                     break;
                  }
                  //infoPanel.setVisible(false);
               }
               //go to next game objective
            }
            else { //landed on wrong planet
               System.out.println("Landed on WRONG planet! " + GameObjectives.getJoke());

               //show text box that says go to other planet + joke
               jokeTextBox
                     .setText("Go to this planet: "
                           + PLANET_NAMES[GameObjectives
                                 .getPlanetObjective()]
                     + "\n\nImportant: " + GameObjectives.getJoke());   
               jokeTextBox.setVisible(true);
            }
         }
      }
      
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

            repaint();
         }
      });
      timer.start();
   }

   public Ship getShip() {
      return ship;
   }

   /**
    * @return the planets
    */
   public Planet[] getPlanets() {
      return planets;
   }

   /**
    * @param planets the planets to set
    */
   public void setPlanets(Planet[] planets) {
      this.planets = planets;
   }

   public void linkWithSidePanel(SidePanel sidePanel) {
      jokeTextBox = sidePanel.getJokeTextBox();
   }

   private class KeyControl extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         if (menu.getIsShown())
            menu.toggleIsShown();
         else {
            int key = e.getKeyCode();
            switch (key) {
            case KeyEvent.VK_LEFT: // rotate angle counter-clockwise
               ship.changeAngle(-0.15);
               break;
            case KeyEvent.VK_RIGHT: // rotate angle clockwise
               ship.changeAngle(0.15);
               break;
            case KeyEvent.VK_UP: // increase thrust
               if (ship.getOnCelestial())
                  ship.changeThrust(0.4);
               else if (ship.getFuel() > 0)
                  ship.changeThrust(0.01);
               break;
            case KeyEvent.VK_DOWN: // decrease thrust
               ship.changeThrust(-0.4);
               break;
            case KeyEvent.VK_SPACE: // launch from planet
               ship.setOnCelestial(!ship.getOnCelestial());
               break;
            case KeyEvent.VK_ESCAPE: // show menu
               menu.toggleIsShown();
            }
         }
      }
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
         tester.setBounds(0, 0, 700, 700);
         tester.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               setEnabled(true);
            }
         });
         tester.setVisible(true);
         JPanel panel = new JPanel();
         panel.requestFocus();
         panel.add(new PeriodSliders());
         tester.add(panel);
      }
   }

   class PeriodSliders extends JPanel {
      PeriodSliders() {
         this.add(BorderLayout.NORTH,
               new JLabel("Period", SwingConstants.CENTER));
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(NUM_OF_PLANETS, 3));
         JLabel[] labels = labelGen();
         JTextField[] values = valueGen();
         JSlider[] sliders = sliderGen();
         for (int i = 0; i < NUM_OF_PLANETS; i++) {
            pairListeners(values[i], sliders[i], getPlanets()[i]);
            panel.add(labels[i]);
            panel.add(values[i]);
            panel.add(sliders[i]);
         }
         this.add(BorderLayout.SOUTH, panel);
      }

      private void pairListeners(JTextField value, JSlider slider,
            Planet planet) {
         value.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String str = ((JTextField)e.getSource()).getText();
               int strValue = 0;
               try {
                  strValue = Integer.parseInt(str);
               } catch (NumberFormatException ex) {}
               int newValue = strValue >= 1000 ? strValue : 10000;
               planet.setPeriodInMS(newValue);
               slider.setValue(newValue);
               value.setText(Integer.toString(newValue));
            }
         });
         slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               int newValue = ((JSlider)e.getSource()).getValue();
               planet.setPeriodInMS(newValue);
               value.setText(Integer.toString(newValue));
            }
         });
      }

      private JSlider[] sliderGen() {
         JSlider[] sliders = new JSlider[NUM_OF_PLANETS];
         int i = 0;
         for (Planet planet : getPlanets()) {
            sliders[i] = new JSlider(1000, 200000, planet.getPeriodInMS());
            i++;
         }
         return sliders;
      }

      private JTextField[] valueGen() {
         JTextField[] values = new JTextField[NUM_OF_PLANETS];
         int i = 0;
         for (Planet planet : getPlanets()) {
            values[i] = new JTextField(10);
            values[i].setHorizontalAlignment(SwingConstants.CENTER);
            values[i].setText(Integer.toString(planet.getPeriodInMS()));
            i++;
         }
         return values;
      }

      private JLabel[] labelGen() {
         JLabel[] labels = new JLabel[NUM_OF_PLANETS];
         int i = 0;
         for(Planet planet: getPlanets()){
            labels[i] = new JLabel(planet.getName(), SwingConstants.CENTER);
            i++;
         }
         return labels;
      }
   }
}