package dk.ek.evu.vpf26.txtadv;

public class Player
{
    private TextIn textIn;
    private Room currentRoom;

    public Player(TextIn textIn, Room start)
    {
        this.textIn = textIn;
        this.currentRoom = start;
    }

    public void moveInMace()
    {
        boolean exit = false;
        while(!exit)
        {
            IO.println(currentRoom.getRoomDescription());
            while(true)
            {
                String move = textIn.ask("Where do you want to go?");
                if("North".equalsIgnoreCase(move))
                {
                    if(currentRoom.getNorth() == null)
                    {
                        IO.println("There is no exit to the north!");
                        continue;
                    }
                    currentRoom = currentRoom.getNorth();
                    break;
                }
                else if("East".equalsIgnoreCase(move))
                {
                    if(currentRoom.getEast() == null)
                    {
                        IO.println("There is no exit to the east!");
                        continue;
                    }
                    currentRoom = currentRoom.getEast();
                    break;
                }
                else if("South".equalsIgnoreCase(move))
                {
                    if(currentRoom.getSouth() == null)
                    {
                        IO.println("There is no exit to the south!");
                        continue;
                    }
                    currentRoom = currentRoom.getSouth();
                    break;
                }
                else if("West".equalsIgnoreCase(move))
                {
                    if(currentRoom.getWest() == null)
                    {
                        IO.println("There is no exit to the west!");
                        continue;
                    }
                    currentRoom = currentRoom.getWest();
                    break;
                }
                else if("Quit".equalsIgnoreCase(move))
                {
                    exit = true;
                    break;
                }
                else
                {
                    IO.println("Unknown command:" + move);
                    continue;
                }
            }
        }
        IO.println("Goodbye!");
    }
}
