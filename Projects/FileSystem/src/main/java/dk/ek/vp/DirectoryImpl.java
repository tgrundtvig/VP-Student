package dk.ek.vp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DirectoryImpl implements Directory
{
    private String name;
    private final List<Node> children;

    public DirectoryImpl(String name)
    {
        this.name = name;
        children = new ArrayList<Node>();
    }

    @Override
    public String name()
    {
        return name;
    }

    @Override
    public Iterator<Node> iterator()
    {
        return children.iterator();
    }

    @Override
    public void addChild(Node child)
    {
        children.add(child);
    }
}
