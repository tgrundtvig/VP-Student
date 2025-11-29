# Week 6 Pre-Class Reading: Trees and Hierarchies

## Introduction

In Week 5, you learned that data structures like List, Stack, and Queue are **contracts** -
interfaces that promise certain behavior without specifying implementation. This week, we
apply the same thinking to **hierarchical** data structures: trees.

You encounter trees every day:
- Your computer's file system (folders containing files and folders)
- Website navigation menus (menus with submenus)
- Company organization charts (managers with direct reports)
- HTML documents (elements containing other elements)

All of these share a common pattern: **parent-child relationships** forming a hierarchy.

## The Key Insight

A tree is not just "nodes with pointers to children." A tree is an **abstraction** that
represents hierarchical relationships. Just like `List` is a promise about ordered collections,
`TreeNode` is a promise about parent-child relationships.

When you design with trees, you're capturing the **structure of relationships**, not the
mechanics of how nodes are connected.

## Anatomy of a Tree

```
           Company (root)
          /       \
    Engineering    Sales
    /    |    \      |    \
Frontend Backend DevOps  North  South
```

**Key terminology:**
- **Root**: The topmost node (Company)
- **Parent**: A node with children (Engineering is parent of Frontend)
- **Child**: A node with a parent (Frontend is child of Engineering)
- **Leaf**: A node with no children (Frontend, Backend, DevOps, North, South)
- **Subtree**: A node and all its descendants (Engineering and everything below it)
- **Depth**: Distance from root (root = 0, Engineering = 1, Frontend = 2)

## Designing a Tree Interface

Let's apply wishful programming. How do we **want** to use a tree?

```java
// Create an organization chart
TreeNode<String> company = new SimpleTreeNode<>("Company");

// Add departments
TreeNode<String> engineering = company.addChild("Engineering");
TreeNode<String> sales = company.addChild("Sales");

// Add teams
engineering.addChild("Frontend Team");
engineering.addChild("Backend Team");
engineering.addChild("DevOps Team");

sales.addChild("North Region");
sales.addChild("South Region");

// Navigate the structure
System.out.println(engineering.getParent().getValue());  // "Company"
System.out.println(engineering.getChildren().size());    // 3

// Check node type
if (engineering.isLeaf()) {
    System.out.println("No teams!");
} else {
    System.out.println("Has " + engineering.getChildren().size() + " teams");
}

// Find a specific node
Optional<TreeNode<String>> devops = company.find("DevOps Team");

// Process all nodes
company.traverse(node -> System.out.println(node.getValue()));
```

### Extracting the Interface

From this usage, we can define:

```java
public interface TreeNode<T> {

    /**
     * Returns the value stored in this node.
     */
    T getValue();

    /**
     * Returns this node's parent, or null if this is the root.
     */
    TreeNode<T> getParent();

    /**
     * Returns an unmodifiable list of this node's children.
     */
    List<TreeNode<T>> getChildren();

    /**
     * Returns true if this node has no children.
     */
    boolean isLeaf();

    /**
     * Returns true if this node has no parent (is the root).
     */
    boolean isRoot();

    /**
     * Adds a child with the given value and returns the new child node.
     */
    TreeNode<T> addChild(T value);

    /**
     * Removes a child node. Returns true if the child was found and removed.
     */
    boolean removeChild(TreeNode<T> child);

    /**
     * Searches this node and its descendants for a node with the given value.
     * Returns Optional.empty() if not found.
     */
    Optional<TreeNode<T>> find(T value);

    /**
     * Applies the given action to this node and all its descendants.
     */
    void traverse(Consumer<TreeNode<T>> action);
}
```

## Recursive Thinking

Tree operations naturally involve recursion. The pattern is:
1. Do something with the current node
2. Recursively process each child

### Example: Counting All Nodes

```java
public int countAll() {
    int count = 1;  // Count myself
    for (TreeNode<T> child : getChildren()) {
        count += child.countAll();  // Add counts from all descendants
    }
    return count;
}
```

### Example: Finding a Value

```java
public Optional<TreeNode<T>> find(T value) {
    // Check myself
    if (Objects.equals(getValue(), value)) {
        return Optional.of(this);
    }

    // Check my children (recursively)
    for (TreeNode<T> child : getChildren()) {
        Optional<TreeNode<T>> result = child.find(value);
        if (result.isPresent()) {
            return result;
        }
    }

    // Not found
    return Optional.empty();
}
```

### Example: Traversal

```java
public void traverse(Consumer<TreeNode<T>> action) {
    action.accept(this);  // Process myself
    for (TreeNode<T> child : getChildren()) {
        child.traverse(action);  // Process descendants
    }
}
```

This is called **pre-order traversal** (process node before children).
There's also **post-order** (children before node).

