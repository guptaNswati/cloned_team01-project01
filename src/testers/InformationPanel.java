package testers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import physics.Constants;

/**
 * adds the textboxes containing information planets in a separate panel on screen
 * @author swati
 *
 */
public class InformationPanel extends JPanel{  
    
   public InformationPanel() {
        super.setSize(Constants.FRAME_WIDTH/4, Constants.FRAME_HEIGHT); 
        super.setLocation(5, 5);
        this.setBackground(Color.WHITE);
        
        CSVReader csv = new CSVReader();
        ArrayList<Information> info = csv.getInfoData();
       
        Iterator<Information> info_iterator = info.iterator();
        
        Information infoKey;
   
        while(info_iterator.hasNext()) {
            infoKey = info_iterator.next();   
            this.add(new JScrollPane(new JTextArea(infoKey.toString(), 8, 15)));             
        }        
    }
}
