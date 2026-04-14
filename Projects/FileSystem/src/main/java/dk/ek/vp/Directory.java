package dk.ek.vp;

public non-sealed interface Directory extends Node, Iterable<Node>
{
    void addChild(Node child);
}
