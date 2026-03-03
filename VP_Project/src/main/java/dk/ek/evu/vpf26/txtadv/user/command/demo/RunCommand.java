package dk.ek.evu.vpf26.txtadv.user.command.demo;

import dk.ek.evu.vpf26.txtadv.user.command.Command;

import java.util.List;

public class RunCommand implements Command
{
    @Override
    public String keyword()
    {
        return "run";
    }

    @Override
    public void execute(List<String> commandWords)
    {
        IO.println("Run command executed with: " +  commandWords);
    }
}
