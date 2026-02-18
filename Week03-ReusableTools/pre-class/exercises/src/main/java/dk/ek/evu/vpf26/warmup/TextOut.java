package dk.ek.evu.vpf26.warmup;

/**
 * Interface for sending text output.
 * This is the output counterpart to TextIn.
 */
public interface TextOut
{
    /**
     * Send a message to the output.
     *
     * @param message the message to send
     */
    void send(String message);
}
