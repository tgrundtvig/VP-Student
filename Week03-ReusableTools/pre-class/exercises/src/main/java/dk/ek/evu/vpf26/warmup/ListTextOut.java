package dk.ek.evu.vpf26.warmup;

import java.util.ArrayList;
import java.util.List;

/**
 * A TextOut implementation that stores messages in a list.
 * This is the "test" implementation — you can inspect what was sent.
 * (Compare with ScriptedTextIn — the "test" TextIn.)
 */
public class ListTextOut implements TextOut
{
    private final List<String> messages = new ArrayList<>();

    @Override
    public void send(String message)
    {
        // TODO: Add the message to the messages list
    }

    /**
     * Returns all messages that have been sent to this output.
     *
     * @return the list of messages, in the order they were sent
     */
    public List<String> getMessages()
    {
        return messages;
    }
}
