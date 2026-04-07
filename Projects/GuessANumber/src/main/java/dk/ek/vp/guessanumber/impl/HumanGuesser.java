package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Feedback;
import dk.ek.vp.guessanumber.Guesser;
import dk.ek.vp.user.TextAppUser;

public class HumanGuesser implements Guesser
{
    private final TextAppUser user;

    public HumanGuesser(TextAppUser user)
    {
        this.user = user;
    }

    @Override
    public void rangeIs(int min, int max)
    {
        user.println("You have to guess the number your opponent is thinking of between " + min + " and " + max + ".");
        user.readLine("Press enter to continue...");
    }

    @Override
    public int makeGuess()
    {
        return user.readInt("Enter your guess: ");
    }

    @Override
    public void giveFeedback(Feedback fb)
    {
        user.print("Your guess was: ");
        switch(fb)
        {
            case TOO_LOW -> user.println("Too low!");
            case TOO_HIGH -> user.println("Too high!");
            case CORRECT -> user.println("Correct!");
        }
        user.readLine("Press enter to continue...");
    }

    @Override
    public void gameOver(int guessCount)
    {
        user.println("You guessed the number in " + guessCount + " guesses.");
    }
}
