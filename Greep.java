import greenfoot.*; // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author Michael Xing
 * @version 0.1
 */
public class Greep extends Creature {
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    // memory = int
    // flags = bool array w/ 2 spots
    
    /**
    * Default constructor for testing purposes.
    */
    public Greep() {
        this(null);
    }
    
    
    /**
    * Create a Greep with its home space ship.
    */
    public Greep(Ship ship) {
        super(ship);
        setMemory(99);
        /*if(getWorld().getObjects(Counter.class).get(0).getValue() == 3){
            setRotation(270);
        }*/
    }
    
    
    /**
    * Do what a greep's gotta do.
    */
    public void act() {
        super.act(); // do not delete! leave as first statement in act().
        //Paint Colors: red, orange, purple
        //Level Number getWorld().getObjects(Counter.class).get(0).getValue();
        
        if (carryingTomato()) {
            //==============================
            //          Has a tomato
            //==============================
            if (atShip()) {
                dropTomato();
                turn(180);
                move();
                setFlag(1, false);
            } else {
                spit("red");
                if(getMemory() == 5){
                    turnHome();
                    if(getFlag(2)){
                        turn(180);
                    }
                    turn(90);
                    move();
                } else if(getMemory() == 10){
                    turnHome();
                    move();
                } else if(getMemory() < 10){
                    move();
                } else if (!atWater() && !atWorldEdge()) {
                    turnHome();
                    move();
                } else {
                    setMemory(0);
                    if(!getFlag(1)){
                        if(Greenfoot.getRandomNumber(2) < 1){
                            setFlag(2, true);
                        } else{
                            setFlag(2, false);
                        }
                        setFlag(1, true);
                    }
                    turn(180);
                    move();
                }
                
                
                if(getMemory() < 30){
                    setMemory(getMemory() + 1);
                } else if(getMemory() == 30){
                    setFlag(1, false);
                }
            }
        } else {
        //==============================
        //          No tomato
        //==============================
            if (!checkFood()) {
                if (atWater()) {
                    if (Greenfoot.getRandomNumber(2) == 0) {
                    turn(10 * (Greenfoot.getRandomNumber(4) + 5));
                    } else {
                    turn(10 * (Greenfoot.getRandomNumber(4) + 27));
                    }
                    move();
                    checkFood();
                } else if (atWorldEdge()) {
                    turn((Greenfoot.getRandomNumber(35) + 1) * 10);
                    move();
                    checkFood();
                } else if (seePaint("red")) {
                    turnHome();
                    turn((Greenfoot.getRandomNumber(3) + 17) * 10);
                    move();
                } else {
                    move();
                    checkFood();
                }
            }
        }
    }
    
    /**
    * Is there any food here where we are? If so, try to load some!
    */
    public boolean checkFood() {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if (tomatoes != null) {
            loadTomato();
            return true;
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
        return false;
    }
    
    
    /**
    * This method specifies the name of the author (for display on the result board).
    */
    public static String getAuthorName() {
        return "Michael Xing"; // write your name here!
    }
    
    
    /**
    * This method specifies the image we want displayed at any time. (No need 
    * to change this for the competition.)
    */
    public String getCurrentImage() {
        if (carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}