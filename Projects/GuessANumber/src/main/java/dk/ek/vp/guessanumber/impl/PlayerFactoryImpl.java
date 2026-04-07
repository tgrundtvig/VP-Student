package dk.ek.vp.guessanumber.impl;

import dk.ek.vp.guessanumber.Guesser;
import dk.ek.vp.guessanumber.PlayerFactory;
import dk.ek.vp.guessanumber.Thinker;
import dk.ek.vp.user.TextAppUser;
import dk.ek.vp.user.TextUser;
import dk.ek.vp.user.impl.TerminalUser;
import dk.ek.vp.user.impl.TextAppUserImpl;

public class PlayerFactoryImpl implements PlayerFactory
{
    @Override
    public Thinker createHumanThinker()
    {
        TextUser tuser = new TerminalUser();
        TextAppUser user = new TextAppUserImpl(tuser);
        return new HumanThinker(user);
    }

    @Override
    public Thinker createComputerThinker()
    {
        return new ComputerThinker();
    }

    @Override
    public Guesser createHumanGuesser()
    {
        TextUser tuser = new TerminalUser();
        TextAppUser user = new TextAppUserImpl(tuser);
        return new HumanGuesser(user);
    }

    @Override
    public Guesser createComputerGuesser()
    {
        return new ComputerGuesser();
    }
}
