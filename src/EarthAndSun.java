import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class EarthAndSun extends JComponent
{
    private static final double SUN_RADIUS_PROPORTION = 0.1;
    
    private static final double MERCURY_RADIUS_PROPORTION = .4 * SUN_RADIUS_PROPORTION;
    
    private static final double VENUS_RADIUS_PROPORTION = .5 * SUN_RADIUS_PROPORTION;
    
    private static final double EARTH_RADIUS_PROPORTION = .6 * SUN_RADIUS_PROPORTION;
    
    private static final double MARS_RADIUS_PROPORTION = .5 * SUN_RADIUS_PROPORTION;
    
    private static final double MERCURY_DISTANCE_PROPORTION_SCREEN = 0.1; 
    
    private static final double VENUS_DISTANCE_PROPORTION_SCREEN = 0.10;
 
    private static final double EARTH_DISTANCE_PROPORTION_SCREEN = 0.11;
    
    private static final double MARS_DISTANCE_PROPORTION_SCREEN = 0.11;
    
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 640;
    
    public EarthAndSun() 
    {
        super();
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));        
    }
    
    // draws sun
    private void drawSun(Graphics2D g2)
    {
      int sunRadius = (int) (SUN_RADIUS_PROPORTION * getWidth());
      System.out.println(sunRadius);
        g2.setPaint(Color.YELLOW);
        g2.fillOval(-sunRadius/2, -sunRadius/2, sunRadius, sunRadius);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(-150, -150, 600,400, 0, 360);
       
    }
    
    private void drawMercury(Graphics2D g2) 
    {

        int distanceBetweenSunAndMercury = (int) (MERCURY_DISTANCE_PROPORTION_SCREEN * getWidth());
        
        g2.translate(distanceBetweenSunAndMercury, 0);
        int mercuryRadius = (int) (MERCURY_RADIUS_PROPORTION * getWidth());
        g2.setColor(Color.ORANGE);
        
        
        g2.fillOval(-mercuryRadius/2, -mercuryRadius/2, mercuryRadius, mercuryRadius);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(-125, -90, 550,350, 0, 360);
    }
    
    private void drawVenus(Graphics2D g2) 
    {

        int distanceBetweenMercuryAndVenus = (int) (VENUS_DISTANCE_PROPORTION_SCREEN * getWidth());
        
        g2.translate(distanceBetweenMercuryAndVenus, 0);
        int venusRadius = (int) (VENUS_RADIUS_PROPORTION * getWidth());
        g2.setColor(Color.RED);
               
        g2.fillOval(-venusRadius/2, -venusRadius/2, venusRadius, venusRadius);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(-100, -75, 500,300, 0, 360);
    }
    
    // draws earth
    private void drawEarth(Graphics2D g2) 
    {                
        int distanceBetweenEarthAndSun = (int) (EARTH_DISTANCE_PROPORTION_SCREEN * getWidth());
        
        // sets the x-coordinate and y-coordinate
        g2.translate(distanceBetweenEarthAndSun, 0);
 
        int earthRadius = (int) (EARTH_RADIUS_PROPORTION * getWidth());
        g2.setPaint(Color.blue);
        
        g2.fillOval(-earthRadius/2, -earthRadius/2, earthRadius, earthRadius);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(-75, -55, 450,250, 0, 360);
    }
    
    private void drawMars(Graphics2D g2) 
    {

        int distanceBetweenEarthAndMars = (int) (MARS_DISTANCE_PROPORTION_SCREEN * getWidth());
        
        g2.translate(distanceBetweenEarthAndMars, 0);
        int marsRadius = (int) (MARS_RADIUS_PROPORTION * getWidth());
        g2.setColor(Color.green);
                
        g2.fillOval(-marsRadius/2, -marsRadius/2, marsRadius, marsRadius);
        
        g2.setColor(Color.BLACK);
        g2.drawArc(-50, -40, 400, 200, 0, 360);
    }
      
    
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;

        // Set the origin to be in the center of the screen
        g2.translate(getWidth()/4, getHeight()/4);
 
        drawSun(g2);
        
        drawMercury(g2);
        
        drawVenus(g2);
 
        drawEarth(g2);  
        
        drawMars(g2);
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Solar System");
      
        final EarthAndSun view = new EarthAndSun();
        frame.add( view);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    } 

}
