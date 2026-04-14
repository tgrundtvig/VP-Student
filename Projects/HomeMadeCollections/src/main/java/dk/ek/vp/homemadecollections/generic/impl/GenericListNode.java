package dk.ek.vp.homemadecollections.generic.impl;

public class GenericListNode<E>
{
    private final E value;
    private GenericListNode<E> next;
    private GenericListNode<E> prev;

    public GenericListNode(E value)
    {
        this.value = value;
    }

    public E getValue()
    {
        return value;
    }

    public GenericListNode<E> getNext()
    {
        return next;
    }

    public GenericListNode<E> getPrev()
    {
        return prev;
    }

    public void setNext(GenericListNode<E> next)
    {
        this.next = next;
    }

    public void setPrev(GenericListNode<E> prev)
    {
        this.prev = prev;
    }
}
