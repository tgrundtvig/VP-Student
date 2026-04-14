package dk.ek.vp.homemadecollections.first;

import dk.ek.vp.homemadecollections.first.impl.StringLinkedList;

public class Demo
{
    static void main()
    {
        StringList list = new StringLinkedList();
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
        System.out.println("--get(2)->"+ list.get(2));
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
