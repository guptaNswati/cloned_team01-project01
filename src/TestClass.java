import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class TestClass
{
    public static void main(String[] args)
  {
      JFrame frame = new JFrame("circle");
      frame.setPreferredSize(new Dimension(200, 200));  
      
      Circle circle_1 = new Circle(frame.getWidth(), frame.getHeight(), 20, Color.blue);
     
      frame.add(circle_1);
      
      frame.add(new Circle(frame.getWidth(), frame.getHeight(), 40, Color.green));
      
      
//      frame.add(new Sun(Color.YELLOW));
//      frame.add(new Earth(Color.ORANGE));
      
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);        
  }
//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
// 
////        drawSpaceBackdrop(g2);
// 
//        // Set the origin to be in the center of the screen
//        g2.translate(100, 100);
//        
//        Sun sun = new Sun(Color.YELLOW);
//        Earth earth = new Earth(Color.ORANGE);
// 
//        // Order matters, since the earth placement is dependent upon the sun
//        // placement, and the moon placement is dependent upon the earth placement
//        sun.drawSun(g2);
// 
//        earth.drawEarth(g2);
//    }
}
