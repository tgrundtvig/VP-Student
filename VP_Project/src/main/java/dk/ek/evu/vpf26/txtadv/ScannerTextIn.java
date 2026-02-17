package dk.ek.evu.vpf26.txtadv;

import java.util.Scanner;

public class ScannerTextIn implements TextIn
{
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question)
    {
        IO.print(question + ">");
        return scanner.nextLine();
    }
}
