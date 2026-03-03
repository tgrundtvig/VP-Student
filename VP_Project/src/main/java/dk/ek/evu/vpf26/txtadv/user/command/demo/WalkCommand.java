package dk.ek.evu.vpf26.txtadv.user.command.demo;

import dk.ek.evu.vpf26.txtadv.user.command.Command;

import java.util.List;

public class WalkCommand implements Command
{
    @Override
    public String keyword()
    {
        return "walk";
    }

    @Override
    public void execute(List<String> commandWords)
    {
        IO.println("Walk command executed with: " +  commandWords);
    }
}
