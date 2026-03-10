package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.Actor;
import dk.ek.evu.vpf26.txtadv.engine.Location;
import dk.ek.evu.vpf26.txtadv.engine.LocationCoordinate;
import dk.ek.evu.vpf26.txtadv.engine.LocationMap;

import java.util.Map;

public class SimpleLocationMap implements LocationMap
{
    private final Map<LocationCoordinate, SimpleLocation> locations;

    public SimpleLocationMap(Map<LocationCoordinate, SimpleLocation> locations)
    {
        this.locations = locations;
    }

    @Override
    public void moveActor(Actor actor, Location from, Location to)
    {
        from.removeActor(actor);
        to.addActor(actor);
        actor.setCurrentLocation(to);
    }

    @Override
    public Location getLocation(int x, int y, int z)
    {
        return getLocation(new LocationCoordinate(x, y, z));
    }

    @Override
    public Location getLocation(LocationCoordinate coordinate)
    {
        return locations.get(coordinate);
    }
}
