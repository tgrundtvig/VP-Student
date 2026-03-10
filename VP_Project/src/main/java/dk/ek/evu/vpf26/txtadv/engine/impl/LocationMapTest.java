package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.Direction;
import dk.ek.evu.vpf26.txtadv.engine.Location;
import dk.ek.evu.vpf26.txtadv.engine.LocationMap;
import dk.ek.evu.vpf26.txtadv.engine.LocationMapBuilder;

public class LocationMapTest
{
    public static void main(String[] args)
    {
        LocationMapBuilder builder = null;
        builder.addLocation(1, 0, 0, "A", "Location A");
        builder.addLocation(1, 1, 0, "B", "Location B");
        builder.addLocation(0, 1, 0, "C", "Location C");
        builder.addLocation(2, 1, 0, "D", "Location D");
        builder.addLocation(1, 1, 1, "E", "Location E");
        LocationMap lm = builder.build();

        Location a = lm.getLocation(1, 0, 0);
        Location b = a.getNeigbour(Direction.NORTH);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        Location c = b.getNeigbour(Direction.WEST);
        if(!"C".equalsIgnoreCase(c.name()))
        {
            throw new RuntimeException("Expected C, but got:" + c.name());
        }
        Location d = b.getNeigbour(Direction.EAST);
        if(!"D".equalsIgnoreCase(d.name()))
        {
            throw new RuntimeException("Expected D, but got:" + d.name());
        }
        Location e = b.getNeigbour(Direction.UP);
        if(!"E".equalsIgnoreCase(e.name()))
        {
            throw new RuntimeException("Expected E, but got:" + e.name());
        }
        a = b.getNeigbour(Direction.SOUTH);
        if(!"A".equalsIgnoreCase(a.name()))
        {
            throw new RuntimeException("Expected A, but got:" + a.name());
        }
        b = c.getNeigbour(Direction.EAST);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        b = c.getNeigbour(Direction.WEST);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        b = e.getNeigbour(Direction.DOWN);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        IO.println("Everything seems to work as expected!");
    }
}
