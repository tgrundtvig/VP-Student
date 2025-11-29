# Week 5 Pre-Class Reading: Data Structures as Abstractions

## Introduction

In Weeks 3 and 4, you learned to design interfaces for behavior - things like `Combatant`,
`Reward`, and `Quest`. This week, we apply the same thinking to **data structures**.

You've probably used `ArrayList` and maybe `LinkedList`. But have you thought about
why Java has a `List` interface that both implement? That's what we're exploring.

## The Key Insight

A data structure is not just "how data is stored" - it's a **contract** about what
operations are available and how they behave.

When you write:
```java
List<String> names = new ArrayList<>();
```

The `List<String>` part is the **contract**. It promises:
- You can add elements
- You can get elements by index
- You can remove elements
- You can check the size
- ...and more

The `new ArrayList<>()` part is the **implementation**. It decides:
- HOW elements are stored (in an array)
- HOW fast each operation is
- HOW memory is managed

## Why This Matters

### Code That Doesn't Care About Implementation

Consider this method:
```java
public void printAll(List<String> items) {
    for (int i = 0; i < items.size(); i++) {
        System.out.println(items.get(i));
    }
}
```

This works with ANY `List`:
- `ArrayList<String>` - works
- `LinkedList<String>` - works
- `CopyOnWriteArrayList<String>` - works
- Any future `List` implementation - works!

### Swapping Implementations

If you later discover that `LinkedList` is better for your use case:
```java
// Before
List<String> names = new ArrayList<>();

// After - only this line changes!
List<String> names = new LinkedList<>();

// All other code stays exactly the same
```

This is only possible because you programmed to the `List` interface, not to `ArrayList`.

## Designing a Collection Interface

Let's apply wishful programming to design our own simplified list.

### Step 1: Write What You Wish Existed

```java
public void manageInventory() {
    SimpleList<Item> inventory = createInventory();

    inventory.add(new Item("Sword"));
    inventory.add(new Item("Shield"));
    inventory.add(new Item("Potion"));

    Item firstItem = inventory.get(0);
    System.out.println("First item: " + firstItem.getName());

    System.out.println("Inventory size: " + inventory.size());

    inventory.remove(1);  // Remove shield

    boolean hasPotion = inventory.contains(new Item("Potion"));
}
```

### Step 2: Extract the Interface

From this usage, we can define:
```java
public interface SimpleList<T> {
    void add(T element);
    T get(int index);
    int size();
    void remove(int index);
    boolean contains(T element);
    boolean isEmpty();
}
```

### Step 3: Implement

Now implementation becomes mechanical:
```java
public class ArraySimpleList<T> implements SimpleList<T> {
    private Object[] elements;
    private int size;

    public ArraySimpleList() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    // ... etc
}
```

## Stack: A Behavioral Contract

A Stack is not about HOW data is stored - it's about WHAT operations are allowed.

### The Stack Contract
A Stack promises **LIFO** (Last In, First Out) behavior:
- `push(element)` - add to the top
- `pop()` - remove and return the top
- `peek()` - look at the top without removing
- `isEmpty()` - check if empty

```java
public interface SimpleStack<T> {
    void push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}
```

### Why Stack is an Interface

You could implement Stack with:
- An array (ArrayStack)
- A linked list (LinkedStack)
- Even using another Stack internally (for decoration)

The user of your Stack doesn't care HOW - they just need LIFO behavior.

```java
SimpleStack<Action> undoStack = new ArrayStack<>();
undoStack.push(moveAction);
undoStack.push(attackAction);

// Undo last action
Action lastAction = undoStack.pop();  // Gets attackAction
lastAction.undo();
```

## Queue: Another Behavioral Contract

A Queue promises **FIFO** (First In, First Out) behavior:
- `enqueue(element)` - add to the back
- `dequeue()` - remove and return the front
- `peek()` - look at the front without removing
- `isEmpty()` - check if empty

