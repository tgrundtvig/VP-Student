package dk.viprogram.week05;

/**
 * A node for use in linked data structures.
 *
 * This class is provided for you to use in LinkedSimpleList.
 * Each node holds an element and a reference to the next node.
 *
 * @param <T> the type of element stored in this node
 */
public class Node<T> {

    private T element;
    private Node<T> next;

    /**
     * Creates a new node with the given element and no next node.
     *
     * @param element the element to store
     */
    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Creates a new node with the given element and next node.
     *
     * @param element the element to store
     * @param next the next node in the chain
     */
    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Gets the element stored in this node.
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Gets the next node in the chain.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the chain.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
