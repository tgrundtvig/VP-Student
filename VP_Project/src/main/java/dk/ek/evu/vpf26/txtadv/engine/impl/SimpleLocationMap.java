package dk.ek.evu.vpf26.txtadv.engine.impl;

import dk.ek.evu.vpf26.txtadv.engine.Actor;
import dk.ek.evu.vpf26.txtadv.engine.Location;
import dk.ek.evu.vpf26.txtadv.engine.LocationMap;

public class SimpleLocationMap implements LocationMap
{
    @Override
    public void moveActor(Actor actor, Location from, Location to)
    {
        from.removeActor(actor);
        to.addActor(actor);
        actor.setCurrentLocation(to);
    }
}
