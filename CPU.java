/**
 * The simulated CPU
 *
 */
 
public class CPU
{
    private static int currentTime = 0;

    /*
     * Simulate running the specified task for the specified slice of time.
     */
    public static void run(Process task, int slice) {
        //fast forward to the arrival time of the next task
        if (task.getArrivalTime() > currentTime ){
            currentTime = task.getArrivalTime();
        }
        System.out.println("Start running " + task + " at time " + currentTime);

        /*It is assumed that none of the scheduling algorithm is preemptive,
          so a task can always finish its given time slice
         */
        currentTime += slice;
    }

    public static int getCurrentTime(){
        return currentTime;
    }

    public static void advanceTimeTo(int time) {
        currentTime = time;
    }
}
