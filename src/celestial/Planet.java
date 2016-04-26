import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Point;

public class Planet extends Celestial implements KeyListener
{
  /**
   * An object of a Planet class inherits from Celestial. It also has three
   * additional members, distanceToSun, periodInMS, angleToSun.
   */
  private final int distanceToSun;
  private final int periodInMS;
  private double angleToSun;
  private Information infoData;
  private JTextField text;

  public Planet() 
  {
      super();
      distanceToSun = 1;
      periodInMS = 1;

  }

  public Planet(Color color, String name, int radius, int distanceToSun,
          double angleToSun, int periodInMS, String[][] data, String[] namesForInfo) 
  {
      super(new Point(), color, name, radius);
      this.distanceToSun = distanceToSun > 0 ? distanceToSun : 10;
      this.setAngleToSun(angleToSun);
      this.periodInMS = periodInMS > 1000 ? periodInMS : 1000;
      // set Planet's initial x and y
      
      text = new JTextField();
      
      
      int count = 0;     
      while(count < namesForInfo.length)
      {
          if(super.getName().equalsIgnoreCase(namesForInfo[count]))
          {                  
              String[] tokens = data[count][0].split("#");
              this.setInfoData(new Information(tokens));
              break;              
          }
          else
              count++;              
      }
     
      text.setText(infoData.getBasic()); // add basic information here by default
//      text.addKeyListener(this);
      text.setVisible(true);
     

      System.out.print(text.isShowing());
  }
  
  

  public int getDistanceToSun() 
  {
      return distanceToSun;
  }

  public double getAngleToSun() 
  {
      return angleToSun;
  }

  public void setAngleToSun(double angleToSun) 
  {
      this.angleToSun = angleToSun;
  }

  public int getPeriodInMS() 
  {
      return periodInMS;
  }

  public Information getInfoData()
  {
      return infoData;
  }

  public void setInfoData(Information infoData)
  {
      this.infoData = infoData;
  }


    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e)
    {       
        ArrayList<String> listOfFeatures = new ArrayList<String>();
        listOfFeatures.add(infoData.getDiameter());
        listOfFeatures.add(infoData.getMass());
        listOfFeatures.add(infoData.getType());
        listOfFeatures.add(infoData.getAge());
        listOfFeatures.add(infoData.getDistance());
        
             // info.get previous;
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                System.out.println("left");
                int count = 5;
                
                while(count != 1)
                {
                    
                    listOfFeatures.get(count);
                    count--;             
                }
           
            }
               // info.get next;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                System.out.println("right");
                int count = 0;
                
                while(count != 5)
                {
                    listOfFeatures.get(count);
                    count++;             
                }
                
            }
               
         }        

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }

}
