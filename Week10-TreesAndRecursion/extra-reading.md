# Extra Reading: DFS, BFS, and Recursion vs Iteration

> Companion to [Week 10's README](README.md).

## Depth-First Search (DFS)

Goes as deep as possible before backtracking.

```
     A
    /|\
   B C D
  /| |
 E F G
```

DFS visit order from A: **A, B, E, F, C, G, D** (pre-order).

### Recursive DFS

The natural way to write DFS on a tree:

```java
public class DepthFirstSearch implements Search {
    @Override
    public Node find(Node start, Predicate<Node> matches) {
        if (matches.test(start)) return start;
        if (start instanceof Directory d) {
            for (Node child : d.children()) {
                Node hit = find(child, matches);
                if (hit != null) return hit;
            }
        }
        return null;
    }
}
```

Three lines of structural logic. The call stack carries the
"where am I in the tree" state for you. This is why recursion fits trees so
well.

### Iterative DFS

Same algorithm with an explicit stack:

```java
public Node find(Node start, Predicate<Node> matches) {
    Deque<Node> stack = new ArrayDeque<>();
    stack.push(start);
    while (!stack.isEmpty()) {
        Node n = stack.pop();
        if (matches.test(n)) return n;
        if (n instanceof Directory d) {
            for (Node child : d.children()) {
                stack.push(child);
            }
        }
    }
    return null;
}
```

Functionally identical to the recursive version. The explicit stack is
just what the call stack was doing implicitly. Iterative DFS matters when:
- The tree is deep enough to overflow the call stack (Java default: ~10k
  frames).
- You need to pause/resume traversal.
- You want fine control over visit order.

## Breadth-First Search (BFS)

Visits all nodes at depth N before any at depth N+1.

```
     A
    /|\
   B C D
  /| |
 E F G
```

BFS visit order: **A, B, C, D, E, F, G**.

### Implementation

BFS always uses an *explicit queue* — there is no recursive form (the call
stack is fundamentally LIFO, not FIFO).

```java
public Node find(Node start, Predicate<Node> matches) {
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
        Node n = queue.poll();
        if (matches.test(n)) return n;
        if (n instanceof Directory d) {
            for (Node child : d.children()) {
                queue.offer(child);
            }
        }
    }
    return null;
}
```

Difference from iterative DFS: **stack → queue**. One line of code. Whole
different algorithm.

## When To Pick Which

| Goal                                          | Use        |
|----------------------------------------------|------------|
| Find any match anywhere in the tree           | DFS or BFS |
| Find the **shortest path** to a match         | **BFS**    |
| Visit the whole tree, order doesn't matter    | DFS        |
| Tree might be infinite-deep, must explore wide| **BFS**    |
| You want a *natural* traversal of a directory | DFS        |
| Implementing an undo/redo system              | DFS        |

For *trees with unbounded depth* (search trees in AI, web crawlers, etc.),
BFS is usually safer — DFS can run away down one branch forever. For
*finite trees* (file systems, menus, ASTs), DFS is fine and easier to
read.

## Complexity

For a tree with N nodes:
- **Time:** O(N) for both. Each node is visited once.
- **Space:** O(depth) for recursive DFS (call stack), O(width) for BFS
  (queue). On a balanced tree these are roughly similar; on a "stringy"
  tree DFS wins on space; on a "bushy" tree BFS wins.

## Generic Search

Our `Search` interface is generic over "what counts as a match":

```java
public interface Search {
    Node find(Node start, Predicate<Node> matches);
}
```

Now you can find anything:

```java
Search dfs = new DepthFirstSearch();
Node hit = dfs.find(root, n -> n.name().equals("config.yaml"));
Node bigFile = dfs.find(root, n -> n instanceof FileNode f && f.size() > 1_000_000);
```

The traversal logic is separated from the *condition*. Swap conditions
without touching DFS code; swap DFS for BFS without touching condition
code. This is the same separation-of-concerns lesson from the whole course
applied to algorithms.

## Recursion vs Iteration — A General Principle

A pattern that comes back over and over:

> Any algorithm you can express recursively, you can also express
> iteratively with an explicit stack (for DFS-like patterns) or queue
> (for BFS-like patterns).

Pick recursion when:
- The recursive form is much shorter and clearer.
- You're not at risk of deep call stacks.
- The recursion *follows the data shape* (e.g. trees).

Pick iteration when:
- The data could go very deep.
- You need fine control over the traversal.
- You want to pause/resume.

For *our* file system, recursion is the right call. For a web crawler with
millions of pages, iteration is.

## For The Exam

- Write DFS and BFS on the whiteboard, both versions of DFS (recursive +
  iterative).
- State the complexity of each.
- Explain why BFS is needed for shortest-path queries.
- Argue when recursion is OK and when it isn't.
- Walk through `DepthFirstSearch` and show that it works for *any* tree of
  `Node`s, regardless of the concrete node types.
