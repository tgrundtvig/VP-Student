package dk.ek.evu.vpf26.warmup;

import java.util.ArrayList;
import java.util.List;

/**
 * A NotificationSender that stores notifications in a list.
 * This is the "test" implementation — you can inspect what was sent.
 * (Compare with ListTextOut from Week 03.)
 */
public class ListSender implements NotificationSender
{
    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void send(Notification notification)
    {
        // TODO: Add the notification to the notifications list
    }

    /**
     * Returns all notifications that have been sent.
     *
     * @return the list of notifications, in the order they were sent
     */
    public List<Notification> getNotifications()
    {
        return notifications;
    }
}
