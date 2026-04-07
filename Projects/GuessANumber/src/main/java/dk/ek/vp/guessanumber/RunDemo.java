package dk.ek.vp.guessanumber;

import dk.ek.vp.guessanumber.impl.*;

public class RunDemo
{
    static void main()
    {
        PlayerFactory pf = new PlayerFactoryImpl();

        Thinker thinker = pf.createComputerThinker();
        Guesser guesser = pf.createComputerGuesser();

        GuessANumberGame game = new GuessANumberGameImpl();
        game.playGame(thinker, guesser, 1, 100);
    }
}
