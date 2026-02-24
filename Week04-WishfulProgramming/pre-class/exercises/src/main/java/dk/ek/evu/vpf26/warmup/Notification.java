package dk.ek.evu.vpf26.warmup;

/**
 * An immutable data carrier for a notification.
 * Contains the recipient, subject, and message body.
 *
 * This is a record because it is pure data — no behavior.
 */
public record Notification(String recipient, String subject, String message)
{
}
