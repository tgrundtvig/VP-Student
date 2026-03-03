package dk.ek.evu.vpf26.txtadv.user.command;

import java.util.List;

public interface Command
{
    String keyword();
    void execute(List<String> commandWords);
}
