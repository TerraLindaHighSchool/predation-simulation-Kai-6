import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An abstract class to set the requirements for a class to be an animal.
 * 
 * @author Bruce Gustin
 * @version ©2021.1.13
 */
public abstract class Animal extends Actor
{
    protected abstract void seekFood();
    protected abstract void parturition();
    
    protected int eat(int fed, Class food)
    {
        if(isTouching(food))
        {
            fed++;
        }
        return fed;
    }
    
    protected void die(int age, int longevity)
    {
        if(age > longevity || isTouching(Carnivore.class))
        {
            getWorld().removeObject(this);
        }
    }
    protected double distTo(int x, int y)
    {
        return Math.abs(Math.sqrt(Math.pow(getX() - x, 2) + Math.pow(getY() - y, 2)));
    }
}
