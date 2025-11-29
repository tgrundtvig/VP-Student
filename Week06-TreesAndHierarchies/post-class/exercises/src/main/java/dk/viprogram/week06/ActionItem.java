package dk.viprogram.week06;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents an actionable menu item (a leaf in the menu tree).
 *
 * Exercise 2a: Implement this class to pass all the MenuTest tests.
 *
 * An ActionItem has a label and an action (Runnable) that executes when selected.
 * It cannot have children - it's always a leaf.
 */
public class ActionItem implements MenuItem {

    private String label;
    private Runnable action;
    private MenuItem parent;

    /**
     * Creates a new action item with the given label and action.
     *
     * @param label the display text
     * @param action the action to execute when selected
     */
    public ActionItem(String label, Runnable action) {
        // TODO: Initialize label and action
        // TODO: Parent starts as null
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getLabel() {
        // TODO: Return the label
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isSubmenu() {
        // TODO: Action items are not submenus, return false
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public MenuItem getParent() {
        // TODO: Return the parent
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public List<MenuItem> getChildren() {
        // TODO: Action items have no children, return empty list
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void execute() {
        // TODO: Run the action
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getPath() {
        // TODO: Build the path from root to this item
        // Example: "Main Menu > Options > Sound"
        // Use " > " as separator
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public Optional<MenuItem> find(String label) {
        // TODO: If this item's label equals the search label, return this
        // TODO: Otherwise return Optional.empty()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void printMenu(String indent) {
        // TODO: Print the label with indentation
        // Format: indent + "- " + label
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Sets the parent menu item. Package-private for use by SubMenu.
     */
    void setParent(MenuItem parent) {
        this.parent = parent;
    }
}
