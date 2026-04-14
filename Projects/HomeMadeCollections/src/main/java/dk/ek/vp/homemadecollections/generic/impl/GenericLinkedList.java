package dk.ek.vp.homemadecollections.generic.impl;

import dk.ek.vp.homemadecollections.first.StringList;
import dk.ek.vp.homemadecollections.generic.GenericList;

import java.util.Iterator;

public class GenericLinkedList<E> implements GenericList<E>
{
    private int curVersion;
    private GenericListNode<E> first;
    private GenericListNode<E> last;
    private int size;

    public GenericLinkedList()
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
    public void addLast(E element)
    {
        GenericListNode<E> newNode = new GenericListNode<>(element);
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
    public void addFirst(E element)
    {
        GenericListNode<E> newNode = new GenericListNode<>(element);
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
    public E removeLast()
    {
        if(last == null)
        {
            throw new RuntimeException("List is empty");
        }
        GenericListNode<E> temp = last;
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
    public E removeFirst()
    {
        if(first == null)
        {
            throw new RuntimeException("List is empty");
        }
        GenericListNode<E> temp = first;
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
    public E get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        GenericListNode<E> cur = first;
        while(index > 0)
        {
            cur = cur.getNext();
            --index;
        }
        return cur.getValue();
    }

    @Override
    public Iterator<E> iterator()
    {
        return new  GenericListIterator();
    }

    private class GenericListIterator implements Iterator<E>
    {
        GenericListNode<E> next;

        public GenericListIterator()
        {
            this.next = first;
        }

        @Override
        public boolean hasNext()
        {
            return next != null;
        }

        @Override
        public E next()
        {
            E res = next.getValue();
            next = next.getNext();
            return res;
        }
    }
}
