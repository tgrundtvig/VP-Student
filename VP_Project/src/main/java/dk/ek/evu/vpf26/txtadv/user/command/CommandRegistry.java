package dk.ek.evu.vpf26.txtadv.user.command;

import java.util.List;

public interface CommandRegistry
{
    void registerCommand(Command cmd);
    boolean hasCommand(List<String> commandWords);
    void executeCommand(List<String> commandWords); // Throws if the command does not exist in the registry
}
