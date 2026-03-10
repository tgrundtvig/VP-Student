package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.*;

import java.util.*;

public class SimpleLocation implements Location
{
    private final LocationCoordinate coordinate;
    private final String name;
    private final String description;
    private final Set<Actor> actors;
    private final Set<Item> items;
    private final Location[] neighbours;

    public SimpleLocation(LocationCoordinate coordinate, String name, String description)
    {
        this.coordinate = coordinate;
        this.name = name;
        this.description = description;
        this.actors = new HashSet<>();
        this.items = new HashSet<>();
        this.neighbours = new Location[Direction.values().length];
    }

    @Override
    public String name()
    {
        return name;
    }

    @Override
    public String description()
    {
        return description;
    }

    @Override
    public void addActor(Actor actor)
    {
        actors.add(actor);
    }

    @Override
    public boolean removeActor(Actor actor)
    {
        return actors.remove(actor);
    }

    @Override
    public void addItem(Item item)
    {
        items.add(item);
    }

    @Override
    public boolean removeItem(Item item)
    {
        return items.remove(item);
    }

    @Override
    public Set<Actor> getActors()
    {
        return Collections.unmodifiableSet(actors);
    }

    @Override
    public Set<Item> getItems()
    {
        return Collections.unmodifiableSet(items);
    }

    @Override
    public Location getNeighbour(Direction direction)
    {
        return neighbours[direction.ordinal()];
    }

    public LocationCoordinate getCoordinate()
    {
        return coordinate;
    }

    public void setNeighbour(Direction direction, Location neighbour)
    {
        neighbours[direction.ordinal()] = neighbour;
    }
}
