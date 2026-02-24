package dk.ek.evu.vpf26.warmup;

/**
 * A NotificationSender that formats and prints notifications as emails.
 *
 * Output format: [EMAIL] To: {recipient} | Subject: {subject} | Message: {message}
 *
 * This is a "real" implementation — in production, it might actually send
 * an email. For this exercise, printing to the console is enough.
 */
public class EmailSender implements NotificationSender
{
    @Override
    public void send(Notification notification)
    {
        // TODO: Print a formatted email notification to System.out.println
        // Format: [EMAIL] To: {recipient} | Subject: {subject} | Message: {message}
    }
}
