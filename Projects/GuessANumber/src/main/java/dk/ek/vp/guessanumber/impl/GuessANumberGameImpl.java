package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Feedback;
import dk.ek.vp.guessanumber.GuessANumberGame;
import dk.ek.vp.guessanumber.Guesser;
import dk.ek.vp.guessanumber.Thinker;

public class GuessANumberGameImpl implements GuessANumberGame
{
    @Override
    public void playGame(Thinker thinker, Guesser guesser, int min, int max)
    {
        thinker.thinkOfANumber(min, max);
        guesser.rangeIs(min, max);
        Feedback fb;
        int guessCount = 0;
        do
        {
            int guess = guesser.makeGuess();
            System.out.println("Guess: " + guess);
            fb = thinker.getFeedback(guess);
            guesser.giveFeedback(fb);
            ++guessCount;
        } while(fb != Feedback.CORRECT);
        thinker.gameOver(guessCount);
        guesser.gameOver(guessCount);
        System.out.println("Game over: " + guessCount + " guesses was used!");
    }
}
