package dk.ek.evu.vpf26.txtadv.engine;

public interface LocationMap
{
    void moveActor(Actor actor, Location from, Location to);
    Location getLocation(int x, int y, int z);
    Location getLocation(LocationCoordinate coordinate);
}
