package dk.ek.vp;

import java.io.File;

public class NodePrinter
{
    String print(Node node)
    {
        StringBuilder sb = new StringBuilder();
        print(sb, 0, node);
        return sb.toString();
    }

    private void print(StringBuilder sb, int indent, Node node)
    {
        sb.append("  ".repeat(indent));
        switch(node)
        {
            case FileNode fn:
            {
                sb.append("File: ");
                sb.append(fn.name());
                sb.append(" -> \"");
                sb.append(fn.content());
                sb.append('\"');
                sb.append(System.lineSeparator());
                break;
            }
            case Directory d:
            {
                sb.append("Dir: ");
                sb.append(d.name());
                sb.append(System.lineSeparator());
                for(Node n : d)
                {
                    print(sb, indent + 1, n);
                }
                break;
            }
        }
    }
}
