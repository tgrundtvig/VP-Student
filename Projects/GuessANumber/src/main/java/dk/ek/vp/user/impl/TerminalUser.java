package dk.ek.vp.user.impl;

import dk.ek.vp.user.TextUser;

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
