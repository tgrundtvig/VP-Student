package dk.ek.evu.vpf26.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarmupTest
{
    // --- Waypoint tests ---

    @Test
    void waypoint_getName_returnsName()
    {
        Waypoint wp = new Waypoint("Summit");
        assertEquals("Summit", wp.getName());
    }

    @Test
    void waypoint_newWaypoint_hasNoNext()
    {
        Waypoint wp = new Waypoint("Summit");
        assertNull(wp.getNext());
    }

    @Test
    void waypoint_hasNext_falseWhenNoNext()
    {
        Waypoint wp = new Waypoint("Summit");
        assertFalse(wp.hasNext());
    }

    @Test
    void waypoint_setNext_linksWaypoints()
    {
        Waypoint first = new Waypoint("Trailhead");
        Waypoint second = new Waypoint("Summit");
        first.setNext(second);
        assertSame(second, first.getNext());
    }

    @Test
    void waypoint_hasNext_trueWhenLinked()
    {
        Waypoint first = new Waypoint("Trailhead");
        Waypoint second = new Waypoint("Summit");
        first.setNext(second);
        assertTrue(first.hasNext());
    }

    // --- Trail tests ---

    @Test
    void trail_getStart_returnsFirstWaypoint()
    {
        Waypoint start = new Waypoint("Trailhead");
        Trail trail = new Trail(start);
        assertSame(start, trail.getStart());
    }

    @Test
    void trail_countWaypoints_singleWaypoint()
    {
        Waypoint only = new Waypoint("Lonely Peak");
        Trail trail = new Trail(only);
        assertEquals(1, trail.countWaypoints());
    }

    @Test
    void trail_countWaypoints_threeWaypoints()
    {
        Waypoint a = new Waypoint("Trailhead");
        Waypoint b = new Waypoint("River Crossing");
        Waypoint c = new Waypoint("Summit");
        a.setNext(b);
        b.setNext(c);
        Trail trail = new Trail(a);
        assertEquals(3, trail.countWaypoints());
    }

    @Test
    void trail_getEndpoint_singleWaypoint()
    {
        Waypoint only = new Waypoint("Lonely Peak");
        Trail trail = new Trail(only);
        assertSame(only, trail.getEndpoint());
    }

    @Test
    void trail_getEndpoint_threeWaypoints()
    {
        Waypoint a = new Waypoint("Trailhead");
        Waypoint b = new Waypoint("River Crossing");
        Waypoint c = new Waypoint("Summit");
        a.setNext(b);
        b.setNext(c);
        Trail trail = new Trail(a);
        assertSame(c, trail.getEndpoint());
    }
}
