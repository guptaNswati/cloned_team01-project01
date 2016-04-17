import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.*;

public class Player
{
   private int xPos;
   private int yPos;
   private boolean isOnPlanet; //false when traveling through space "hop"
   public boolean [] planetsVisited = new boolean[8];
   //1-Mercury, 2-Venus, 3-Earth, 4-Mars, 5-Jupiter, 6-Saturn, 7-Uranus, 8-Neptune
   
 
   //CONSTRUCTOR
    public Player() {
        xPos = 0; //get Earth's xPos
        yPos = 0; //get Earth's yPos
       Arrays.fill(planetsVisited, Boolean.FALSE);
       planetsVisited[3] = true;
       isOnPlanet = true;
    }
    
   
    
    public void addToX(int x) {
       xPos += x;
    }
    
    
    public void addToY(int y) {
       yPos += y;
    }
    
    
    private void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       
       if(key == KeyEvent.VK_LEFT)
       {
          //hop direction degrees --
       }
       
       if(key == KeyEvent.VK_RIGHT)
       {
          //hop direction degrees ++
       }
       
       if(key == KeyEvent.VK_UP)
       {
          //hop power ++
       }
       
       if(key == KeyEvent.VK_DOWN)
       {
          //hop power --
       }
    }
    
    //draw player
    public void draw(Graphics g){
       g.setColor(Color.BLACK);
       g.drawRect(xPos, yPos, 10, 50);
    }
    
    //GETTERS
    public int getXPos() {
       return xPos;
    }
    
    public int getYPos() {
       return yPos;
    }
    
    public boolean getIsPlayerOnPlanet() {
       return isOnPlanet;
    }
   
   
   
   public static void main(String[] args)
   {
      
      Player joe = new Player();
      //joe.draw(g);

   }

}
