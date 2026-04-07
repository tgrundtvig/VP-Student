package dk.ek.vp.user;

import dk.ek.vp.user.impl.TerminalUser;
import dk.ek.vp.user.impl.TextAppUserImpl;

public class UserTest
{
    static void main()
    {
        TextUser tuser = new TerminalUser();
        TextAppUser user = new TextAppUserImpl(tuser);

        String[] choices ={"Too low", "Too high", "Correct"};
        int choice = user.choose(choices, "Give feedback -> ");
        System.out.println("You choose: " + choices[choice]);
    }
}
