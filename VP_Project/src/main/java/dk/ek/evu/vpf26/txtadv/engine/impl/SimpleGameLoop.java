package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.Actor;
import dk.ek.evu.vpf26.txtadv.engine.Game;
import dk.ek.evu.vpf26.txtadv.engine.GameLoop;

import java.util.List;

public class SimpleGameLoop implements GameLoop
{
    @Override
    public void run(Game game)
    {
        int turn = 0;
        game.initialize();
        while(!game.isGameOver())
        {
            ++turn;
            game.onTurnStart(turn);
            List<Actor> actors = game.getTurnSortedActiveActors();
            for(Actor actor : actors)
            {
                actor.takeTurn();
            }
            game.onTurnEnd(turn);
        }
    }
}
