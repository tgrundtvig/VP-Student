package dk.ek.vp.homemadecollections;

public interface StringList extends Iterable<String>
{
    int size();
    void addLast(String str);
    void addFirst(String str);
    String removeLast();
    String removeFirst();
    String get(int index);
}
