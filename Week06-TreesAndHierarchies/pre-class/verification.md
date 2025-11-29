# Week 6 Pre-Class Verification

Use this checklist to verify you're ready for class.

## Reading Comprehension

### Conceptual Understanding
- [ ] I can explain why a tree is an abstraction, not just nodes with pointers
- [ ] I understand the difference between root, parent, child, and leaf nodes
- [ ] I can describe the Composite pattern in my own words
- [ ] I understand why recursive thinking is natural for tree operations

### Tree Terminology
- [ ] I can identify the root of a tree
- [ ] I can explain what a leaf node is
- [ ] I understand what "depth" means in a tree
- [ ] I can explain what a subtree is

## Exercise Completion

### Pre-Class Exercises
Run `mvn test` in the `pre-class/exercises` directory:

- [ ] Exercise 1: TreeNode interface understanding
- [ ] Exercise 2: SimpleTreeNode basic operations pass
- [ ] Exercise 3: Recursive operations (countAll, find, traverse) pass

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
menu.addItem("New Game");
menu.addItem("Load Game");
MenuItem options = menu.addSubmenu("Options");
options.addItem("Sound");
options.addItem("Graphics");
menu.addItem("Exit");

menu.display();
```

Can you:
- [ ] Identify what interface methods are needed?
- [ ] Recognize this as a tree structure?
- [ ] Explain why the Composite pattern applies here?

### Recursive Thinking
Given this tree:
```
       A
      /|\
     B C D
    /|   |
   E F   G
```

Can you:
- [ ] Trace through a pre-order traversal? (A, B, E, F, C, D, G)
- [ ] Calculate `countAll()` for node A? (7)
- [ ] Calculate `countAll()` for node B? (3)
- [ ] Identify all leaf nodes? (E, F, C, G)

## Reflection Questions

Before class, think about:

1. **In file systems**, how does the tree structure help organize files?

2. **In a company**, how does the org chart tree help represent reporting relationships?

3. **In a menu system**, why is a tree better than a flat list?

4. **In your own projects**, where have you used or seen hierarchical data?

## Quick Self-Test

Answer these without looking at notes:

1. What is the root of a tree?
   - [ ] A node with no children
   - [ ] A node with no parent
   - [ ] Any node in the tree

2. What is a leaf node?
   - [ ] A node with no parent
   - [ ] A node with no children
   - [ ] A node with exactly one child

3. The Composite pattern allows you to:
   - [ ] Only work with leaf nodes
   - [ ] Treat individual objects and compositions uniformly
   - [ ] Avoid using interfaces

4. In recursive tree operations, the base case typically handles:
   - [ ] The root node
   - [ ] Leaf nodes or empty subtrees
   - [ ] Only the first child

5. Complete this recursive method:
```java
public int countAll() {
    int count = ____;  // What goes here?
    for (TreeNode<T> child : getChildren()) {
        count += child.countAll();
    }
    return count;
}
```

## Answers (Check After Attempting)

<details>
<summary>Click to reveal answers</summary>

1. A node with no parent (the topmost node)
2. A node with no children
3. Treat individual objects and compositions uniformly
4. Leaf nodes or empty subtrees
5. `int count = 1;` (count the current node first)

</details>

## Tree Visualization Practice

Draw the tree represented by this code:
```java
TreeNode<String> root = new SimpleTreeNode<>("Animals");
TreeNode<String> mammals = root.addChild("Mammals");
TreeNode<String> birds = root.addChild("Birds");
mammals.addChild("Dog");
mammals.addChild("Cat");
mammals.addChild("Elephant");
birds.addChild("Eagle");
birds.addChild("Penguin");
```

<details>
<summary>Click to reveal answer</summary>

```
            Animals
           /       \
      Mammals      Birds
      / | \        /   \
   Dog Cat Elephant Eagle Penguin
```

</details>

## Coming to Class Prepared

If you struggled with any of the above:
- Re-read the relevant section of `reading.md`
- Try the exercises again
- Draw tree structures on paper to visualize them
- Prepare specific questions for class
- Remember: recursive thinking takes practice - it's okay to struggle!
