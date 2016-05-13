package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
   private boolean isShown;
   
   public Menu() {
      isShown = true;
   }

   public boolean getIsShown() {
      return isShown;
   }

   public void toggleIsShown() {
      isShown = !isShown;
   }

   public void draw(Graphics g) {
      if (isShown) {
         g.setColor(new Color(0, 0, 0, 128));
         g.fillRect(0, 0, 1000, 1000);
         g.setColor(Color.white);
         g.setFont(new Font("Arial", Font.BOLD, 72));
         g.drawString("PLANET HOPPER", 180, 380);
         g.setFont(new Font("Arial", Font.BOLD, 18));
         g.drawString("LEFT / RIGHT : Ship angle", 364, 630);
         g.drawString("UP / DOWN : Ship thrust", 382, 670);
         g.drawString("SPACEBAR : Launch/reset ship", 378, 710);
         g.setFont(new Font("Arial", Font.BOLD, 24));
         g.drawString("Controls", 439, 580);
         g.setColor(new Color(255, 255, 255, (int)(Math.sin(System.currentTimeMillis() * Math.PI / 500) * 127 + 128)));
         g.drawString("Press any key to start", 366, 480);
      }
   }
}
