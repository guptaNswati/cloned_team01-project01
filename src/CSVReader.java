import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
// object of type PlanetInformation will contain information about planets
public class CSVReader
{
    private String[][] info;
    /**
     * @return the info
     */
    public String[][] getInfo()
    {
        return info;
    }

    private String [] names;
    
    CSVReader()
    {
        Scanner input;
        info = new String[9][1];
        names = new String[9];

        /*
         * reads given file line by line and splits every line into tokens 
         * and stores these tokens into countryName, yearLable and cellularDataTable arrays
         */
        try 
        {
            input = new Scanner (new File("resources/info.txt"));
            
            int count = 0;
            while(input.hasNext())
            {
                
                String[] token = input.nextLine().split(",");
                names[count] = token[0];
                info[count][0] = token[1];                    
              count++;
            }
            input.close();       
        } 
    catch (FileNotFoundException e) 
    {
        System.out.println("File sun.txt not found!");
    }
    
}
    
    public static void main(String[] args)
    {
        CSVReader information = new CSVReader();
        
        information.getInfo();
        
        for(int i = 0; i < information.getInfo().length; i++)
        {
            System.out.println(information.getInfo()[i][0].toString());           
        }
            
        
    }
   

}
