package dk.ek.evu.vpf26.warmup;

/**
 * A named waypoint that can link to the next waypoint in a trail.
 * This is a simplified version of the Room pattern — instead of four
 * directions, a Waypoint links to just one next Waypoint.
 */
public class Waypoint
{
    // TODO: add fields for name (String) and next (Waypoint)

    public Waypoint(String name)
    {
        // TODO: store the name
    }

    public String getName()
    {
        // TODO: return the name
        return null;
    }

    public Waypoint getNext()
    {
        // TODO: return the next waypoint (null if there is none)
        return null;
    }

    public void setNext(Waypoint next)
    {
        // TODO: set the link to the next waypoint
    }

    public boolean hasNext()
    {
        // TODO: return true if there is a next waypoint
        return false;
    }
}
