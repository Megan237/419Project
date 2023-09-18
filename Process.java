/**
 * Process to be scheduled by the scheduling algorithm.
 *
 * Each process is represented by:
 *
 *    String name - a unique process name
 *
 *    int CPUBurstTime - the length of the CPU burst of the process.
 *    (It's assumed that each process has only a single CPU burst and no I/O wait.)
 *
 */

public class Process
{
    private final String name;

    /**
     * arrivalTime represents the arrival time of a process
     */
    private int arrivalTime;

    /**
     * burst represents the CPU burst time required by a process;
     * Assume every process has a single CPU burst and no I/O wait.
     */
    private int CPUBurstTime;



    public Process(String name, int arrivalTime, int CPUBurstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.CPUBurstTime = CPUBurstTime;
    }

    /**
     * Appropriate getters
     */
    public String getName() {
        return name;
    }


    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCPUBurstTime() {
        return CPUBurstTime;
    }

    /**
     * Appropriate setters
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurst(int burst) {
        this.CPUBurstTime = burst;
    }

    /**
     * We override equals() so we can use a
     * Process object in Java collection classes.
     */
    public boolean equals(Object other) {
        /**
         * if the two are the same object, then they are equal
         */
        if (other == this)
            return true;

        if (!(other instanceof Process))
            return false;

        /**
         * If the two processes have the same name, then they are equal
         */
        Process p = (Process)other;
        return (this.name == p.name);
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", CPUBurstTime=" + CPUBurstTime +
                '}';
    }
}
