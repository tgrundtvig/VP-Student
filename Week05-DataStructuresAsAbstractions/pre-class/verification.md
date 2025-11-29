# Week 5 Pre-Class Verification

Use this checklist to verify you're ready for class.

## Reading Comprehension

### Conceptual Understanding
- [ ] I can explain why `List` is an interface rather than a class
- [ ] I understand the difference between a data structure's **contract** and its **implementation**
- [ ] I can describe LIFO (Stack) and FIFO (Queue) behavior in my own words
- [ ] I understand why we write `List<String> names` instead of `ArrayList<String> names`

### Trade-offs
- [ ] I can list one advantage of array-based implementations
- [ ] I can list one advantage of linked-based implementations
- [ ] I can describe a scenario where ArrayList is better than LinkedList
- [ ] I can describe a scenario where LinkedList is better than ArrayList

## Exercise Completion

### Pre-Class Exercises
Run `mvn test` in the `pre-class/exercises` directory:

- [ ] Exercise 1: SimpleList interface tests pass
- [ ] Exercise 2: ArraySimpleList tests pass
- [ ] Exercise 3: SimpleStack interface tests pass

### Verification Command
```bash
cd pre-class/exercises
mvn test
```

All tests should pass before attending class.

## Practical Skills

### Interface Design
Given this code:
```java
bag.addItem(sword);
bag.addItem(potion);
Item item = bag.getRandomItem();
boolean hasSword = bag.containsItem(sword);
int total = bag.getTotalWeight();
```

Can you:
- [ ] Identify the interface methods needed?
- [ ] Write the interface declaration?
- [ ] Explain why this should be an interface (not a class)?

### Implementation Understanding
- [ ] I understand how to use `Object[]` to store generic elements
- [ ] I understand why we need `@SuppressWarnings("unchecked")` for casting
- [ ] I can implement a simple `add()` method that grows an array when full

## Reflection Questions

Before class, think about:

1. **In your current/past projects**, where have you used collection interfaces vs concrete classes?

2. **The Java Collections Framework** has `List`, `Set`, `Map`, `Queue` interfaces. Why do you think they designed it this way?

3. **If you had to add a new collection type** (like a priority queue), how would interface-first design help?

## Quick Self-Test

Answer these without looking at notes:

1. What's the time complexity of `get(index)` in:
   - ArrayList: ___
   - LinkedList: ___

2. What's the time complexity of inserting at the beginning in:
   - ArrayList: ___
   - LinkedList: ___

3. Stack uses which principle?
   - [ ] FIFO
   - [ ] LIFO

4. Queue uses which principle?
   - [ ] FIFO
   - [ ] LIFO

5. Complete this interface:
```java
public interface SimpleStack<T> {
    void push(T element);
    T ____();  // remove and return top
    T ____();  // look at top without removing
    boolean isEmpty();
}
```

## Answers (Check After Attempting)

<details>
<summary>Click to reveal answers</summary>

1. ArrayList `get(index)`: O(1), LinkedList `get(index)`: O(n)
2. ArrayList insert at beginning: O(n), LinkedList insert at beginning: O(1)
3. Stack: LIFO
4. Queue: FIFO
5. `pop()` and `peek()`

</details>

## Coming to Class Prepared

If you struggled with any of the above:
- Re-read the relevant section of `reading.md`
- Try the exercises again
- Prepare specific questions for class
- Remember: struggling now means learning during class will be more valuable!
