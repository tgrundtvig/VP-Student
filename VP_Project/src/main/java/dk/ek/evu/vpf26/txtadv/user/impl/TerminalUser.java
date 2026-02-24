package dk.ek.evu.vpf26.txtadv.user.impl;

import dk.ek.evu.vpf26.txtadv.user.TextUser;

import java.util.Scanner;

public class TerminalUser implements TextUser
{
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public void put(String text)
    {
        IO.print(text);
    }

    @Override
    public String get()
    {
        return scanner.nextLine();
    }
}
