package dk.ek.evu.vpf26.txtadv.user.command.demo;

import dk.ek.evu.vpf26.txtadv.user.TextAppUser;
import dk.ek.evu.vpf26.txtadv.user.TextUser;
import dk.ek.evu.vpf26.txtadv.user.command.CommandRegistry;
import dk.ek.evu.vpf26.txtadv.user.command.impl.ReferenceCommandRegistry;
import dk.ek.evu.vpf26.txtadv.user.impl.TerminalUser;
import dk.ek.evu.vpf26.txtadv.user.impl.TextAppUserImpl;

import java.util.List;

public class CommandDemo
{
    static void main()
    {
        CommandRegistry cr = new ReferenceCommandRegistry(); // Should be set to an implementation of CommandRegistry.
        cr.registerCommand(new WalkCommand());
        cr.registerCommand(new RunCommand());
        cr.registerCommand(new JumpCommand());

        TextUser textUser = new TerminalUser();
        TextAppUser appUser = new TextAppUserImpl(textUser);
        while(true)
        {
            List<String> commandWords = appUser.getCommand("Enter command :>");
            if(commandWords != null && !commandWords.isEmpty() && commandWords.getFirst().equalsIgnoreCase("exit"))
            {
                break;
            }

            if(cr.hasCommand(commandWords))
            {
                cr.executeCommand(commandWords);
            }
            else
            {
                IO.println("Unknow command: " + commandWords);
            }
        }

        IO.println("Goodbye!");
    }
}
