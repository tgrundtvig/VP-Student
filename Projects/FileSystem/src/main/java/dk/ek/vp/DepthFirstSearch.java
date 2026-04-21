package dk.ek.vp;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch implements Search
{
    @Override
    public List<Node> search(Node root, String name)
    {
        List<Node> result = new ArrayList<Node>();
        search(root, name, result);
        return result;
    }

    private void search(Node current, String name, List<Node> result)
    {
        if(name.equals(current.name()))
        {
            result.add(current);
        }
        if (current instanceof Directory d)
        {
            for (Node child : d)
            {
                search(child, name, result);
            }
        }
    }
}
