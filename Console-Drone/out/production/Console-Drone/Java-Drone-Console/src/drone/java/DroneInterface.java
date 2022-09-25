package drone.java;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DroneInterface {

    private Scanner s; // scanner used for input from user
    private DroneArena myArena;// arena in which drones are shown
    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */

    public DroneInterface() {
        s = new Scanner(System.in);	          // set up scanner for user input
        myArena = new DroneArena(0, 0); // create arena of size 20*6
        char ch = ' ';
        do {
            //check if there is an arena and if not asks user to enter the arena//
            if(myArena.getArenaPositionX() == 0 || myArena.getArenaPositionY() == 0) {
                System.out.println("No Arena Enter (N) to create new arena\n");
            }
            System.out.print("Enter (A)dd drone, Enter (D)isplay, get (I)nformation, Enter (M)ove Drone, Enter (S)imulate" +
                    ", Enter (F)ile options, Enter (N)ew Arena or e(X)it > ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'A' :
                case 'a' :
                    myArena.addDrone();	// add a new drone to arena
                    break;
                case 'D' :
                case 'd' :
                    DoDisplay();
                    break;
                case 'M' :
                case 'm' :
                        myArena.MoveAllDrones(myArena);
                        DoDisplay();
                        break;
                case 'S' :
                case 's' :
                    // This will move the drones 10 times and then display the new drone information.//
                    for (int i = 0; i < 10; i ++) {
                        myArena.MoveAllDrones(myArena);
                        DoDisplay();
                        System.out.println(myArena.toString());

                        try {
                            TimeUnit.MILLISECONDS.sleep(500);

                        } catch (InterruptedException e) {
                            System.err.format("IOException: %s%n", e);
                        }
                    }

                    break;
                case 'I' :
                case 'i' :
                    System.out.print(myArena.toString());
                    break;
                case 'N' :
                case 'n' :
                    createArena();
                    break;
                case 'F' :
                case 'f' :
                    selectFile();
                    break;
                case 'x' : 	ch = 'X';				// when X detected program ends
                    break;
            }
        } while (ch != 'X');						// test if end

        s.close(); // close scanner

    }

    /**
     * this is to display the drone arena on the console,
     */
    void DoDisplay(){
        ConsoleCanvas c = new ConsoleCanvas(myArena.getArenaPositionX() +2 , myArena.getArenaPositionY() +2 );
        myArena.ShowDrones(c);
        System.out.print(c.toString());
    }

    /**
     * This is to let the users enter the arena position x and y and it accepts only numbers.
     */
    void createArena(){
        s = new Scanner(System.in);
        int arenaX;
        int arenaY;
        System.out.print("Enter X: ");
        while (!s.hasNextInt()){

            System.out.println("Please Enter Only Number: ");
            System.out.print("Enter X: ");
            s.nextLine();
        }
        arenaX = s.nextInt();

        System.out.print("Enter Y: ");
        while (!s.hasNextInt()){

            System.out.println("Please Enter only Number");
            System.out.print("Enter Y: ");
            s.nextLine();
        }
        arenaY = s.nextInt();
        myArena = new DroneArena(arenaX,arenaY);

    }

    /**
     * this is to save the arena size and the all the drone information to a chosen file.
     */
    void saveFile() {

        JFileChooser choseFile = new JFileChooser("C:\\Users\\aabdu\\Desktop\\file");
        choseFile.setDialogTitle("Save file: ");
        choseFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        s = new Scanner(System.in);
        String userFileName = " "; // Ask the user to enter name of file they want to save//
        System.out.println("Enter your file name");
        userFileName = s.next();
        int returnValue = choseFile.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File myFile =  new File (choseFile.getSelectedFile() + "\\" + userFileName + ".txt");
                System.out.println("The file has been saved at location: " + myFile.getAbsolutePath());
                FileWriter fileWriter = new FileWriter(myFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(Integer.toString(myArena.getArenaPositionX())); // saves the arenaPosition x//
                writer.write(" ");
                writer.write(Integer.toString(myArena.getArenaPositionY()));// saves the arenaPotion y//
                writer.newLine();

                int size = myArena.manyDrones.size();
                for (int i = 0; i < size; i++) {
                    Drone d = myArena.manyDrones.get(i);
                    writer.write(Integer.toString(d.getPositionX())); // Save the drone positionX//
                    writer.write(" ");
                    writer.write(Integer.toString(d.getPositionY())); // Save the drone positionY
                    writer.write(" ");
                    writer.write(Integer.toString(d.getDirection().ordinal())); // Save the drone direction //
                    writer.newLine();
                }
                writer.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * this is to load the chosen file and print out the arena information and the drone information to the console.
     */
    void loadFile(){
        BufferedReader brReader;
        File file;
        int returnVal;
        String currentLine;


            JFileChooser choseFile = new JFileChooser("C:\\Users\\aabdu\\Desktop\\file");
            choseFile.setDialogTitle("Load file: ");
            choseFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            returnVal = choseFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){ // if the file is successfully chosen then get the file//
                file = choseFile.getSelectedFile();

                try{
                    System.out.println("File is loaded");
                    if(!myArena.manyDrones.isEmpty()){
                        myArena.manyDrones.clear();
                    }
                    brReader = new BufferedReader(new FileReader(file));
                    currentLine = brReader.readLine();
                    String[] content = currentLine.split(" ");
                    int x = Integer.parseInt(content[0]); // load the arena position x//
                    int y = Integer.parseInt(content[1]); // load the arena position Y//
                    myArena = new DroneArena(x,y); // with the new loaded position create a arena//

                    // checking the next value is not equal to null and next line is not empty.//
                    while ( currentLine != null && !currentLine.isEmpty() ){
                        currentLine = brReader.readLine();
                        if( currentLine != null && !currentLine.isEmpty()){
                            String[] drone = currentLine.split(" ");
                            int droneX = Integer.parseInt(drone[0]); // load the drone positionX//
                            int droneY = Integer.parseInt(drone[1]); // load the drone positionY
                            int direction = Integer.parseInt(drone[2]); // load the drone direction//
                            Drone newLoadDrone = new Drone(droneX,droneY,Direction.values()[direction]);
                            myArena.manyDrones.add(newLoadDrone); // added it to arraylist //
                        }

                    }
                        brReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }

    /**
     * this gives the user the options to select either save file or load file, this method is being called in the
     * DroneInterface constructor.
     */
    void selectFile() {
        s = new Scanner(System.in);
        char ch = ' ';
            System.out.print("Enter (S)ave drone, Enter (L)oad drone > ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'S' :
                case 's' :
                    try {
                        saveFile();
                    }catch (Exception e){
                        System.out.println(" ");
                    }
                    break;
                case 'L' :
                case 'l' :
                    try {
                        loadFile();
                    }catch (Exception e){
                        System.out.println(" ");
                    }

                    break;
                case 'x' : 	ch = 'X';
                    break;
                default:
                    break;
            }
        }  // close scanner

    }



