package dk.ek.vp.homemadecollections.generic;

public interface GenericList<E> extends Iterable<E>
{
    int size();
    void addLast(E element);
    void addFirst(E element);
    E removeLast();
    E removeFirst();
    E get(int index);
}
