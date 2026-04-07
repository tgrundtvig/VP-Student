package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Feedback;
import dk.ek.vp.guessanumber.Thinker;
import dk.ek.vp.user.TextAppUser;

public class HumanThinker implements Thinker
{
    private final TextAppUser user;

    public HumanThinker(TextAppUser user)
    {
        this.user = user;
    }

    @Override
    public void thinkOfANumber(int min, int max)
    {
        user.println("Think of an integer between " + min + " and " + max + " (both inclusive)");
        user.readLine("Press enter when ready: ");
    }

    @Override
    public Feedback getFeedback(int guess)
    {
        String[] strFeedBack = {"Too low", "Correct", "Too high"};
        user.println("Your opponent has guessed on: " + guess);
        user.println("Give your feedback:");
        int intFeedback = user.choose(strFeedBack, "Choose feedback > ");
        return Feedback.values()[intFeedback];
    }

    @Override
    public void gameOver(int guessCount)
    {
        user.println("Game over! Your opponent guessed your number in " + guessCount + " guesses!");
    }
}
