/**
 * Driver.java
 * 
 * Demonstrates different scheduling algorithms.
 * 
 * Command-line Usage:
 *  
 *  java Driver <algorithm> <schedule>
 *
 * where 
 *  schedule is name of the text file containing th schedule of tasks;
 *        (it is assumed that the tasks are listed in the correct arriving order)
 *
 *  algorithm = [FCFS, SJF, RR] (not case-sensitive)
 *
 *  If you use an IDE (highly recommended), specify those two arguments in "Run Configuration"
 */
  
import java.util.*;
import java.io.*;

public class Driver
{
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Driver <algorithm> <schedule>");
            System.exit(0);
        }

        BufferedReader inFile = new BufferedReader(new FileReader(args[1]));

        String schedule;

        /**
         This is a list of all processes that will be used for the simulation
         Processes are sorted by their arrival time.
         Note: this is NOT the Ready queue.
         */
        final List<Process> allProcessList = new ArrayList<>();

        // read in the tasks and populate the Ready queue
        while ( (schedule = inFile.readLine()) != null) {
            String[] params = schedule.split(",\\s*");
            allProcessList.add(new Process(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }

        inFile.close();
        
        Algorithm scheduler = null;
        String choice = args[0].toUpperCase();

        switch(choice) {
            case "FCFS":
                scheduler = new FCFS(allProcessList);
                break;
            case "SJF":
                scheduler = new SJF(allProcessList);
                break;
            case "RR":
                scheduler = new RR(allProcessList);
                break;
            default:
                System.err.println("Invalid algorithm");
                System.exit(0);
        }

        // start the scheduler
        scheduler.schedule();
    }
}
