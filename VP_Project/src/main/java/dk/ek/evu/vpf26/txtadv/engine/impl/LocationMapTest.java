package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.Direction;
import dk.ek.evu.vpf26.txtadv.engine.Location;
import dk.ek.evu.vpf26.txtadv.engine.LocationMap;
import dk.ek.evu.vpf26.txtadv.engine.LocationMapBuilder;

public class LocationMapTest
{
    static void main()
    {
        LocationMapBuilder builder = new SimpleLocationMapBuilder();
        builder.addLocation(1, 0, 0, "A", "Location A");
        builder.addLocation(1, 1, 0, "B", "Location B");
        builder.addLocation(0, 1, 0, "C", "Location C");
        builder.addLocation(2, 1, 0, "D", "Location D");
        builder.addLocation(1, 1, 1, "E", "Location E");
        LocationMap lm = builder.build();

        Location a = lm.getLocation(1, 0, 0);
        Location b = a.getNeighbour(Direction.NORTH);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        Location c = b.getNeighbour(Direction.WEST);
        if(!"C".equalsIgnoreCase(c.name()))
        {
            throw new RuntimeException("Expected C, but got:" + c.name());
        }
        Location d = b.getNeighbour(Direction.EAST);
        if(!"D".equalsIgnoreCase(d.name()))
        {
            throw new RuntimeException("Expected D, but got:" + d.name());
        }
        Location e = b.getNeighbour(Direction.UP);
        if(!"E".equalsIgnoreCase(e.name()))
        {
            throw new RuntimeException("Expected E, but got:" + e.name());
        }
        a = b.getNeighbour(Direction.SOUTH);
        if(!"A".equalsIgnoreCase(a.name()))
        {
            throw new RuntimeException("Expected A, but got:" + a.name());
        }
        b = c.getNeighbour(Direction.EAST);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        b = d.getNeighbour(Direction.WEST);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }
        b = e.getNeighbour(Direction.DOWN);
        if(!"B".equalsIgnoreCase(b.name()))
        {
            throw new RuntimeException("Expected B, but got:" + b.name());
        }



        IO.println("Everything seems to work as expected!");


    }
}
