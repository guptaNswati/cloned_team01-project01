/**
 * Process and store celestial bodies' information.
 */

package information;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Information
{
   private Map<String, Map<String, String>> info;
   private String[] elementNames = {"basic", "temperature", "mass",
                                    "diameter", "distance", "period"};

   /**
    * Parse info stored in XML form.
    * 
    * Reference: http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
    */
   public Information() {
      try {
         File inputFile = new File("resources/Info.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         info = new HashMap<>();
         
         NodeList nList = doc.getElementsByTagName("celestial");
         for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element elem = (Element) nNode;
               Map<String, String> tempInfo = new HashMap<String, String>();
               for (String name : elementNames)
                  tempInfo.put(name, elem.getElementsByTagName(name).item(0).getTextContent());
               info.put(elem.getElementsByTagName("name").item(0).getTextContent(), tempInfo);
            }
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found!");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Returns the info for the named planet as a string with line breaks.
    * 
    * @param name
    *           planet name
    * @return info for the planet
    */
   public String getCelestial(String name) {
      String tempInfo = "";
      for (String key : elementNames) {
         tempInfo += toTitleCase(key) + ": " + info.get(name).get(key).toString() + "\n\n";
      }
      return tempInfo.trim();
   }

   /**
    * Converts a string to Title Case.
    * 
    * @param string
    *           A string
    * @return Title case of the string.
    */
   private String toTitleCase(String string) {
      String[] tokens = string.split("\\s");
      String result = "";

      for(int i = 0; i < tokens.length; i++){
          char capLetter = Character.toUpperCase(tokens[i].charAt(0));
          result += capLetter + tokens[i].substring(1) + " ";
      }
      return result.trim();
   }
}