## The Composite Pattern

The Composite pattern emerges naturally when leaves and branches share an interface.

### Real Example: File System

```java
public interface FileSystemEntry {
    String getName();
    long getSize();
    void print(String indent);
}

public class File implements FileSystemEntry {
    private String name;
    private long size;

    @Override
    public long getSize() {
        return size;  // Just my size
    }
}

public class Directory implements FileSystemEntry {
    private String name;
    private List<FileSystemEntry> contents;

    @Override
    public long getSize() {
        // Sum of all contents (recursive!)
        long total = 0;
        for (FileSystemEntry entry : contents) {
            total += entry.getSize();
        }
        return total;
    }
}
```

**The magic:** Client code can call `getSize()` on any `FileSystemEntry` without knowing
whether it's a file or a directory. The recursion is hidden inside the implementation.

```java
FileSystemEntry entry = ...;  // Could be file or directory
System.out.println("Size: " + entry.getSize());  // Just works!
```

## Real-World Tree Examples

### 1. Organization Charts
```
        CEO
       / | \
    CTO CFO CMO
    /|\   |   |
  Dev QA  Acct Marketing
```
Every employee has one manager (parent), managers have direct reports (children).

### 2. Menu Systems
```
        Main Menu
       /    |    \
    File  Edit  View
    /|\     |    /|\
  New Open Save Undo  ...
```
Menus contain items and submenus.

### 3. Game Scene Graphs
```
           World
          /     \
       Player   Enemies
       /  \       |
    Weapon Armor Enemy1 Enemy2
```
Game entities organized hierarchically for rendering and updates.

### 4. HTML DOM
```html
<html>
  <head>
    <title>Page</title>
  </head>
  <body>
    <div>
      <p>Hello</p>
    </div>
  </body>
</html>
```
Every element can contain other elements.

## Binary Trees vs General Trees

**General tree:** A node can have any number of children.
- File systems, menus, organization charts

**Binary tree:** A node has at most two children (left, right).
- Binary search trees, expression trees, decision trees

This week focuses on **general trees**. Binary trees are a specialization.

```java
// General tree node
interface TreeNode<T> {
    List<TreeNode<T>> getChildren();
}

// Binary tree node (specialization)
interface BinaryTreeNode<T> {
    BinaryTreeNode<T> getLeft();
    BinaryTreeNode<T> getRight();
}
```

## Common Mistakes

### 1. Forgetting the Base Case in Recursion
**Wrong:**
```java
public int countAll() {
    int count = 0;
    for (TreeNode<T> child : getChildren()) {
        count += child.countAll();
    }
    return count;  // Never counts the node itself!
}
```

**Right:**
```java
public int countAll() {
    int count = 1;  // Count myself first!
    for (TreeNode<T> child : getChildren()) {
        count += child.countAll();
    }
    return count;
}
```

### 2. Infinite Loops with Parent References
**Wrong:**
```java
public void traverse(Consumer<TreeNode<T>> action) {
    action.accept(this);
    if (getParent() != null) {
        getParent().traverse(action);  // Goes UP, not DOWN!
    }
}
```

**Right:**
```java
public void traverse(Consumer<TreeNode<T>> action) {
    action.accept(this);
    for (TreeNode<T> child : getChildren()) {
        child.traverse(action);  // Goes DOWN through children
    }
}
```

### 3. Modifying Children While Iterating
**Wrong:**
```java
for (TreeNode<T> child : getChildren()) {
    if (shouldRemove(child)) {
        removeChild(child);  // ConcurrentModificationException!
    }
}
```

**Right:**
```java
List<TreeNode<T>> toRemove = new ArrayList<>();
for (TreeNode<T> child : getChildren()) {
    if (shouldRemove(child)) {
        toRemove.add(child);
    }
}
for (TreeNode<T> child : toRemove) {
    removeChild(child);  // Safe - not iterating children anymore
}
```

## Exercise Preview

In the pre-class exercises, you'll:
1. Study the `TreeNode<T>` interface
2. Implement a `SimpleTreeNode<T>` class
3. Add recursive operations: `countAll()`, `find()`, `traverse()`
4. Test your implementation with various tree structures

## Key Takeaways

1. **Trees are abstractions** - They represent parent-child relationships, not pointer mechanics
2. **Same methodology** - Interface-first design works for hierarchies just like for lists
3. **Recursive thinking** - Process current node, then recurse on children
4. **Composite pattern** - When leaves and branches share an interface
5. **Everywhere you look** - File systems, menus, org charts, DOM, game scenes

## Before Class

Make sure you can:
- Explain what makes a tree different from a list
- Describe the Composite pattern
- Write a simple recursive function that processes a tree
- Give examples of hierarchical data in real applications
