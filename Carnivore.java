import greenfoot.*;
import java.util.List;

/**
 * Carnivores eat herbivores
 * @author Bruce Gustin
 * @version ©2021.1.13
 */
public class Carnivore extends Animal
{
    // Carnivore Constants
    private final int SPEED = 2;
    private final int VISION_DISTANCE = 120;
    private final int TURN_AT_EDGE = 90;
    private final int FOOD_TO_BREED = 6;
    private final int BREED_AGE = 300;
    private final int AVE_LONGEVITY = 2000;
    private final int BROOD = 2;
    
    // Carnivore Variables
    private GreenfootImage image;
    private String imageFileName;
    private int longevity;
    private int fed;
    private Actor targetPrey;
    
    // General
    private int age;   
    
    public Carnivore()
    {
        turn(Greenfoot.getRandomNumber(360));
        longevity = (int) (AVE_LONGEVITY * (Greenfoot.getRandomNumber(100) / 80.0));
        move(30);
        fed = 0;
    }
 
    public void act()
    {
        seekFood();
        fed = eat(fed, Herbivore.class);
        parturition();
        die(age, longevity);
        age++;
    }
    
    protected void seekFood()
    {
        move(SPEED);
        
        if(isAtEdge()) 
        {
            turn(TURN_AT_EDGE);
        }
        
        // If there are old enough to hunt and hungry the turn to food.
        if(fed < FOOD_TO_BREED && age > BREED_AGE / 3)
        {
            List preyInSight = getObjectsInRange(VISION_DISTANCE, Herbivore.class);

            if(preyInSight.size() > 0)
            {
                targetPrey = (Actor) preyInSight.get(0);
                for(int i = 0; i < preyInSight.size(); i++)
                {
                    Actor prey = (Actor) preyInSight.get(i);
                    if(distTo(prey.getX(), prey.getY()) < distTo(targetPrey.getX(), targetPrey.getY()))
                    {
                        targetPrey = prey;
                    }
                }
                turnTowards(targetPrey.getX(), targetPrey.getY());
            }
        }
    }
    
    protected void parturition()
    {
        if(fed >= FOOD_TO_BREED && age > BREED_AGE)
        {
            fed = 0;
            for(int i = 0; i < BROOD; i++)
            {
                getWorld().addObject(new Carnivore(), getX() + 10, getY() + 10);
            }
        }
    }
}
