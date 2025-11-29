package dk.viprogram.week06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A simple implementation of TreeNode.
 *
 * Exercise 2: Implement this class to pass all the tests in TreeNodeTest.
 *
 * Implementation hints:
 * - Store the value, parent reference, and list of children
 * - When adding a child, create a new SimpleTreeNode and set its parent
 * - Many operations are naturally recursive
 * - getChildren() should return an unmodifiable view
 *
 * @param <T> the type of value stored in each node
 */
public class SimpleTreeNode<T> implements TreeNode<T> {

    private T value;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    /**
     * Creates a new tree node with the given value.
     * The node starts with no parent (root) and no children.
     *
     * @param value the value to store
     */
    public SimpleTreeNode(T value) {
        // TODO: Initialize value, parent (null), and children (empty list)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public T getValue() {
        // TODO: Return the stored value
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void setValue(T value) {
        // TODO: Update the stored value
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public TreeNode<T> getParent() {
        // TODO: Return the parent (null for root)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public List<TreeNode<T>> getChildren() {
        // TODO: Return an unmodifiable view of children
        // Hint: Use Collections.unmodifiableList()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isLeaf() {
        // TODO: Return true if this node has no children
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isRoot() {
        // TODO: Return true if this node has no parent
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public TreeNode<T> addChild(T value) {
        // TODO: Create a new SimpleTreeNode with the given value
        // TODO: Set its parent to this node
        // TODO: Add it to this node's children
        // TODO: Return the new child node
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean removeChild(TreeNode<T> child) {
        // TODO: Remove the child from children list
        // TODO: If found, clear the child's parent reference
        // TODO: Return true if removed, false if not found
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int countAll() {
        // TODO: Count this node plus all descendants
        // This is naturally recursive:
        // count = 1 (for this node)
        // for each child: count += child.countAll()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int getDepth() {
        // TODO: Return the distance from root
        // Root has depth 0
        // For other nodes: depth = parent.getDepth() + 1
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public Optional<TreeNode<T>> find(T value) {
        // TODO: If this node's value equals the search value, return this
        // TODO: Otherwise, search children recursively
        // TODO: Return Optional.empty() if not found
        // Hint: Use Objects.equals() for null-safe comparison
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void traverse(Consumer<TreeNode<T>> action) {
        // TODO: Apply action to this node (pre-order)
        // TODO: Then traverse each child recursively
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Helper method to set the parent. Package-private for use by addChild.
     */
    void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }
}
