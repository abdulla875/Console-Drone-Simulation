package drone.java;

import java.util.Random;

public enum Direction {
    North,East,South,West; // All the directions//

    /**
     * this is to get random Direction
     */
    public static Direction GetRandomDire(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    /**
     * this is to get the next given direction
     */
    public Direction NextDirection(){
           return values()[(ordinal()+1)%values().length];
    }




}

