# Week 6: Post-Class Exercise Hints

Use these hints if you get stuck. Try to solve the exercises on your own first!

---

## Exercise 1: File System (File and Directory)

### Hint 1: File Constructor and Basic Methods
```java
public File(String name, long size) {
    this.name = name;
    this.size = size;
    this.parent = null;  // Set when added to directory
}

@Override
public String getName() {
    return name;
}

@Override
public long getSize() {
    return size;
}

@Override
public boolean isDirectory() {
    return false;
}
```

### Hint 2: Directory Size (Recursive!)
The key insight is that a directory's size is the sum of its contents:
```java
@Override
public long getSize() {
    long total = 0;
    for (FileSystemEntry entry : contents) {
        total += entry.getSize();  // Works for both files AND directories!
    }
    return total;
}
```

This is the Composite pattern - the same method works uniformly.

### Hint 3: Path Building
For files:
```java
@Override
public String getPath() {
    if (parent == null) {
        return "/" + name;  // Orphan file (unusual)
    }
    String parentPath = parent.getPath();
    if (parentPath.equals("/")) {
        return "/" + name;  // Parent is root
    }
    return parentPath + "/" + name;
}
```

For directories:
```java
@Override
public String getPath() {
    if (parent == null) {
        return "/";  // This is root
    }
    String parentPath = parent.getPath();
    if (parentPath.equals("/")) {
        return "/" + name;
    }
    return parentPath + "/" + name;
}
```

### Hint 4: Recursive Find in Directory
```java
@Override
public Optional<FileSystemEntry> find(String name) {
    // Check this directory
    if (getName().equals(name)) {
        return Optional.of(this);
    }

    // Search contents recursively
    for (FileSystemEntry entry : contents) {
        Optional<FileSystemEntry> found = entry.find(name);
        if (found.isPresent()) {
            return found;
        }
    }

    return Optional.empty();
}
```

### Hint 5: Counting Files and Directories
```java
public int countFiles() {
    int count = 0;
    for (FileSystemEntry entry : contents) {
        if (entry.isDirectory()) {
            count += ((Directory) entry).countFiles();
        } else {
            count += 1;
        }
    }
    return count;
}

public int countDirectories() {
    int count = 0;
    for (FileSystemEntry entry : contents) {
        if (entry.isDirectory()) {
            count += 1 + ((Directory) entry).countDirectories();
        }
    }
    return count;
}
```

---

## Exercise 2: Menu System (ActionItem and SubMenu)

### Hint 1: ActionItem Execute
```java
@Override
public void execute() {
    if (action != null) {
        action.run();
    }
}
```

### Hint 2: SubMenu Children Management
```java
public void addItem(ActionItem item) {
    children.add(item);
    item.setParent(this);
}

public void addSubmenu(SubMenu submenu) {
    children.add(submenu);
    submenu.setParent(this);
}
```

### Hint 3: Path Building with " > " Separator
```java
@Override
public String getPath() {
    if (parent == null) {
        return label;  // Root menu
    }
    return parent.getPath() + " > " + label;
}
```

### Hint 4: Recursive Find
```java
@Override
public Optional<MenuItem> find(String label) {
    if (getLabel().equals(label)) {
        return Optional.of(this);
    }

    for (MenuItem child : children) {
        Optional<MenuItem> found = child.find(label);
        if (found.isPresent()) {
            return found;
        }
    }

    return Optional.empty();
}
```

### Hint 5: Total Item Count (Recursive)
```java
public int getTotalItemCount() {
    int count = children.size();  // Direct children
    for (MenuItem child : children) {
        if (child.isSubmenu()) {
            count += ((SubMenu) child).getTotalItemCount();
        }
    }
    return count;
}
```

Wait, that's not quite right. Let me reconsider...

```java
public int getTotalItemCount() {
    int count = 0;
    for (MenuItem child : children) {
        count += 1;  // Count this child
        if (child.isSubmenu()) {
            // Add the submenu's total (not including the submenu itself again)
            count += ((SubMenu) child).getTotalItemCount() - 1;
        }
    }
    return count;
}
```

Actually, let's think about what the test expects:
- Main has: New Game, Load Game, Options submenu, Extras submenu, Exit (but wait, the test only has New Game, Load Game, Options, Extras - no Exit)
- Options has: Graphics submenu, Audio submenu, Controls
- Graphics has: Resolution, Quality, Fullscreen
- Audio has: Master, Music, Effects
- Extras has: Credits, Achievements

Actually, looking at the test, the count includes submenus themselves:
```java
public int getTotalItemCount() {
    int count = 0;
    for (MenuItem child : children) {
        count += 1;  // Count this child
        if (child.isSubmenu()) {
            count += ((SubMenu) child).getTotalItemCount();
        }
    }
    return count;
}
```

---

## General Tips

1. **Draw the tree**: Before coding, sketch the structure on paper
2. **Think recursively**: Most operations follow "do something, then recurse on children"
3. **Test incrementally**: Get basic operations working before complex ones
4. **Use the debugger**: Step through recursive calls to understand the flow
5. **Handle base cases**: Leaves (files, action items) don't recurse further

## Common Mistakes

1. **Forgetting to set parent**: When adding children, always set the parent reference
2. **Infinite recursion**: Make sure you're recursing on children, not parents
3. **Path edge cases**: Handle root specially (no leading "/" before name)
4. **Not returning early in find**: Return as soon as you find a match

## The Composite Pattern

Remember: The power of Composite is that leaf and branch share the same interface:

```java
FileSystemEntry entry = ...;  // Could be file OR directory
long size = entry.getSize();  // Works for both!

MenuItem item = ...;  // Could be action OR submenu
item.execute();       // Works for both!
```

This uniformity is what makes tree operations elegant.
