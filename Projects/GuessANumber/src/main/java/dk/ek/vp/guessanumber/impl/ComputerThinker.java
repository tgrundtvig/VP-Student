package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Feedback;
import dk.ek.vp.guessanumber.Thinker;

import java.util.Random;

public class ComputerThinker implements Thinker
{
    private final static Random random = new Random();
    private int secretNumber;
    @Override
    public void thinkOfANumber(int min, int max)
    {
        secretNumber = random.nextInt(max - min) + min;
    }

    @Override
    public Feedback getFeedback(int guess)
    {
        if(guess <  secretNumber)
        {
            return Feedback.TOO_LOW;
        }
        else if(guess > secretNumber)
        {
            return Feedback.TOO_HIGH;
        }
        return Feedback.CORRECT;
    }

    @Override
    public void gameOver(int guessCount)
    {

    }
}
