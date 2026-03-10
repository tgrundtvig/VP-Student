package dk.ek.evu.vpf26.txtadv.engine;

public interface LocationMapBuilder
{
    void addLocation(LocationCoordinate coordinate, String name, String description);
    void addLocation(int x, int y, int z, String name, String description);
    LocationMap build();
}
