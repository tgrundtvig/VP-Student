# Week 6: Self-Assessment Rubric

Use this rubric to evaluate your own work before submission.

## Exercise 1: File System (File and Directory) (50 points)

### File Implementation (15 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor stores name and size | 3 | |
| getName() and getSize() work correctly | 2 | |
| isDirectory() returns false | 1 | |
| getParent() returns parent or null | 2 | |
| getPath() builds correct path from root | 4 | |
| find() returns this if name matches, empty otherwise | 3 | |

**File Total: ___ / 15**

### Directory Implementation (35 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor initializes name and empty contents | 3 | |
| isDirectory() returns true | 1 | |
| getContents() returns unmodifiable list | 2 | |
| addFile() adds file and sets parent | 3 | |
| addDirectory() adds directory and sets parent | 3 | |
| createFile() creates, adds, and returns file | 3 | |
| createDirectory() creates, adds, and returns directory | 3 | |
| getSize() calculates recursive sum | 5 | |
| countFiles() counts all files recursively | 4 | |
| countDirectories() counts all subdirectories recursively | 4 | |
| find() searches recursively through contents | 4 | |

**Directory Total: ___ / 35**

---

## Exercise 2: Menu System (ActionItem and SubMenu) (50 points)

### ActionItem Implementation (15 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor stores label and action | 3 | |
| isSubmenu() returns false | 1 | |
| getChildren() returns empty list | 1 | |
| execute() runs the action | 3 | |
| getPath() builds correct path with " > " separator | 4 | |
| find() returns this if label matches, empty otherwise | 3 | |

**ActionItem Total: ___ / 15**

### SubMenu Implementation (35 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor initializes label and empty children | 3 | |
| isSubmenu() returns true | 1 | |
| getChildren() returns unmodifiable list | 2 | |
| addItem() adds item and sets parent | 3 | |
| addSubmenu() adds submenu and sets parent | 3 | |
| createItem() creates, adds, and returns item | 3 | |
| createSubmenu() creates, adds, and returns submenu | 3 | |
| execute() runs onDisplay callback if set | 3 | |
| getItemCount() returns direct child count | 2 | |
| getTotalItemCount() counts all items recursively | 5 | |
| find() searches recursively through children | 4 | |
| printMenu() prints tree structure correctly | 3 | |

**SubMenu Total: ___ / 35**

---

## Overall Assessment

**Total Score: ___ / 100**

### Grading Scale
- 90-100: Excellent - You've mastered hierarchical structures and Composite pattern
- 80-89: Good - Solid understanding with minor gaps
- 70-79: Satisfactory - Basic concepts understood
- Below 70: Needs work - Review the material and try again

### Reflection Questions

Answer these honestly:

1. **Did you understand the Composite pattern?**
   - [ ] Yes, I can explain how File/Directory and ActionItem/SubMenu use it
   - [ ] Partially, I see the pattern but struggle to explain it
   - [ ] No, I'm not sure what makes this a pattern

2. **Did recursive operations make sense?**
   - [ ] Yes, I understood the base case and recursive case clearly
   - [ ] Partially, I got them working but wasn't confident
   - [ ] No, I struggled with recursive thinking

3. **What was the hardest part?**

   _Your answer:_

4. **Can you think of another real-world example of hierarchical data?**

   _Your answer:_

5. **How would you extend these implementations?**

   _Your answer:_

---

## Design Quality Checklist

Beyond just passing tests, check:

- [ ] Recursive methods have clear base cases
- [ ] Parent references are always set when adding children
- [ ] No duplicate code between similar operations
- [ ] Path building handles root directory correctly
- [ ] Unmodifiable views used for collections

---

## Key Concepts Verification

Can you explain these concepts?

- [ ] **Composite Pattern**: Treating individual objects and compositions uniformly
- [ ] **Recursive Size Calculation**: Directory size = sum of all contents' sizes
- [ ] **Path Building**: Constructed from root through all ancestors
- [ ] **Leaf vs Branch**: Files/ActionItems are leaves, Directories/SubMenus are branches

---

## Extension Challenge (Bonus)

If you completed the extensions, add your self-assessment:

| Extension | Completed? | Notes |
|-----------|------------|-------|
| Remove method for directory contents | [ ] | |
| Move file/directory to another location | [ ] | |
| Implement breadth-first traversal | [ ] | |
| Add keyboard navigation to menu | [ ] | |
| Implement binary tree variant | [ ] | |
