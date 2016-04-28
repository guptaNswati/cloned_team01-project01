package ship;

import java.awt.Point;
import java.util.LinkedList;

public class Ship {
    private LinkedList<Point> coordinate = new LinkedList<Point>();
    private double speed;
    private double angle;
    // -1 is free flight, 0-8 are celestial bodies
    private int onCelestial;
    
    public Ship() {
        coordinate.push(new Point(0, 0));
        speed = 0;
        angle = 0;
        onCelestial = 3;
    }
}
