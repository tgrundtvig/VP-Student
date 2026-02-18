package dk.ek.evu.vpf26.warmup;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TextOutTest
{
    // --- ListTextOut tests ---

    @Test
    void listTextOut_noMessages_emptyList()
    {
        ListTextOut out = new ListTextOut();
        assertTrue(out.getMessages().isEmpty());
    }

    @Test
    void listTextOut_sendOneMessage_storesIt()
    {
        ListTextOut out = new ListTextOut();
        out.send("Hello");
        assertEquals(1, out.getMessages().size());
        assertEquals("Hello", out.getMessages().get(0));
    }

    @Test
    void listTextOut_sendMultipleMessages_storesAllInOrder()
    {
        ListTextOut out = new ListTextOut();
        out.send("First");
        out.send("Second");
        out.send("Third");
        assertEquals(3, out.getMessages().size());
        assertEquals("First", out.getMessages().get(0));
        assertEquals("Second", out.getMessages().get(1));
        assertEquals("Third", out.getMessages().get(2));
    }

    // --- ConsoleTextOut tests ---

    @Test
    void consoleTextOut_sendMessage_printsToSystemOut()
    {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try
        {
            ConsoleTextOut out = new ConsoleTextOut();
            out.send("Hello Console");
        }
        finally
        {
            System.setOut(original);
        }
        assertEquals("Hello Console" + System.lineSeparator(), captured.toString());
    }

    // --- Both implement TextOut ---

    @Test
    void bothImplementations_areTextOut()
    {
        TextOut console = new ConsoleTextOut();
        TextOut list = new ListTextOut();
        assertNotNull(console);
        assertNotNull(list);
    }
}
