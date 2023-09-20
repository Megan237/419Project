/**
 * RR (Round Robin) scheduling algorithm.
 *
 */
 
import java.util.*;

public class RR implements Algorithm
{

    private final Queue<Process> readyQueue;
    private final Queue<Process> processesToSchedule;
    private ArrayList<Process> processesBackUp = new ArrayList<>();
    private final int totalNumProcesses;
    private static final int quantum = 5;
    public RR(List<Process> allProcessList) {
        readyQueue = new LinkedList<>();
        processesToSchedule = new LinkedList<>();
        totalNumProcesses = allProcessList.size();
        for(Process p: allProcessList){
            processesToSchedule.add(p);
            processesBackUp.add(new Process(p.getName(), p.getArrivalTime(), p.getCPUBurstTime()));
        }

    }

    @Override
    public void schedule() {
        System.out.println("Round Robin Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;

        Process currentProcess;

        /**
         * add first process to the ready queue
         */
        Process p = processesToSchedule.remove();
        readyQueue.add(p);
        if (CPU.getCurrentTime() < p.getArrivalTime()) {
            CPU.advanceTimeTo(p.getArrivalTime());
        }

        while (true) {
            if (processesToSchedule.isEmpty()) break;
            if (CPU.getCurrentTime() >= processesToSchedule.peek().getArrivalTime())
                readyQueue.add(processesToSchedule.remove());
            else
                break;
        }

        while (!readyQueue.isEmpty()) {
            currentProcess = pickNextProcess();
            readyQueue.remove(currentProcess);

            /**
             * Calculate the waiting time of the selected process
             */

            //program finishes
            if (currentProcess.getCPUBurstTime() <= quantum) {
                int originalBurstTime = 0;
                for (Process original: processesBackUp){
                    if (original.equals(currentProcess))
                        originalBurstTime = original.getCPUBurstTime();
                }
                CPU.run(currentProcess, currentProcess.getCPUBurstTime());
                int wTime = 0;
                if (CPU.getCurrentTime() > currentProcess.getArrivalTime()) {
                    wTime = CPU.getCurrentTime() - currentProcess.getArrivalTime() - originalBurstTime;
                }
                totalWaitingTime += wTime;
                System.out.println(currentProcess.getName() + " finished at time " + CPU.getCurrentTime() + ". Its waiting time is: " + wTime);
            } //program keeps going
            else {
                CPU.run(currentProcess, quantum);
                currentProcess.setBurst(currentProcess.getCPUBurstTime() - quantum);
                readyQueue.add(currentProcess);

            }
            while (true) {
                if (processesToSchedule.isEmpty()) break;
                if (CPU.getCurrentTime() >= processesToSchedule.peek().getArrivalTime())
                    readyQueue.add(processesToSchedule.remove());
                else
                    break;
            }
        }

        /**
         * We need to cast either the numerator or the denominator to the double type;
         * otherwise, when both are integers, their division result will always be rounded to integer
         */

        double averageWaitingTime = totalWaitingTime / (double) totalNumProcesses ;
        /**
         * use printf for formatted out (only show two digits after the decimal point).
         */

        System.out.printf("\nThe average waiting time is: %.2f\n", averageWaitingTime);

    }

    @Override
    public Process pickNextProcess() {
        return readyQueue.remove();
    }
}