```java
public interface SimpleQueue<T> {
    void enqueue(T element);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
}
```

### Real-World Example: Print Queue

```java
SimpleQueue<PrintJob> printQueue = new LinkedQueue<>();
printQueue.enqueue(new PrintJob("Document1.pdf"));
printQueue.enqueue(new PrintJob("Photo.jpg"));
printQueue.enqueue(new PrintJob("Report.docx"));

while (!printQueue.isEmpty()) {
    PrintJob job = printQueue.dequeue();  // Gets Document1.pdf first
    printer.print(job);
}
```

## Choosing Implementations

Different implementations have different trade-offs:

### Array-Based (ArrayList, ArrayStack)
**Pros:**
- Fast random access: `get(i)` is O(1)
- Memory efficient (no extra pointers)
- Good cache locality

**Cons:**
- Slow insertions/deletions in middle: O(n)
- May waste space if array is larger than needed
- Resizing is expensive

### Linked-Based (LinkedList, LinkedStack)
**Pros:**
- Fast insertions/deletions anywhere: O(1) once you have the node
- No wasted space for unused capacity
- Never needs resizing

**Cons:**
- Slow random access: `get(i)` is O(n)
- Extra memory for node pointers
- Poor cache locality

### When to Use Which?

| Use Case | Better Choice | Why |
|----------|---------------|-----|
| Random access by index | Array-based | O(1) vs O(n) |
| Frequent insertions/deletions | Linked-based | O(1) vs O(n) |
| Memory-constrained | Depends | Array wastes space, linked uses pointers |
| Stack operations | Either | Both are O(1) for push/pop |
| Queue operations | Linked-based | Array needs shifting or circular buffer |

## Generics Refresher

Our interfaces use `<T>` to be generic. This means:
- `SimpleList<String>` - a list of strings
- `SimpleList<Quest>` - a list of quests
- `SimpleList<Integer>` - a list of integers (boxed)

```java
public interface SimpleList<T> {
    void add(T element);  // T is replaced by actual type
    T get(int index);     // Returns the actual type
}
```

The `T` is a placeholder that gets replaced when you use the interface:
```java
SimpleList<Quest> quests;  // T becomes Quest
quests.add(new Quest("Slay Dragon"));  // add(Quest element)
Quest q = quests.get(0);               // Quest get(int index)
```

## Common Mistakes

### 1. Programming to Implementation
**Wrong:**
```java
ArrayList<String> names = new ArrayList<>();
```

**Right:**
```java
List<String> names = new ArrayList<>();
```

Use the most general type that provides what you need.

### 2. Putting Implementation Details in Interface
**Wrong:**
```java
interface List<T> {
    Object[] getBackingArray();  // NO! Implementation detail
}
```

**Right:**
```java
interface List<T> {
    T get(int index);  // What, not how
}
```

### 3. Forgetting That Interfaces Can't Have Instance Fields
**Wrong (doesn't compile):**
```java
interface SimpleList<T> {
    private int size;  // NO! Interfaces can't have instance fields
}
```

**Right:**
```java
class ArraySimpleList<T> implements SimpleList<T> {
    private int size;  // Implementation has fields
}
```

## Exercise Preview

In the pre-class exercises, you'll:
1. Analyze existing Java collections as interfaces
2. Design and implement a `SimpleList` interface
3. Create a `SimpleStack` interface using wishful programming

## Key Takeaways

1. **Data structures are contracts** - List, Stack, Queue define behavior, not storage
2. **Program to interfaces** - Write `List<T>` not `ArrayList<T>`
3. **Multiple implementations** - Same interface, different trade-offs
4. **Wishful programming applies** - Design the interface from how you want to use it
5. **Swap freely** - Change implementation without changing client code

## Before Class

Make sure you can:
- Explain why `List` is an interface
- Design a simple collection interface from usage code
- Implement an interface with array-backed storage
- Describe trade-offs between array and linked implementations
