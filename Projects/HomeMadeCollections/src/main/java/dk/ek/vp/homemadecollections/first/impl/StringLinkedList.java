package dk.ek.vp.homemadecollections.first.impl;

import dk.ek.vp.homemadecollections.first.StringList;

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
        StringListNode newNode = new StringListNode(str);
        if(first == null)
        {
            first = newNode;
            last = newNode;
        }
        else
        {
            first.setPrev(newNode);
            newNode.setNext(first);
            first = newNode;
        }
        ++size;
        ++curVersion;
    }

    @Override
    public String removeLast()
    {
        if(last == null)
        {
            throw new RuntimeException("List is empty");
        }
        StringListNode temp = last;
        if(last.getPrev() == null)
        {
            first = null;
            last = null;
        }
        else
        {
            last = last.getPrev();
            last.setNext(null);
        }
        ++curVersion;
        --size;
        return temp.getValue();
    }

    @Override
    public String removeFirst()
    {
        if(first == null)
        {
            throw new RuntimeException("List is empty");
        }
        StringListNode temp = first;
        if(first.getNext() == null)
        {
            first = null;
            last = null;
        }
        else
        {
            first = first.getNext();
            first.setPrev(null);
        }
        ++curVersion;
        --size;
        return temp.getValue();
    }

    @Override
    public String get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        StringListNode cur = first;
        while(index > 0)
        {
            cur = cur.getNext();
            --index;
        }
        return cur.getValue();
    }

    @Override
    public Iterator<String> iterator()
    {
        return new  StringListIterator();
    }

    private class StringListIterator implements Iterator<String>
    {
        StringListNode next;

        public StringListIterator()
        {
            this.next = first;
        }

        @Override
        public boolean hasNext()
        {
            return next != null;
        }

        @Override
        public String next()
        {
            String res = next.getValue();
            next = next.getNext();
            return res;
        }
    }
}
