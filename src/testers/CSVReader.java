package testers;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import celestial.*;

/**
 * reads from a csv file and creates a arraylist of Information Objects that contains information about Planets
 * @author swati
 *
 */
public class CSVReader {
    private ArrayList<Information> infoData;
   
    CSVReader() {
        infoData = new ArrayList<Information>();
        
        try {
            Scanner input = new Scanner (new File("resources/info.txt"));
            
            int count = 0;
           
            while(input.hasNext()) {      
                //reads given file line by line and splits every line into tokens                  
                String[] token = input.nextLine().split(",");
                String[] tokens = token[1].split("#");               
                infoData.add(new Information(token[0], tokens));
 
              count++;
            }
            input.close();       
        } 
    
        catch (FileNotFoundException e) {
        System.out.println("File info.txt not found!");
    }
    
   }
    /**
     * @return the info
     */
    public ArrayList<Information> getInfoData() {
        return this.infoData;
    }
}