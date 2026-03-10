package dk.ek.evu.vpf26.txtadv.engine;

import java.util.List;

public interface Location
{
    String name();

    String description();

    void addActor(Actor actor);

    boolean removeActor(Actor actor);

    void addItem(Item item);

    boolean removeItem(Item item);

    List<Actor> getActors();

    List<Item> getItems();

    Location getNeigbour(Direction direction);
}
