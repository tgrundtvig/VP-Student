package dk.ek.vp.guessanumber;

public interface Guesser
{
    void rangeIs(int min, int max);

    int makeGuess();

    void giveFeedback(Feedback fb);

    void gameOver(int guessCount);
}
