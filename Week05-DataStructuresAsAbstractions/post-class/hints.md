# Week 5: Post-Class Exercise Hints

Use these hints if you get stuck. Try to solve the exercises on your own first!

---

## Exercise 1: LinkedSimpleList

### Hint 1: Understanding the Structure
A linked list is a chain of nodes, where each node points to the next:
```
head -> [Element1|next] -> [Element2|next] -> [Element3|null]
```

The last node has `next = null`.

### Hint 2: Adding Elements
To add at the end, you need to traverse to the last node:
```java
public void add(T element) {
    Node<T> newNode = new Node<>(element);
    if (head == null) {
        head = newNode;
    } else {
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }
    size++;
}
```

### Hint 3: Getting by Index
Traverse from head, counting steps:
```java
private Node<T> getNode(int index) {
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
        current = current.getNext();
    }
    return current;
}
```

### Hint 4: Removing Elements
Removing the head is special. For others, find the node BEFORE the one to remove:
```java
public T remove(int index) {
    checkIndex(index);
    T element;
    if (index == 0) {
        element = head.getElement();
        head = head.getNext();
    } else {
        Node<T> before = getNode(index - 1);
        element = before.getNext().getElement();
        before.setNext(before.getNext().getNext());
    }
    size--;
    return element;
}
```

---

## Exercise 2: LinkedQueue

### Hint 1: Maintaining Front and Back
A queue needs quick access to both ends:
```
front -> [A|next] -> [B|next] -> [C|null] <- back
```

### Hint 2: Enqueue (Add to Back)
```java
public void enqueue(T element) {
    Node<T> newNode = new Node<>(element);
    if (isEmpty()) {
        front = back = newNode;
    } else {
        back.setNext(newNode);
        back = newNode;
    }
    size++;
}
```

### Hint 3: Dequeue (Remove from Front)
```java
public T dequeue() {
    if (isEmpty()) {
        throw new NoSuchElementException("Queue is empty");
    }
    T element = front.getElement();
    front = front.getNext();
    if (front == null) {
        back = null;  // Queue is now empty
    }
    size--;
    return element;
}
```

### Hint 4: Common Bug
Don't forget to update `back` to `null` when the queue becomes empty!

---

## Exercise 3: ArraySortedList

### Hint 1: Finding the Insertion Point
You need to find where the element belongs. Linear scan version:
```java
private int findInsertionPoint(T element) {
    for (int i = 0; i < size; i++) {
        @SuppressWarnings("unchecked")
        T current = (T) elements[i];
        if (element.compareTo(current) <= 0) {
            return i;
        }
    }
    return size;  // Goes at the end
}
```

### Hint 2: Binary Search Version (More Efficient)
```java
private int findInsertionPoint(T element) {
    int low = 0;
    int high = size;
    while (low < high) {
        int mid = (low + high) / 2;
        @SuppressWarnings("unchecked")
        T midElement = (T) elements[mid];
        if (element.compareTo(midElement) <= 0) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    return low;
}
```

### Hint 3: Adding with Shift
After finding the position, shift elements to make room:
```java
public void add(T element) {
    ensureCapacity();
    int insertionPoint = findInsertionPoint(element);

    // Shift elements right
    for (int i = size; i > insertionPoint; i--) {
        elements[i] = elements[i - 1];
    }

    elements[insertionPoint] = element;
    size++;
}
```

### Hint 4: indexOf with Binary Search
Since the list is sorted, use binary search:
```java
public int indexOf(T element) {
    int low = 0;
    int high = size - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        @SuppressWarnings("unchecked")
        T midElement = (T) elements[mid];
        int cmp = element.compareTo(midElement);
        if (cmp < 0) {
            high = mid - 1;
        } else if (cmp > 0) {
            low = mid + 1;
        } else {
            return mid;  // Found!
        }
    }
    return -1;  // Not found
}
```

---

## General Tips

1. **Draw pictures**: Linked structures are much easier to understand with diagrams
2. **Handle edge cases first**: Empty list, single element, head removal
3. **Test incrementally**: Get one test passing before moving to the next
4. **Use the debugger**: Step through your code to see what's happening
5. **Check null references**: Many bugs come from forgetting to handle null

## Common Mistakes

1. **Forgetting to update size**: Every add/remove must update size
2. **Off-by-one errors in loops**: Draw out small examples
3. **Not handling empty list**: Always check if list is empty first
4. **Losing reference to head**: Be careful when removing the first element

Remember: These data structures demonstrate that the SAME interface can have DIFFERENT implementations. The user of your list doesn't care if it's array-backed or linked!
