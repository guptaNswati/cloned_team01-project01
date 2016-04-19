import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class EarthAndSun extends JComponent
{
    private static final double SUN_RADIUS_PROPORTION = 0.2;
    
    private static final double EARTH_RADIUS_PROPORTION = .6 * SUN_RADIUS_PROPORTION;
 
    private static final double EARTH_DISTANCE_PROPORTION_SCREEN = 0.2;
    
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
        g2.setPaint(Color.YELLOW);
        g2.fillOval(-sunRadius/2, -sunRadius/2, sunRadius, sunRadius);
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
    }
      
    
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;

        // Set the origin to be in the center of the screen
        g2.translate(getWidth()/2, getHeight()/2);
 
        drawSun(g2);
 
        drawEarth(g2);       
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
