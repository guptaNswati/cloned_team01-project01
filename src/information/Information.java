package information;

public class Information
{
   private String name;
   private String basic;
   private String diameter;
   private String mass;
   private String type;
   private String age;
   private String distance;

   public Information(String name, String basics, String diameter, String mass, String type, String age, String distance) {
      this.name = name;
      this.basic = basics;
      this.diameter = diameter;
      this.mass = mass;
      this.type = type;
      this.age = age;
      this.distance = distance;
   }

   public Information(String token, String[] tokens) {

      this.name = token;
      this.basic = tokens[0];
      this.diameter = tokens[1];
      this.mass = tokens[2];
      this.type = tokens[3];
      this.age = tokens[4];
      this.distance = tokens[5];               
   }

   public String divideBasic() {
      String features = "Did you know";
      String[] token = this.basic.split(".");

      for(int i = 0; i < token.length; i++)
      {
         features += token[i] + "\n";
      }
      return features;

   }

   public String toString() {
      String text = " ";

      text += this.name + "\n" + this.basic + "." + "\n" + this.diameter + "." 
            + "\n" + this.mass + "." + "\n" + this.type + "." + "\n" + this.age + "." + "\n" + this.distance + ".";

      return text;
   }

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      this.name = name;
   }

   /**
    * @return the basic
    */
   public String getBasic()
   {
      return basic;
   }

   /**
    * @param basic the basic to set
    */
   public void setBasic(String basic)
   {
      this.basic = basic;
   }

   /**
    * @return the diameter
    */
   public String getDiameter()
   {
      return diameter;
   }

   /**
    * @param diameter the diameter to set
    */
   public void setDiameter(String diameter)
   {
      this.diameter = diameter;
   }

   /**
    * @return the mass
    */
   public String getMass()
   {
      return mass;
   }

   /**
    * @param mass the mass to set
    */
   public void setMass(String mass)
   {
      this.mass = mass;
   }

   /**
    * @return the type
    */
   public String getType()
   {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(String type)
   {
      this.type = type;
   }

   /**
    * @return the age
    */
   public String getAge()
   {
      return age;
   }

   /**
    * @param age the age to set
    */
   public void setAge(String age)
   {
      this.age = age;
   }

   /**
    * @return the distance
    */
   public String getDistance()
   {
      return distance;
   }

   /**
    * @param distance the distance to set
    */
   public void setDistance(String distance)
   {
      this.distance = distance;
   }
}

/*package information;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Information
{
   private Map<String, Map<String, String>> info;
   private String[] elementNames = {"basic", "diameter", "mass",
                                    "type", "age", "distance"};
   
   *//**
    * Parse info stored in XML form.
    * 
    * Reference: http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
    *//*
   public Information() {
      try {
         File inputFile = new File("resources/info.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         
         NodeList nList = doc.getElementsByTagName("celestial");
         for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element elem = (Element) nNode;
               Map<String, String> tempInfo = new HashMap<String, String>();
               for (String name : elementNames) {
                  tempInfo.put(name, elem.getElementsByTagName(name).item(0).getTextContent());
               }
               info.put(elem.getElementsByTagName("name").item(0).getTextContent(), tempInfo);
            }
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found!");
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
*/