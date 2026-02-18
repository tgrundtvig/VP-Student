package dk.ek.evu.vpf26.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingTest
{
    // --- FormalGreeting tests ---

    @Test
    void formalGreeting_greet_returnsFormattedGreeting()
    {
        FormalGreeting greeting = new FormalGreeting();
        assertEquals("Good day, Alice.", greeting.greet("Alice"));
    }

    @Test
    void formalGreeting_greet_worksWithDifferentName()
    {
        FormalGreeting greeting = new FormalGreeting();
        assertEquals("Good day, Bob.", greeting.greet("Bob"));
    }

    @Test
    void formalGreeting_implementsGreeting()
    {
        FormalGreeting formal = new FormalGreeting();
        assertInstanceOf(Greeting.class, formal);
    }

    // --- CasualGreeting tests ---

    @Test
    void casualGreeting_greet_returnsFormattedGreeting()
    {
        CasualGreeting greeting = new CasualGreeting();
        assertEquals("Hey Alice!", greeting.greet("Alice"));
    }

    @Test
    void casualGreeting_greet_worksWithDifferentName()
    {
        CasualGreeting greeting = new CasualGreeting();
        assertEquals("Hey Bob!", greeting.greet("Bob"));
    }

    @Test
    void casualGreeting_implementsGreeting()
    {
        CasualGreeting casual = new CasualGreeting();
        assertInstanceOf(Greeting.class, casual);
    }

    // --- Receptionist tests ---

    @Test
    void receptionist_welcome_withFormalGreeting()
    {
        Receptionist receptionist = new Receptionist(new FormalGreeting());
        assertEquals("Good day, Alice.", receptionist.welcome("Alice"));
    }

    @Test
    void receptionist_welcome_withCasualGreeting()
    {
        Receptionist receptionist = new Receptionist(new CasualGreeting());
        assertEquals("Hey Alice!", receptionist.welcome("Alice"));
    }
}
