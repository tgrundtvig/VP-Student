package dk.ek.vp.user.impl;

import dk.ek.vp.user.TextAppUser;
import dk.ek.vp.user.TextUser;

import java.util.List;

public class TextAppUserImpl implements TextAppUser
{
    private final TextUser user;

    public TextAppUserImpl(TextUser user)
    {
        this.user = user;
    }

    @Override
    public void print(String text)
    {
        user.put(text);
    }

    @Override
    public void println(String text)
    {
        user.put(text);
        user.put(System.lineSeparator());
    }

    @Override
    public void println()
    {
        user.put(System.lineSeparator());
    }

    @Override
    public String readLine(String prompt)
    {
        user.put(prompt);
        return user.get();
    }

    @Override
    public int readInt(String prompt)
    {
        while(true)
        {
            user.put(prompt);
            String input = user.get().trim();
            try
            {
                int i = Integer.parseInt(input);
                return i;
            }
            catch(NumberFormatException e)
            {
                println("You must enter an integer! Please try again.");
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max)
    {
        while(true)
        {
            int i = readInt(prompt);
            if(i >= min && i <= max)
            {
                return i;
            }
            println("You must enter an integer in the range from " + min + " to " + max + ". Please try again.");
        }
    }

    @Override
    public float readFloat(String prompt)
    {
        while(true)
        {
            user.put(prompt);
            String input = user.get().trim();
            try
            {
                float f = Float.parseFloat(input);
                return f;
            }
            catch(NumberFormatException e)
            {
                println("You must enter a float! Please try again.");
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max)
    {
        while(true)
        {
            float f = readFloat(prompt);
            if(f >= min && f <= max)
            {
                return f;
            }
            println("You must enter a float in the range from " + min + " to " + max + ". Please try again.");
        }

    }

    @Override
    public int choose(String[] choices, String prompt)
    {
        for(int i = 0; i < choices.length; i++)
        {
            print(Integer.toString(i + 1));
            print(" -> ");
            println(choices[i]);
        }
        println();
        int choice = readInt(prompt, 1, choices.length);
        return choice - 1;
    }

    @Override
    public List<String> getCommand(String prompt)
    {
        String line = readLine(prompt);
        String[] array = line.split("\\s+");
        return List.of(array);
    }
}
