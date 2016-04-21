import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Sun extends JPanel
{
    private static final double SUN_RADIUS_PROPORTION = 0.2;
    private Color sunColor;
    Sun (Color color)
    {
        this.sunColor = color;
    }
    
    void drawSun(Graphics2D g2) {
        int sunRadius = (int) (SUN_RADIUS_PROPORTION * getWidth());
//        GradientPaint sunColor = new GradientPaint(0, 0, Color.YELLOW, 0, sunRadius, Color.RED);
        g2.setPaint(sunColor);
        g2.fillOval(-sunRadius/2, -sunRadius/2, sunRadius, sunRadius);
        
        
    }
    
//    public void paintComponent(Graphics g) 
//    {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.translate(getWidth()/2, getHeight()/2);
//        
//        // Order matters, since the earth placement is dependent upon the sun
//        // placement, and the moon placement is dependent upon the earth placement
//        drawSun(g2);
//    }

    public static double getSunRadiusProportion()
    {
        return SUN_RADIUS_PROPORTION;
    }

    
}
