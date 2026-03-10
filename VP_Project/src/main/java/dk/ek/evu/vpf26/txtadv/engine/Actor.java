package dk.ek.evu.vpf26.txtadv.engine;

import java.util.List;

public interface Actor
{
    void takeTurn();

    Location getCurrentLocation();

    void setCurrentLocation(Location location);

    void addItem(Item item);

    void removeItem(Item item);

    boolean hasItem(Item item);

    List<Item> getItems();
}
