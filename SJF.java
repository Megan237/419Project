/**
 * Non-preemptive SJF (Shortest-Job First) scheduling algorithm.
 *
 */

import java.util.*;

public class SJF implements Algorithm
{

    private final Queue<Process> readyQueue;
    private final Queue<Process> processesToSchedule;
    private final int totalNumProcesses;

    public SJF(List<Process> allProcessList) {

        readyQueue = new LinkedList<>();
        processesToSchedule = new LinkedList<>();
        totalNumProcesses = allProcessList.size();
        for(Process p: allProcessList){
            processesToSchedule.add(p);
        }
    }

    @Override
    public void schedule() {
        System.out.println("Shortest Job First Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;

        Process currentProcess;

        /**
         * add first process to the ready queue
         */
        Process p = processesToSchedule.remove();
        readyQueue.add(p);

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
            int wTime = 0;
            if (CPU.getCurrentTime() > currentProcess.getArrivalTime()){
                wTime = CPU.getCurrentTime() - currentProcess.getArrivalTime();
            }

            totalWaitingTime += wTime;

            CPU.run(currentProcess, currentProcess.getCPUBurstTime());

            System.out.println(currentProcess.getName() + " finished at time "+CPU.getCurrentTime() + ". Its waiting time is: " + wTime);

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
        int shortestTime = Integer.MAX_VALUE;
        Process shortestProcess = null;
        for (Process p:readyQueue){
            if (p.getCPUBurstTime() < shortestTime) {
                shortestTime = p.getCPUBurstTime();
                shortestProcess = p;
            }
        }

        return shortestProcess;
    }
}
