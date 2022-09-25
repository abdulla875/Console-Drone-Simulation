package drone.java;


public class ConsoleCanvas {
   public int Rows;
   public int Columns;
   char [][] ConsoleCan;

    /**
     * Draws the canvus for the drone with 2d array where the border of the canvus are filled with #
     * and rest are just spaces.
     */
    public ConsoleCanvas(int Rows, int Columns) {
        this.Rows = Rows;
        this.Columns = Columns;
        ConsoleCan = new char[Rows][Columns];
        for(int i = 0; i < Rows; i++){
            for(int j = 0; j < Columns; j++){
                if(i==0 || j==0||i==Rows -1 ||j==Columns-1){
                    ConsoleCan[i][j] = '#';
                }
                else {
                    ConsoleCan[i][j] = ' ';
                }
            }
        }

    }

    /**
     *  display the drone inside the consle canvus
     * @param dronePositionX which is the drown position x
     * @param dronePositionY which is the drown position y
     * @param drone a character for the drone which will displayed inside the canvus
     */
    public void ShowIt(int dronePositionX, int dronePositionY, char drone){
            ConsoleCan[dronePositionX +1 ][dronePositionY + 1] = drone;
        }

    /**
     * display the console canvus
     */

    public String toString() {
        String console = "";
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                console = console + ConsoleCan[i][j] + " ";
            }
            console = console + "\n";
        }
        return console;
    }


}





