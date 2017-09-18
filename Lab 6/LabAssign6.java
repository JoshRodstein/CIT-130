package Code;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author joshua.rodstein
 */
public class LabAssign6 {

    public static void main(String[] args) throws FileNotFoundException {
        Finch atticus = new Finch();
        String newLine, fileName = "Lab6Data.txt";
        String[] tokens;
        File file = new File(fileName);
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()) {
            newLine = readFile.nextLine();
            tokens = newLine.split(" ");
            System.out.println(newLine);

            if (tokens.length == 2) {
                    try {
                        atticus.setLED(Color.GREEN);
                        atticus.playTone(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException nfe) {
                        atticus.saySomething("Invalid Input");
                        atticus.setLED(Color.RED, 1000);
                    } 
            } else {
                atticus.saySomething("Invalid Input");
                atticus.setLED(Color.RED, 1000);
              
            }
                

        }
        atticus.quit();
        System.exit(0);
    } 
}

