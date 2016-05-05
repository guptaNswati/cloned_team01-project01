package information;

/**
 * an object of Information class contains information about a planet. Planets name and other basic features.
 * @author swati
 *
 */
public class Information {
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

    /**
     * constructor that takes a string and array of string objects that sets information members
     */
    public Information(String token, String[] tokens) {

        this.name = token;
        this.basic = tokens[0];
        this.diameter = tokens[1];
        this.mass = tokens[2];
        this.type = tokens[3];
        this.age = tokens[4];
        this.distance = tokens[5];               
    }

    /**
     * this divides the basic information in multiple lines for easy reading
     */
    public String divideBasic() {
        String features = "Did you know";
        String[] token = this.basic.split(".");

        for(int i = 0; i < token.length; i++)
        {
            features += token[i] + "\n";
        }
        return features;

    }

    /**
     * returns this as a String
     */
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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the basic
     */
    public String getBasic() {
        return basic;
    }

    /**
     * @param basic the basic to set
     */
    public void setBasic(String basic) {
        this.basic = basic;
    }

    /**
     * @return the diameter
     */
    public String getDiameter() {
        return diameter;
    }

    /**
     * @param diameter the diameter to set
     */
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    /**
     * @return the mass
     */
    public String getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(String mass) {
        this.mass = mass;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }
}
