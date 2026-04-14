package dk.ek.vp;

public sealed interface Node permits FileNode, Directory
{
    String name();
}
