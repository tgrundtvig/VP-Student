package dk.ek.vp.guessanumber;

public interface Thinker
{
    void thinkOfANumber(int min, int max);

    Feedback getFeedback(int guess);

    void gameOver(int guessCount);
}
