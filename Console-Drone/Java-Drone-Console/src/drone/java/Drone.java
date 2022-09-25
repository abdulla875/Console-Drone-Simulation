package drone.java;


public class Drone {
    private int positionX; // Drone positionX//
    private int  positionY; // Drone position Y//
    private Direction direction; // direction of the drone//
    private  int droneID; // drone ID//
    private static int droneIdCount = 1; // count the drone ID//

    /**
     * this is a constructor for the Drone class which holds drone position X & Y and the Drone Direction
     * @param positionX Drone positionX
     * @param positionY Drone positionY
     * @param Direc Drone direction
     */

    public Drone(int positionX, int positionY, Direction Direc){
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = Direc;
        droneID = droneIdCount;
        droneIdCount++; // this will increment by 1 everytime you add a Drone//
    }

    /**
     *  the method below is to check the drone position and check if the position already taken
     */

    public boolean isHere(int px, int py){

        if (px == positionX && py == positionY){
            return true;
        }
        return false;
    }


    /**
     * This method is to display the Drone inside the ConsoleCanvas
     * @param c which is canvas where we will display the drone
     */


    public void DisplayDrone(ConsoleCanvas c){
            c.ShowIt(positionX, positionY, 'D');

    }

    /**
     *  This first checks weather the drone can move, if the drone can move then it will move the drone
     *  if it can't move the drone then it will change the direction of the drone.
     */

    public void TryToMove(DroneArena x){

        switch (direction){
            case North:
                if(x.CanMoveHere(positionX -1,positionY))
                    positionX = positionX -1;
                else
                    direction = direction.NextDirection();
                break;

            case East:
                if (x.CanMoveHere(positionX,positionY+1))
                    positionY = positionY + 1;
                else
                    direction = direction.NextDirection();
                break;
            case South:
                if (x.CanMoveHere(positionX + 1 ,positionY))
                    positionX = positionX + 1;
                else
                    direction = direction.NextDirection();
                break;
            case West:
                if (x.CanMoveHere(positionX ,positionY-1))
                    positionY = positionY - 1;
                else
                    direction = direction.NextDirection();
                break;
            default:
                break;

        }


    }

    /**
     * This is to print out the information about the drone
     * @return information about the drone
     */

    public String toString() {
        return "Drone " + droneID + " at" +
                " position = " + positionX +
                ", " + positionY + " Direction is: " + direction.toString();

    }

    /**
     *  Getter function for the drone position x and y and drone Direction
     */

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Direction getDirection() {
        return direction;
    }
}
