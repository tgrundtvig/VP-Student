package dk.ek.evu.vpf26.txtadv.engine;

import java.util.List;

public interface Game
{
    void initialize();

    void onTurnStart(int turn);

    boolean isGameOver();

    List<Actor> getTurnSortedActiveActors();

    void onTurnEnd(int turn);

    List<Player> getPlayers();

    List<NPC> getNPCs();
}
