package dk.ek.evu.vpf26.txtadv.user.command.demo;

import dk.ek.evu.vpf26.txtadv.user.command.Command;

import java.util.List;

public class JumpCommand implements Command
{
    @Override
    public String keyword()
    {
        return "jump";
    }

    @Override
    public void execute(List<String> commandWords)
    {
        IO.println("Jump command executed with: " +  commandWords);
    }
}
