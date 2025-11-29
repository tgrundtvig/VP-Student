# Week 6: Extension Challenges

These optional challenges are for students who finish early or want to deepen their understanding.

## Extension 1: Remove Operations

Add the ability to remove files and directories:

```java
public interface FileSystemEntry {
    // ... existing methods ...

    /**
     * Removes this entry from its parent.
     * Returns true if removed, false if this is root or orphan.
     */
    boolean remove();
}

// In Directory:
public boolean removeEntry(String name) {
    // Find and remove the entry with the given name
    // Remember to clear the removed entry's parent
}
```

Consider:
- What happens when you remove a directory with contents?
- Should you allow removing the root?
- How do you handle the removed entry's parent reference?

---

## Extension 2: Move Operations

Implement moving files and directories:

```java
public class Directory {
    /**
     * Moves an entry from its current location to this directory.
     * @param entry the entry to move (can be in another directory)
     */
    public void moveHere(FileSystemEntry entry) {
        // Remove from old parent
        // Add to this directory
        // Update parent reference
    }
}
```

Consider:
- What if you try to move a directory into itself?
- What if a file with the same name already exists?

---

## Extension 3: Breadth-First Traversal

Implement breadth-first (level-by-level) traversal:

```java
public interface TreeNode<T> {
    /**
     * Traverses the tree level by level (breadth-first).
     * First visits all nodes at depth 0, then depth 1, etc.
     */
    void traverseBreadthFirst(Consumer<TreeNode<T>> action);
}
```

Implementation hint: Use a Queue!
```java
public void traverseBreadthFirst(Consumer<TreeNode<T>> action) {
    Queue<TreeNode<T>> queue = new LinkedList<>();
    queue.add(this);

    while (!queue.isEmpty()) {
        TreeNode<T> current = queue.remove();
        action.accept(current);
        queue.addAll(current.getChildren());
    }
}
```

---

## Extension 4: Post-Order Traversal

Implement post-order traversal (children before parent):

```java
public void traversePostOrder(Consumer<TreeNode<T>> action) {
    // First traverse all children
    for (TreeNode<T> child : getChildren()) {
        child.traversePostOrder(action);
    }
    // Then process this node
    action.accept(this);
}
```

When is post-order useful?
- Deleting a tree (delete children before parent)
- Calculating sizes (need children's sizes first)

---

## Extension 5: Binary Tree

Implement a binary tree variant with exactly two children (left and right):

```java
public interface BinaryTreeNode<T> {
    T getValue();
    BinaryTreeNode<T> getLeft();
    BinaryTreeNode<T> getRight();
    void setLeft(BinaryTreeNode<T> left);
    void setRight(BinaryTreeNode<T> right);
    boolean isLeaf();

    // Traversals specific to binary trees
    void traverseInOrder(Consumer<BinaryTreeNode<T>> action);  // left, root, right
    void traversePreOrder(Consumer<BinaryTreeNode<T>> action); // root, left, right
    void traversePostOrder(Consumer<BinaryTreeNode<T>> action); // left, right, root
}
```

In-order traversal is special for binary trees - it visits nodes in sorted order
if the tree is a binary search tree!

---

## Extension 6: Expression Trees

Build an expression tree for mathematical expressions:

```java
public interface Expression {
    double evaluate();
    String toInfix();  // e.g., "(3 + 4)"
}

public class NumberExpression implements Expression {
    private double value;
    // Always a leaf
}

public class BinaryOperation implements Expression {
    private Expression left;
    private Expression right;
    private char operator;  // '+', '-', '*', '/'

    @Override
    public double evaluate() {
        double l = left.evaluate();
        double r = right.evaluate();
        return switch (operator) {
            case '+' -> l + r;
            case '-' -> l - r;
            case '*' -> l * r;
            case '/' -> l / r;
            default -> throw new IllegalStateException();
        };
    }
}
```

Example: `(3 + 4) * 2`
```
       *
      / \
     +   2
    / \
   3   4
```

---

## Extension 7: Menu Navigation

Add keyboard navigation to the menu system:

```java
public class MenuNavigator {
    private SubMenu currentMenu;
    private int selectedIndex;

    public void moveUp() { ... }
    public void moveDown() { ... }
    public void select() {
        MenuItem selected = currentMenu.getChildren().get(selectedIndex);
        if (selected.isSubmenu()) {
            currentMenu = (SubMenu) selected;
            selectedIndex = 0;
        } else {
            selected.execute();
        }
    }
    public void back() {
        if (currentMenu.getParent() != null) {
            currentMenu = (SubMenu) currentMenu.getParent();
            selectedIndex = 0;
        }
    }
}
```

---

## Extension 8: Tree Visualization

Create a visual representation of trees:

```java
public class TreePrinter {
    public static <T> void print(TreeNode<T> root) {
        // Print with ASCII art:
        // Root
        // ├── Child1
        // │   ├── Grandchild1
        // │   └── Grandchild2
        // └── Child2
    }
}
```

Characters to use:
- `├──` for items with siblings after
- `└──` for last item in parent
- `│   ` for vertical line continuation
- `    ` for empty space

---

## Design Challenge: XML/HTML Parser

Design interfaces for an XML/HTML document structure:

```java
public interface XmlNode {
    String getTagName();
    String getAttribute(String name);
    List<XmlNode> getChildNodes();
    String getTextContent();
    Optional<XmlNode> findById(String id);
    List<XmlNode> findByTag(String tagName);
}
```

This is how real DOM APIs work!

---

## Submission

For any extensions you complete:
1. Include source code in a separate `extensions/` package
2. Write tests for your implementations
3. Document your design decisions

These are for extra learning - they won't affect your grade but will deepen your understanding!
