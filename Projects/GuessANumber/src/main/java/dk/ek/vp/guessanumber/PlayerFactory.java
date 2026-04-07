package dk.ek.vp.guessanumber;

public interface PlayerFactory
{
    Thinker createHumanThinker();

    Thinker createComputerThinker();

    Guesser createHumanGuesser();

    Guesser createComputerGuesser();
}
