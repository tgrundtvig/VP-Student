package dk.ek.evu.vpf26.txtadv.user.command.impl;

import dk.ek.evu.vpf26.txtadv.user.command.Command;
import dk.ek.evu.vpf26.txtadv.user.command.CommandRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceCommandRegistry implements CommandRegistry
{
    Map<String, Command> commandMap;

    public ReferenceCommandRegistry()
    {
        commandMap = new HashMap<>();
    }

    @Override
    public void registerCommand(Command cmd)
    {
        commandMap.put(cmd.keyword().toLowerCase(), cmd);
    }

    @Override
    public boolean hasCommand(List<String> commandWords)
    {
        if(commandWords == null || commandWords.isEmpty())
        {
            return false;
        }
        return commandMap.containsKey(commandWords.getFirst().toLowerCase());
    }

    @Override
    public void executeCommand(List<String> commandWords)
    {
        if(commandWords != null && !commandWords.isEmpty())
        {
            Command command = commandMap.get(commandWords.getFirst().toLowerCase());
            if(command != null)
            {
                command.execute(commandWords);
                return;
            }
        }
        throw new RuntimeException("Command not found: " + commandWords);
    }
}
