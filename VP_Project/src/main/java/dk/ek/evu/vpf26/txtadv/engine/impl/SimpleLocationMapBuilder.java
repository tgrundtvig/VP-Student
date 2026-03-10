package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.*;

import java.util.HashMap;
import java.util.Map;

public class SimpleLocationMapBuilder implements LocationMapBuilder
{
    private Map<LocationCoordinate, SimpleLocation> locations;

    public SimpleLocationMapBuilder()
    {
        locations = new HashMap<LocationCoordinate, SimpleLocation>();
    }

    @Override
    public void addLocation(LocationCoordinate coordinate, String name, String description)
    {
        SimpleLocation location = new SimpleLocation(coordinate, name, description);
        locations.put(coordinate, location);
    }

    @Override
    public void addLocation(int x, int y, int z, String name, String description)
    {
        addLocation(new LocationCoordinate(x, y, z), name, description);
    }

    @Override
    public LocationMap build()
    {
        for(SimpleLocation location : locations.values())
        {
            for(Direction dir :  Direction.values())
            {
                LocationCoordinate neighbourCoordinate = getNeighbour(location.getCoordinate(), dir);
                SimpleLocation neighbourLocation = locations.get(neighbourCoordinate);
                location.setNeighbour(dir, neighbourLocation);
            }
        }
        return new SimpleLocationMap(locations);
    }

    private static LocationCoordinate getNeighbour(LocationCoordinate coordinate, Direction dir)
    {
        return switch (dir)
        {
            case NORTH -> new LocationCoordinate(coordinate.x(), coordinate.y() + 1, coordinate.z());
            case SOUTH -> new LocationCoordinate(coordinate.x(), coordinate.y() - 1, coordinate.z());
            case EAST -> new LocationCoordinate(coordinate.x() + 1, coordinate.y(), coordinate.z());
            case WEST -> new LocationCoordinate(coordinate.x() - 1, coordinate.y(), coordinate.z());
            case UP -> new LocationCoordinate(coordinate.x(), coordinate.y(), coordinate.z() + 1);
            case DOWN -> new LocationCoordinate(coordinate.x(), coordinate.y(), coordinate.z() - 1);
        };
    }
}
