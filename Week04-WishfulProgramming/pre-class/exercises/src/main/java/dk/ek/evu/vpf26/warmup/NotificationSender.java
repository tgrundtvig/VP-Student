package dk.ek.evu.vpf26.warmup;

/**
 * Interface for sending notifications.
 * Implementations decide HOW to send — email, SMS, logging, etc.
 * The caller only knows WHAT to send.
 */
public interface NotificationSender
{
    /**
     * Send a notification.
     *
     * @param notification the notification to send
     */
    void send(Notification notification);
}
