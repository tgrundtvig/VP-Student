package dk.ek.vp.homemadecollections.generic.impl;

import dk.ek.vp.homemadecollections.first.StringList;
import dk.ek.vp.homemadecollections.generic.GenericList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class GenericArrayList<E> implements GenericList<E>
{
    private int version;
    private E[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public GenericArrayList()
    {
        array = (E[]) new Object[8];
        size = 0;
        version = 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void addLast(E element)
    {
        ++version;
        ensureCapacity();
        array[size++] = element;
    }

    @Override
    public void addFirst(E element)
    {
        ++version;
        ensureCapacity();
        for(int i = size; i > 0; --i)
        {
            array[i] = array[i-1];
        }
        array[0] = element;
        ++size;
    }

    @Override
    public E removeLast()
    {
        if(size == 0) throw new RuntimeException("List is empty");
        ++version;
        return array[--size];
    }

    @Override
    public E removeFirst()
    {
        if(size == 0) throw new RuntimeException("List is empty");
        ++version;
        E element = array[0];
        --size;
        for(int i = 0; i < size; ++i)
        {
            array[i] = array[i+1];
        }
        return element;
    }

    @Override
    public E get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return array[index];
    }

    @Override
    public Iterator<E> iterator()
    {
        return new GenericArrayIterator();
    }

    private void ensureCapacity()
    {
        if(size == array.length)
        {
            doubleArray();
        }
    }

    @SuppressWarnings("unchecked")
    private void doubleArray()
    {
        E[] newArray = (E[]) new Object[array.length*2];
        if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private class GenericArrayIterator implements Iterator<E>
    {
        private final int curVersion;
        private int curIndex;


        public GenericArrayIterator()
        {
            curVersion = version;
            curIndex = 0;
        }

        @Override
        public boolean hasNext()
        {
            if(curVersion != version)
            {
                throw new ConcurrentModificationException("List has changed!");
            }
            return curIndex < size;
        }

        @Override
        public E next()
        {
            if(curVersion != version)
            {
                throw new ConcurrentModificationException("List has changed!");
            }
            return array[curIndex++];
        }
    }
}
