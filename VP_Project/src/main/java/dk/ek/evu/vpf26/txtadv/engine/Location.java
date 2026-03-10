package dk.ek.evu.vpf26.txtadv.engine;

import java.util.Set;

public interface Location
{
    String name();

    String description();

    void addActor(Actor actor);

    boolean removeActor(Actor actor);

    void addItem(Item item);

    boolean removeItem(Item item);

    Set<Actor> getActors();

    Set<Item> getItems();

    Location getNeighbour(Direction direction);
}
