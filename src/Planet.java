import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Planet extends JPanel
{
    private String name;
    private double radiusProportion;
    private double planetDistanceProportion;
    private Color color;
    
    Planet(String planetName, double radiusProportion, double distance, Color color)
    { 
        this.name = planetName;
        this.radiusProportion = radiusProportion;
        this.planetDistanceProportion = distance;
        this.color = color;       
    }
 
//    public void drawPlanet(Graphics2D g2)
//    {
//       int planetDistanceFromSun = (int) (this.planetDistanceProportion * getWidth());
//       g2.translate(planetDistanceFromSun, 0);
//       int planetRadius = (int) (this.radiusProportion * getWidth());
//       g2.setColor(this.color);
//       g2.fillOval(-planetRadius/2, -planetRadius/2, planetRadius, planetRadius);        
//    }
    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.translate(getWidth()/2, getHeight()/2);
        
        int planetDistanceFromSun = (int) (this.planetDistanceProportion * getWidth());
        
        g2.translate(planetDistanceFromSun, 0);
        
        int planetRadius = (int) (this.radiusProportion * getWidth());
        
        g2.setColor(this.color);
        
        g2.fillOval(-planetRadius/2, -planetRadius/2, planetRadius, planetRadius);  
        
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("circle");
        frame.setPreferredSize(new Dimension(200, 200));
        Planet sun = new Planet("Sun", .6, .4, Color.YELLOW);       
        frame.add(sun);
        frame.add(new Planet("Earth", .4, .1, Color.GREEN));
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        
    }

}
