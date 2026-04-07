package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Feedback;
import dk.ek.vp.guessanumber.Guesser;

public class ComputerGuesser implements Guesser
{
    private int min;
    private int max;


    @Override
    public void rangeIs(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public int makeGuess()
    {
        return (min + max) / 2;
    }

    @Override
    public void giveFeedback(Feedback fb)
    {
        if(fb == Feedback.TOO_LOW)
        {
            min = ((min + max) / 2) + 1;
        }
        else if(fb == Feedback.TOO_HIGH)
        {
            max = ((max + min) / 2) - 1;
        }
    }

    @Override
    public void gameOver(int guessCount)
    {

    }
}
