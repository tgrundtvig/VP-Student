package dk.ek.evu.vpf26.warmup;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest
{
    // --- Notification record tests ---

    @Test
    void notification_storesRecipient()
    {
        Notification n = new Notification("alice@example.com", "Hello", "Welcome!");
        assertEquals("alice@example.com", n.recipient());
    }

    @Test
    void notification_storesSubject()
    {
        Notification n = new Notification("alice@example.com", "Hello", "Welcome!");
        assertEquals("Hello", n.subject());
    }

    @Test
    void notification_storesMessage()
    {
        Notification n = new Notification("alice@example.com", "Hello", "Welcome!");
        assertEquals("Welcome!", n.message());
    }

    // --- ListSender tests ---

    @Test
    void listSender_noSends_emptyList()
    {
        ListSender sender = new ListSender();
        assertTrue(sender.getNotifications().isEmpty());
    }

    @Test
    void listSender_sendOne_storesIt()
    {
        ListSender sender = new ListSender();
        Notification n = new Notification("bob@example.com", "Hi", "How are you?");
        sender.send(n);
        assertEquals(1, sender.getNotifications().size());
        assertEquals("bob@example.com", sender.getNotifications().get(0).recipient());
    }

    @Test
    void listSender_sendMultiple_storesAllInOrder()
    {
        ListSender sender = new ListSender();
        sender.send(new Notification("a@example.com", "First", "1"));
        sender.send(new Notification("b@example.com", "Second", "2"));
        sender.send(new Notification("c@example.com", "Third", "3"));
        assertEquals(3, sender.getNotifications().size());
        assertEquals("a@example.com", sender.getNotifications().get(0).recipient());
        assertEquals("b@example.com", sender.getNotifications().get(1).recipient());
        assertEquals("c@example.com", sender.getNotifications().get(2).recipient());
    }

    // --- EmailSender tests ---

    @Test
    void emailSender_formatsCorrectly()
    {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try
        {
            EmailSender sender = new EmailSender();
            sender.send(new Notification("alice@example.com", "Hello", "Welcome!"));
        }
        finally
        {
            System.setOut(original);
        }
        String expected = "[EMAIL] To: alice@example.com | Subject: Hello | Message: Welcome!"
                + System.lineSeparator();
        assertEquals(expected, captured.toString());
    }

    // --- Both implement NotificationSender ---

    @Test
    void bothImplementations_areNotificationSender()
    {
        NotificationSender email = new EmailSender();
        NotificationSender list = new ListSender();
        assertNotNull(email);
        assertNotNull(list);
    }

    // --- NotificationService tests ---

    @Test
    void service_notifyUser_sendsOneNotification()
    {
        ListSender sender = new ListSender();
        NotificationService service = new NotificationService(sender);
        service.notifyUser("alice@example.com", "Update", "Your order shipped.");
        assertEquals(1, sender.getNotifications().size());
        Notification sent = sender.getNotifications().get(0);
        assertEquals("alice@example.com", sent.recipient());
        assertEquals("Update", sent.subject());
        assertEquals("Your order shipped.", sent.message());
    }

    @Test
    void service_notifyAll_sendsToAllRecipients()
    {
        ListSender sender = new ListSender();
        NotificationService service = new NotificationService(sender);
        List<String> recipients = List.of("a@example.com", "b@example.com", "c@example.com");
        service.notifyAll(recipients, "Announcement", "System maintenance tonight.");
        assertEquals(3, sender.getNotifications().size());
        assertEquals("a@example.com", sender.getNotifications().get(0).recipient());
        assertEquals("b@example.com", sender.getNotifications().get(1).recipient());
        assertEquals("c@example.com", sender.getNotifications().get(2).recipient());
        // All share the same subject and message
        for (Notification n : sender.getNotifications())
        {
            assertEquals("Announcement", n.subject());
            assertEquals("System maintenance tonight.", n.message());
        }
    }

    @Test
    void service_notifyAll_emptyList_sendsNothing()
    {
        ListSender sender = new ListSender();
        NotificationService service = new NotificationService(sender);
        service.notifyAll(List.of(), "Empty", "No one to send to.");
        assertTrue(sender.getNotifications().isEmpty());
    }
}
