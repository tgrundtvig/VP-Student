package dk.ek.evu.vpf26.warmup;

/**
 * A trail is a chain of Waypoints. It starts at one Waypoint and follows
 * the links to walk the entire chain.
 */
public class Trail
{
    // TODO: add a field for the start waypoint

    public Trail(Waypoint start)
    {
        // TODO: store the start waypoint
    }

    public Waypoint getStart()
    {
        // TODO: return the first waypoint in the trail
        return null;
    }

    /**
     * Count the total number of waypoints in the trail by walking the chain
     * from start to end.
     */
    public int countWaypoints()
    {
        // TODO: walk the chain, counting each waypoint (including start)
        return 0;
    }

    /**
     * Find the last waypoint in the trail by walking the chain until
     * there is no next waypoint.
     */
    public Waypoint getEndpoint()
    {
        // TODO: walk the chain and return the last waypoint
        return null;
    }
}
