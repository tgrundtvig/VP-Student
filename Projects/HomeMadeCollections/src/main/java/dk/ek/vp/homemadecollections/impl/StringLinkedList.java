package dk.ek.vp.homemadecollections.impl;

import dk.ek.vp.homemadecollections.StringList;

import java.util.Iterator;

public class StringLinkedList implements StringList
{
    private int curVersion;
    private StringListNode first;
    private StringListNode last;
    private int size;

    public StringLinkedList()
    {
        first = null;
        last = null;
        size = 0;
        curVersion = 0;
    }


    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void addLast(String str)
    {
        StringListNode newNode = new StringListNode(str);
        if(last == null)
        {
            first = newNode;
            last = newNode;
        }
        else
        {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
        ++size;
        ++curVersion;
    }

    @Override
    public void addFirst(String str)
    {
        ++curVersion;
    }

    @Override
    public String removeLast()
    {
        ++curVersion;
        return "";
    }

    @Override
    public String removeFirst()
    {
        return "";
    }

    @Override
    public String get(int index)
    {
        return "";
    }

    @Override
    public Iterator<String> iterator()
    {
        return null;
    }
}
