package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import celestial.Celestial;
import celestial.Planet;
import physics.Constants;
import physics.Physics;
import ship.Arrow;
import ship.Ship;
import testers.CSVReader;
import testers.Information;

/**
 * An update object contains all dynamic graphical elements.
 * It contains a paintComponent method necessary for GUI.
 * The run method updates physical coordinates and GUI elements at regular intervals.
 */
public class GameFrame extends JPanel
{
    private Celestial sun;
    private Planet [] planets;
    private Ship ship;
    private Arrow arrow;

    // added new members 
    // player
    Player player =  new Player();

    // information data 
    private ArrayList<Information> info;

    // keeps track of players last planet 
    String planetWithPlayer;

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
            10, //Mercury
            12, //Venus
            15, //Earth
            23,  //Mars
            27, //Jupiter
            25, //Saturn
            22, //Uranus
            20 //Neptune
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


    public GameFrame() {
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
        arrow = new Arrow("resource/arrow-sample.png", planets[2].getCoordinate());
        toggleKeyListener();

        // adding key listener on player of this panel 
        this.addKeyListener(player); 

        CSVReader csv = new CSVReader();
        info = csv.getInfoData();
        planetWithPlayer = "";
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

        player.draw(g, this);
        //draw all planets
        for (Planet planet : planets) {
            planet.draw(g); //draws planet

            // checks the distance between planets and player and displays information appropriately
            if(calculateDistance((int)planet.getX(), (int)planet.getY(),(int) planet.getRadius()) < 2)
            {
                for(int i = 1; i < info.size(); i++)
                {
                    if(info.get(i).getName().equals(planet.getName()) && planetWithPlayer != info.get(i).getName())
                    {                        
                        JOptionPane.showMessageDialog(null,this.info.get(i),"Did you know!", JOptionPane.INFORMATION_MESSAGE); 
                        planetWithPlayer = info.get(i).getName();
                    }
                }

            }

            //draws planet orbit path
            //             g2d.drawOval((int)sun.getX() - planet.getDistanceToSun(), 
            //                   (int)sun.getY() - planet.getDistanceToSun(),
            //                   planet.getDistanceToSun() * 2, planet.getDistanceToSun() * 2);
            //             System.out.println((int)sun.getX() - planet.getDistanceToSun());
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

    /**
     * calculates the distance between planets and palyer
     * @param x [x-coordinate of object]
     * @param y [y-coordinate of object]
     * @param radius [size of object]
     * @return distance between objects
     */       
    public int calculateDistance(int x, int y, int radius) 
    {
        int distance = (int) Math.sqrt( (((x-player.x) * (x-player.x)) + ((y-player.y) * (y -player.y))) -(radius + player.radius));
        return distance;
    }
}

