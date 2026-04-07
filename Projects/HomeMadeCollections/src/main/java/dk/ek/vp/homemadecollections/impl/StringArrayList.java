package dk.ek.vp.homemadecollections.impl;

import dk.ek.vp.homemadecollections.StringList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class StringArrayList implements StringList
{
    private int version;
    private String[] array;
    private int size;

    public StringArrayList()
    {
        array = new String[8];
        size = 0;
        version = 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void addLast(String str)
    {
        ++version;
        ensureCapacity();
        array[size++] = str;
    }

    @Override
    public void addFirst(String str)
    {
        ++version;
        ensureCapacity();
        for(int i = size; i > 0; --i)
        {
            array[i] = array[i-1];
        }
        array[0] = str;
        ++size;
    }

    @Override
    public String removeLast()
    {
        if(size == 0) throw new RuntimeException("List is empty");
        ++version;
        return array[--size];
    }

    @Override
    public String removeFirst()
    {
        if(size == 0) throw new RuntimeException("List is empty");
        ++version;
        String str = array[0];
        --size;
        for(int i = 0; i < size; ++i)
        {
            array[i] = array[i+1];
        }
        return str;
    }

    @Override
    public String get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return array[index];
    }

    @Override
    public Iterator<String> iterator()
    {
        return new StringArrayIterator();
    }

    private void ensureCapacity()
    {
        if(size == array.length)
        {
            doubleArray();
        }
    }

    private void doubleArray()
    {
        String[] newArray = new String[array.length*2];
        for(int i = 0; i < size; ++i)
        {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private class StringArrayIterator implements Iterator<String>
    {
        private final int curVersion;
        private int curIndex;


        public StringArrayIterator()
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
        public String next()
        {
            if(curVersion != version)
            {
                throw new ConcurrentModificationException("List has changed!");
            }
            return array[curIndex++];
        }
    }
}
