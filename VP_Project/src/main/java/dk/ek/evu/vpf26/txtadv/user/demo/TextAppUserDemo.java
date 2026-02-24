package dk.ek.evu.vpf26.txtadv.user.demo;

import dk.ek.evu.vpf26.txtadv.user.TextAppUser;
import dk.ek.evu.vpf26.txtadv.user.TextUser;
import dk.ek.evu.vpf26.txtadv.user.impl.TerminalUser;
import dk.ek.evu.vpf26.txtadv.user.impl.TextAppUserImpl;

import java.util.Arrays;

public class TextAppUserDemo
{
    public static void main()
    {
        TextUser textUser = new TerminalUser();
        TextAppUser appUser = new TextAppUserImpl(textUser);

        String name = appUser.readLine("Enter your name: ");
        int age = appUser.readInt("Enter your age: ");
        float height = appUser.readFloat("Enter your height: ", 0.2f, 3.0f);
        int number = appUser.readInt("Enter a number between 1 and 100: ", 1, 100);
        String[] colors = {"Blue", "Red", "Green", "Yellow"};
        int choice = appUser.choose(colors, "What is your favorite color: ");

        appUser.println("Name: " + name);
        appUser.println("Age: " + age);
        appUser.println("Height: " + height);
        appUser.println("Number: " + number);
        appUser.println("Favorite color: " + colors[choice]);
    }
}
