package dk.ek.evu.vpf26.warmup;

import java.util.List;

/**
 * A service that sends notifications using a NotificationSender.
 *
 * This class was written BEFORE any NotificationSender implementation existed.
 * It was designed wishfully — the code describes what it needs, and the
 * interface contract ensures that any implementation will work.
 */
public class NotificationService
{
    private final NotificationSender sender;

    /**
     * Create a notification service that sends through the given sender.
     *
     * @param sender the sender to use for delivering notifications
     */
    public NotificationService(NotificationSender sender)
    {
        this.sender = sender;
    }

    /**
     * Send a single notification to one user.
     *
     * @param recipient the recipient's address
     * @param subject   the notification subject
     * @param message   the notification message body
     */
    public void notifyUser(String recipient, String subject, String message)
    {
        Notification notification = new Notification(recipient, subject, message);
        sender.send(notification);
    }

    /**
     * Send the same notification to multiple recipients.
     *
     * @param recipients the list of recipient addresses
     * @param subject    the notification subject
     * @param message    the notification message body
     */
    public void notifyAll(List<String> recipients, String subject, String message)
    {
        for (String recipient : recipients)
        {
            notifyUser(recipient, subject, message);
        }
    }
}
