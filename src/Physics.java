import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



public class ScheduledExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Runnable delayedTask = new Runnable() {
            @Override
            public void run()
            {
                // Physics
                // Update planet positions
                for (Planet p : planets) {
                    // Increment angle by fixed arc length
                    // Arc length formula: s = r * theta -> theta = s / r 
                    p.angle = (p.angle + 0.01 / p.dist) % 6.28;
                }
                
                // Update ship position
                if (ship.onPlanet) {
                    ship.x = planets.get(ship.planet, radius) * cos(ship.angle) + planets.get(ship.planet, x);
                    ship.y = planets.get(ship.planet, radius) * sin(ship.angle) + planets.get(ship.planet, y);
                }
                else {
                    float newX = ship.x
                    float newY = ship.y
                    for (Planet p : planets) {
                        // TODO: gConst must be initialized
                        newX -= gConst * p.mass / (ship.x - p.x)^2;
                        newY -= gConst * p.mass / (ship.y - p.y)^2;
                    }
                    ship.x = newX;
                    ship.y = newY;
                }
                
                // Rules
                
                // Redraw
                draw();
                
                System.out.println(Thread.currentThread().getName()+" updated");
            }
        };

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);

        // Loop task
        scheduledPool.scheduleWithFixedDelay(delayedTask, 0, 100, TimeUnit.MILLISECONDS);
    };
};
