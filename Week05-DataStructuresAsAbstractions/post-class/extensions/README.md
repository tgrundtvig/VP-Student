# Week 5: Extension Challenges

These optional challenges are for students who finish early or want to deepen their understanding.

## Extension 1: Iterator Pattern

Implement the `Iterable<T>` interface for your collections so they can be used in for-each loops:

```java
public class LinkedSimpleList<T> implements SimpleList<T>, Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            // TODO
        }

        @Override
        public T next() {
            // TODO
        }
    }
}
```

This allows:
```java
SimpleList<String> list = new LinkedSimpleList<>();
for (String item : list) {
    System.out.println(item);
}
```

---

## Extension 2: Insert at Index

Add the ability to insert at a specific position:

```java
public interface SimpleList<T> {
    // ... existing methods ...

    /**
     * Inserts an element at the specified position.
     * Shifts existing elements to the right.
     */
    void insertAt(int index, T element);
}
```

For LinkedSimpleList:
- Find the node before the insertion point
- Create new node pointing to what was there
- Update previous node to point to new node

For ArraySimpleList:
- Shift elements to the right
- Insert at the index

---

## Extension 3: Circular Queue

Implement a `CircularQueue` using a fixed-size array:

```java
public class CircularQueue<T> implements SimpleQueue<T> {
    private Object[] elements;
    private int front;
    private int back;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        // Initialize with fixed capacity
    }

    @Override
    public void enqueue(T element) {
        // Add at back, wrap around if necessary
        // Throw if full
    }

    @Override
    public T dequeue() {
        // Remove from front, wrap around if necessary
    }
}
```

The trick is using modulo arithmetic:
```java
back = (back + 1) % capacity;
front = (front + 1) % capacity;
```

---

## Extension 4: Priority Queue

Implement a priority queue where elements with higher priority come out first:

```java
public interface SimplePriorityQueue<T extends Comparable<T>> {
    void enqueue(T element);          // Inserts maintaining priority
    T dequeue();                       // Removes highest priority
    T peek();                          // Views highest priority
    boolean isEmpty();
    int size();
}
```

You could implement this:
- Using ArraySortedList internally (simple but slow)
- Using a binary heap (more complex but efficient)

---

## Extension 5: Deque (Double-Ended Queue)

A deque allows insertion and removal at both ends:

```java
public interface SimpleDeque<T> {
    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    T peekFirst();
    T peekLast();
    boolean isEmpty();
    int size();
}
```

Implement using a doubly-linked list (nodes have both `next` and `prev` pointers).

---

## Extension 6: Performance Comparison

Write a benchmark that compares:
- ArraySimpleList vs LinkedSimpleList for add() operations
- ArraySimpleList vs LinkedSimpleList for get() operations
- ArraySimpleList vs LinkedSimpleList for remove(0) operations

Create a table showing timings for lists of size 100, 1000, 10000, 100000.

---

## Design Challenge: Bag Collection

Design and implement a `SimpleBag<T>` interface - a collection that:
- Allows duplicates
- Has no ordering
- Supports `add()`, `remove()`, `contains()`, `count(T element)`, `size()`

Think about:
- What's the best implementation strategy?
- Should it extend SimpleList? Why or why not?
- How is it different from List and Set?

---

## Submission

For any extensions you complete:
1. Include source code in a separate `extensions/` package
2. Write tests for your implementations
3. Document your design decisions

These are for extra learning - they won't affect your grade but will deepen your understanding!
