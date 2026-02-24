package dk.ek.evu.vpf26.txtadv.user.demo;

import dk.ek.evu.vpf26.txtadv.user.TextUser;
import dk.ek.evu.vpf26.txtadv.user.impl.TerminalUser;

public class TextUserDemo
{
    public static void main()
    {
        TextUser user = new TerminalUser();
        while(true)
        {
            user.put("Enter command :>");
            String command = user.get();
            if("exit".equalsIgnoreCase(command))
            {
                break;
            }
            user.put("You entered the command: ");
            user.put(command);
            user.put("\n");
        }
        user.put("\nGoodbye!");
    }
}
