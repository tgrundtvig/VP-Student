package dk.viprogram.week06;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A generic tree node interface for hierarchical data structures.
 *
 * Exercise 1: This interface is already complete. Study it carefully
 * to understand what contract it defines. You will implement this
 * interface in Exercise 2.
 *
 * A TreeNode represents a single node in a tree structure:
 * - Each node contains a value of type T
 * - Each node can have zero or more children
 * - Each node (except the root) has exactly one parent
 *
 * @param <T> the type of value stored in each node
 */
public interface TreeNode<T> {

    /**
     * Returns the value stored in this node.
     *
     * @return the value
     */
    T getValue();

    /**
     * Sets the value stored in this node.
     *
     * @param value the new value
     */
    void setValue(T value);

    /**
     * Returns this node's parent, or null if this is the root.
     *
     * @return the parent node, or null for root
     */
    TreeNode<T> getParent();

    /**
     * Returns an unmodifiable list of this node's children.
     * The list may be empty but never null.
     *
     * @return list of children
     */
    List<TreeNode<T>> getChildren();

    /**
     * Returns true if this node has no children (is a leaf).
     *
     * @return true if leaf, false otherwise
     */
    boolean isLeaf();

    /**
     * Returns true if this node has no parent (is the root).
     *
     * @return true if root, false otherwise
     */
    boolean isRoot();

    /**
     * Creates a new child node with the given value and adds it
     * to this node's children.
     *
     * @param value the value for the new child
     * @return the newly created child node
     */
    TreeNode<T> addChild(T value);

    /**
     * Removes a child node from this node's children.
     *
     * @param child the child to remove
     * @return true if the child was found and removed, false otherwise
     */
    boolean removeChild(TreeNode<T> child);

    /**
     * Returns the total number of nodes in the subtree rooted at this node.
     * This includes the node itself plus all descendants.
     *
     * @return total count of nodes in subtree
     */
    int countAll();

    /**
     * Returns the depth of this node (distance from root).
     * The root has depth 0, its children have depth 1, etc.
     *
     * @return the depth of this node
     */
    int getDepth();

    /**
     * Searches this node and all its descendants for a node
     * with the given value. Uses equals() for comparison.
     *
     * @param value the value to search for
     * @return Optional containing the found node, or empty if not found
     */
    Optional<TreeNode<T>> find(T value);

    /**
     * Applies the given action to this node and all its descendants.
     * Traversal order is pre-order (parent before children).
     *
     * @param action the action to apply to each node
     */
    void traverse(Consumer<TreeNode<T>> action);
}
