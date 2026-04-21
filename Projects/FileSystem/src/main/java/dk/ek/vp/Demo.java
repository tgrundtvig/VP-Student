package dk.ek.vp;

import java.util.List;

public class Demo
{
    static void main()
    {
        Directory root = new DirectoryImpl("root");
        FileNode file1 = new FileNode("file1", "Hello");
        FileNode file2 = new FileNode("file2", "World");
        FileNode file3 = new FileNode("file3", "Hej");
        FileNode file4 = new FileNode("file4", "med");
        FileNode file5 = new FileNode("file5", "dig");
        Directory dir1 = new DirectoryImpl("dir1");
        Directory dir2 = new DirectoryImpl("dir2");
        root.addChild(file1);
        root.addChild(file2);
        root.addChild(dir1);
        dir1.addChild(file3);
        root.addChild(file4);
        root.addChild(dir2);
        dir2.addChild(file5);

        NodePrinter printer = new NodePrinter();
        String s = printer.print(root);
        System.out.println(s);
        Search search = new DepthFirstSearch();
        List<Node> res = search.search(root, "file3");
        for(Node n : res)
        {
            System.out.println(n.name());
        }
    }
}
