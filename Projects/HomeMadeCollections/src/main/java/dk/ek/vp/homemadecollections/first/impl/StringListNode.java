package dk.ek.vp.homemadecollections.first.impl;

public class StringListNode
{
    private final String value;
    private StringListNode next;
    private StringListNode prev;

    public StringListNode(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public StringListNode getNext()
    {
        return next;
    }

    public StringListNode getPrev()
    {
        return prev;
    }

    public void setNext(StringListNode next)
    {
        this.next = next;
    }

    public void setPrev(StringListNode prev)
    {
        this.prev = prev;
    }
}
