package dk.ek.evu.vpf26.txtadv;

public class Room
{
    private String name;
    private String description;
    private Room n;
    private Room s;
    private Room e;
    private Room w;

    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        n = null;
        s = null;
        e = null;
        w = null;
    }

    public void connectNorth(Room otherRoom)
    {
        this.n = otherRoom;
        otherRoom.s = this;
    }

    public void connectEast(Room otherRoom)
    {
        this.e = otherRoom;
        otherRoom.w = this;
    }

    public Room getNorth()
    {
        return n;
    }

    public Room getEast()
    {
        return e;
    }

    public Room getSouth()
    {
        return s;
    }

    public Room getWest()
    {
        return w;
    }

    public String getRoomDescription()
    {
        StringBuilder res = new StringBuilder();
        res.append("Room: " + name + "\n");
        res.append("Description: " + description + "\n");
        res.append("Exits:\n");
        if (n != null)
        {
            res.append("North\n");
        }
        if(e != null)
        {
            res.append("East\n");
        }
        if(s != null)
        {
            res.append("South\n");
        }
        if(w != null)
        {
            res.append("West\n");
        }
        return res.toString();
    }
}
