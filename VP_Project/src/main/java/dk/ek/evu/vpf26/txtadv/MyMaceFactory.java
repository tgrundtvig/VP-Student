package dk.ek.evu.vpf26.txtadv;

public class MyMaceFactory
{
    public Mace createMyMace()
    {
        Room r2_0 = new Room("Start", "It looks like a normal living room!");
        Room r0_1 = new Room("The blue room", "This room has blue walls");
        Room r1_1 = new Room("The green room", "This room has green walls");
        Room r2_1 = new Room("The red room", "This room has red walls");
        Room r3_1 = new Room("The yellow room", "This room has yellow walls");
        Room r4_1 = new Room("The black room", "This room has black walls");
        Room r0_2 = new Room("The bedroom room", "This room has a large bed");
        Room r1_2 = new Room("The work room", "This room has a desk");
        Room r3_2 = new Room("The movie room", "This room has a projector");
        Room r1_3 = new Room("The white room", "This room has white walls");
        //North connections
        r2_0.connectNorth(r2_1);
        r0_1.connectNorth(r0_2);
        r1_1.connectNorth(r1_2);
        r3_1.connectNorth(r3_2);
        r1_2.connectNorth(r1_3);
        //East connections
        r0_1.connectEast(r1_1);
        r1_1.connectEast(r2_1);
        r2_1.connectEast(r3_1);
        r3_1.connectEast(r4_1);
        r0_2.connectEast(r1_2);

        return new Mace(r2_0);
    }
}
