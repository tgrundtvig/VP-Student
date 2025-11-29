# Week 6: Trees and Hierarchies

## Overview
This week we apply the interface-first methodology to hierarchical data structures. You'll learn
that trees are not just "nodes with children" - they are **contracts** for representing hierarchical
relationships. This is where the **Composite pattern** emerges naturally from good design.

## Learning Objectives
By the end of this week, you will:
- Design tree interfaces before implementation
- Understand trees as abstractions for hierarchies
- Implement tree structures using recursive thinking
- Recognize the Composite pattern in everyday code
- Apply trees to real-world problems (file systems, menus, organization charts)

## The Big Idea

> "A tree is not about nodes and pointers. A tree is a promise about parent-child relationships."

When you think about a file system, a menu structure, or an organization chart, you're thinking
about **hierarchies**. Trees are the abstraction that captures this pattern. The same interface-first
approach we used for Lists and Stacks applies perfectly to trees.

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- Trees as abstractions for hierarchies
- The TreeNode interface concept
- Recursive thinking for tree operations
- Real-world tree examples

### Exercises
The pre-class exercises ask you to:
1. Study the `TreeNode<T>` interface
2. Implement a basic `SimpleTreeNode<T>` class
3. Implement basic tree operations (add child, find, traversal)

**Time estimate:** 60-90 minutes

## This Week's Focus: Hierarchical Data

We'll build our own tree framework:

```java
// The "wish" - how we WANT to use a tree:
TreeNode<String> root = new SimpleTreeNode<>("Company");

TreeNode<String> engineering = root.addChild("Engineering");
engineering.addChild("Frontend Team");
engineering.addChild("Backend Team");
engineering.addChild("DevOps Team");

TreeNode<String> sales = root.addChild("Sales");
sales.addChild("North Region");
sales.addChild("South Region");

// Traverse the entire organization
root.traverse(node -> System.out.println(node.getValue()));

// Find a specific department
Optional<TreeNode<String>> devops = root.find("DevOps Team");
```

This reveals what interfaces we need:
- `TreeNode<T>` - a node that can have children
- Methods for navigation: `getParent()`, `getChildren()`, `isLeaf()`
- Methods for modification: `addChild()`, `removeChild()`
- Methods for traversal: `traverse()`, `find()`

## Key Concepts

### Why Trees?
Trees naturally represent:
- **File systems**: folders contain files and other folders
- **Organization charts**: managers have direct reports
- **Menu systems**: menus have submenus and items
- **Game scenes**: scenes contain entities and sub-scenes
- **DOM/HTML**: elements contain other elements

### The Composite Pattern
When leaves and branches share the same interface, you get the Composite pattern:
```java
// Both files and folders implement the same interface
interface FileSystemEntry {
    String getName();
    long getSize();  // Folder calculates sum of children
}
```

This allows you to treat individual objects and compositions uniformly.

### Recursive Thinking
Tree operations naturally use recursion:
```java
public int countAll() {
    int count = 1;  // Count myself
    for (TreeNode<T> child : children) {
        count += child.countAll();  // Count my descendants
    }
    return count;
}
```

The pattern: **do something, then recurse on children**.

## Post-Class Work

### Exercises
After class, complete the tree exercises:
- Implement a `FileSystemNode` for files and directories
- Create a `MenuNode` for hierarchical menus
- Build tree traversal algorithms (depth-first, breadth-first)

### Homework: Menu System Design
Design and implement a tree structure for a menu system:
- Main menu with submenus
- Nested menu items
- Execute action when leaf item selected
- Navigate back to parent menu

Focus on designing the interface first, then implementing.

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 3 | Interfaces as contracts | Same principle, different structure |
| Week 4 | Wishful programming | Design tree usage first |
| Week 5 | Data structures | Trees are another data structure abstraction |
| **Week 6** | **Trees and hierarchies** | **Apply interfaces to hierarchical data** |

## Looking Ahead
Trees are fundamental to many advanced structures:
- Week 7: Maps - often implemented as trees (TreeMap)
- Week 8: GUI - component hierarchies
- Week 9: Persistence - nested data structures

## Verification Checklist
Before class, ensure you can:
- [ ] Explain why a tree is an abstraction, not just nodes with pointers
- [ ] Describe the Composite pattern in your own words
- [ ] Implement a simple recursive method that processes a tree
- [ ] Give two real-world examples of hierarchical data

## Getting Help
- Review Week 5 materials on data structures as abstractions
- Check the pre-class reading for examples
- Come to class with specific questions
