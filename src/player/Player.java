package player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player implements KeyListener
{
    int x;
    int y;
    // I wanted to play with image, so using a public domain image
    private BufferedImage image;
    int radius;

    Player()
    {
        x = 436;
        y = 436;
        this.radius = 15;

        try 
        {
            image = ImageIO.read(new File("image/gem.jpeg"));
        } 

        catch (IOException ex) 
        {
            System.out.println("file not found");
        }       
    }

    public void draw(Graphics g, ImageObserver imgOb) 
    {

        Graphics2D g2d = (Graphics2D)g;
        AffineTransform trans = new AffineTransform();
        trans.translate(x - radius/2, y - radius / 2);
        trans.scale(radius / (double)image.getWidth(),
                radius / (double)image.getHeight()); 
        AffineTransformOp op = new AffineTransformOp(trans,
                AffineTransformOp.TYPE_BILINEAR);
        g2d.drawImage(image, op, -1, -1);     
    }

    /**
     * updates the x and y coordinates each time player moves
     * @param x x-coordinate of the player
     * @param y y-coordinate of the player
     */
    private void updateCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;        
    }

    /**
     * specifies the number by which to change x and y coordinates
     * @param x [changes the x coordinate by specified x] 
     * @param y [changes the y coordinate by specified y]
     */
    public void moveByAmount(int x, int y) {
        this.x += x;
        this.y += y;

        updateCoordinates(this.x, this.y);
    }

    /**
     *  specifies the number by which to move when left, right, up and down keybs are pressed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.moveByAmount(-5, 0);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.moveByAmount(5, 0);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.moveByAmount(0, -5);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.moveByAmount(0, 5);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.moveByAmount(-5, 5);             
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.moveByAmount(5, 5);            
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.moveByAmount(-5, -5);            
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.moveByAmount(5, -5);            
        }         
    }

    @Override
    public void keyTyped(KeyEvent e) { }
  
    @Override
    public void keyReleased(KeyEvent e) { }
   
}
