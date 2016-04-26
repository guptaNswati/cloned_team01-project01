
public class Information
{
    private String name;
    private String basic;
    /**
     * @return the name
     */
    public String getName()
    {
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

    private String diameter;
    private String mass;
    private String type;
    private String age;
    private String distance;
    
    Information(String name, String basics, String diameter, String mass, String type, String age, String distance)
    {
        this.name = name;
        this.basic = basics;
        this.diameter = diameter;
        this.mass = mass;
        this.type = type;
        this.age = age;
        this.distance = distance;
    }
    
   Information(String[] tokens)
   {
       
           this.name = tokens[0];
           this.basic = tokens[1];
           this.diameter = tokens[2];
           this.mass = tokens[3];
           this.type = tokens[4];
           this.age = tokens[5];
           this.distance = tokens[6];          
     
   }

}
