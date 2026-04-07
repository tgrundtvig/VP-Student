package dk.ek.vp.homemadecollections;

import dk.ek.vp.homemadecollections.impl.StringArrayList;

import java.util.Iterator;

public class Demo
{
    static void main()
    {
        StringList list = new StringArrayList();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");
        list.addLast("E");
        list.addLast("F");

        for(String s : list)
        {
            System.out.println(s);
        }
        System.out.println("---------");
        for(int i = 0; i < list.size(); ++i)
        {
            System.out.println(list.get(i));
        }
        System.out.println("---------");

        String removed =  list.removeFirst();

        System.out.println("Removed: " + removed);

        System.out.println("---------");
        for(String s : list)
        {
            System.out.println(s);
        }
    }
}
