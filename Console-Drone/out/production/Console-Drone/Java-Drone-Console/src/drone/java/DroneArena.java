package drone.java;

import java.util.*;
import java.util.Random;
import java.util.ArrayList;

public class DroneArena {
    private int arenaPositionX;
    private  int arenaPositionY;
    private Drone ranD;
    private Direction NewDir;
    public int positionX;
    public int positionY;
    ArrayList <Drone> manyDrones;
    Random randomGenerator = new Random();

    /**
     * Constructor for the drone arena which include the drone arena position x and y and array list to add many drone
     * to the arena.
     *
     */
    public DroneArena (int arenaPositionX, int arenaPositionY){
        this.arenaPositionX = arenaPositionX;
        this.arenaPositionY = arenaPositionY;
        this.manyDrones = new ArrayList<Drone>(); // array list to store many drones//
    }

    /**
     *  this is the random generator for the drone position x and y and random drone direction.
     *  so this will give the random number for the drone positions and with a random direction.
     */
    public void randomGen() {
      int  xMax = arenaPositionX; // This makes sure x position of the drone is not grater then arenaPositionX//
      int yMax = arenaPositionY;// This makes sure y position of the drone is not grater then arenaPositionY//
        positionX = randomGenerator.nextInt(xMax);
        positionY = randomGenerator.nextInt(yMax);
        NewDir = Direction.GetRandomDire(); // gets the random direction method from direction class//
    }

    // getter for the drone arena position x //
    public int getArenaPositionX() {
        return arenaPositionX;
    }
    // getter for the drone arena position y//
    public int getArenaPositionY() {
        return arenaPositionY;
    }

    /**
     *  checks if there is a drone, if there is a drone then return the drone
     *
     */
    public  Drone getDroneAt(int x, int y){

        Drone drone = null;
        int size = manyDrones.size();
        for(int i = 0; i <size; i ++){
            Drone d = manyDrones.get(i);
            if(d.isHere(x,y) == true){
                drone = d;
            }
        }
            return drone;
    }

    /**
     * this is to add each drone at different position with random numbers to the array list
     * it looks for a free space for the new drone, it will look until free space is found and then it will add it
     * if there is no space to add drone it will print out the message arena is full.
     */
     public void addDrone(){
        int totalSize = arenaPositionX * arenaPositionY;
         if(manyDrones.size()< totalSize){

             do{
                 randomGen();
             }while (getDroneAt(positionX,positionY) != null);
                 ranD = new Drone(positionX,positionY, NewDir);
                 manyDrones.add(ranD);
         }else if (manyDrones.isEmpty() == false){
             System.out.println("\n Arena is full\n");
         }




   }

    /**
     *  this is to check if the drone can move to a given position
     *
     */

    public boolean CanMoveHere(int x, int y) {
        if (getDroneAt(x, y) != null || x >= arenaPositionX || y >= arenaPositionY || x < 0 || y < 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * this is to display the drone inside the console canvus.
     */
   public void ShowDrones(ConsoleCanvas c){
        for(Drone x : manyDrones){
            x.DisplayDrone(c);

        }
   }

    /**
     * move all the drones
     */
   public void MoveAllDrones(DroneArena arena){
        for(Drone drone : manyDrones)
        drone.TryToMove(arena);
   }

    /**
     *  shows information about the arena and all the drones.
     */
    public String toString() {

        String droneArena = "Drone Area size is " + arenaPositionX + " by " + arenaPositionY + "\n";
        for(Drone y : manyDrones) droneArena += y.toString() +"\n";
        return droneArena;



    }
}
