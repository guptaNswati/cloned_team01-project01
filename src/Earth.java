import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Earth extends JPanel
{
    private static final double EARTH_RADIUS_PROPORTION = .3 * Sun.getSunRadiusProportion();
    private static final double EARTH_DISTANCE_PROPORTION_SCREEN = 0.4;
    private Color earthColor;

    Earth(Color color)
    {
        this.earthColor = color;        
    }
    public static final double map(double value, double low1, double high1, double low2, double high2) {
        
        double diff = value - low1;
        double proportion = diff / (high1 - low1);
 
        return lerp(low2, high2, proportion);
    }
 
    // Linearly interpolate between two values
    public static final double lerp(double value1, double value2, double amt) {
        return ((value2 - value1) * amt) + value1;
    }
 
    void drawEarth(Graphics2D g2) {
        // Draw the earth
        // Calculate what portion along its orbit the earth is, and thus how
        // far to rotate about our centerpoint
        double earthTheta = map(15, 0, 365, 0, (2.0 * Math.PI));
// 
//        // Rotate our coordinate system by that much
        g2.rotate(earthTheta);
        // Translate the earth
        int distanceFromEarthToSun = (int) (EARTH_DISTANCE_PROPORTION_SCREEN * getWidth());
        g2.translate(distanceFromEarthToSun, 0);
 
        int earthRadius = (int) (EARTH_RADIUS_PROPORTION * getWidth());
//        GradientPaint earthColor = new GradientPaint(0, 0, Color.BLUE, 0, earthRadius, Color.GREEN.darker(), true);
        g2.setPaint(earthColor);
 
        g2.fillOval(-earthRadius/2, -earthRadius/2, earthRadius, earthRadius);
    }
    
//    public void paintComponent(Graphics g) 
//    {
//       Graphics2D g2 = (Graphics2D) g;
//            g2.translate(getWidth()/2, getHeight()/2);
//            
//            // Order matters, since the earth placement is dependent upon the sun
//            // placement, and the moon placement is dependent upon the earth placement
//            drawEarth(g2);
//        
//    }


}
