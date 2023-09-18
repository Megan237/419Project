/**
 * Interface representing a generic scheduling algorithm.
 *
 * Your implementation must implement this interface.
 */

public interface Algorithm
{
    /**
     * Invokes the scheduler
     */
    void schedule();

    /**
     * Selects the next task using the appropriate scheduling algorithm
     */
    Process pickNextProcess();
}
