package dk.viprogram.week06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a submenu in the menu system (a branch in the menu tree).
 *
 * Exercise 2b: Implement this class to pass all the MenuTest tests.
 *
 * A SubMenu has a label and can contain other menu items (both ActionItems and SubMenus).
 * When executed, it typically displays its children.
 */
public class SubMenu implements MenuItem {

    private String label;
    private MenuItem parent;
    private List<MenuItem> children;
    private Runnable onDisplay;

    /**
     * Creates a new submenu with the given label.
     *
     * @param label the display text
     */
    public SubMenu(String label) {
        // TODO: Initialize label
        // TODO: Initialize children as empty list
        // TODO: Parent starts as null
        // TODO: onDisplay can be null (set later if needed)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getLabel() {
        // TODO: Return the label
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isSubmenu() {
        // TODO: SubMenus are submenus, return true
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public MenuItem getParent() {
        // TODO: Return the parent
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public List<MenuItem> getChildren() {
        // TODO: Return unmodifiable view of children
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void execute() {
        // TODO: If onDisplay is set, run it
        // TODO: (This simulates displaying the submenu)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getPath() {
        // TODO: Build the path from root to this menu
        // Example: "Main Menu > Options"
        // Use " > " as separator
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public Optional<MenuItem> find(String label) {
        // TODO: Check if this menu's label matches
        // TODO: If not, search children recursively
        // TODO: Return first match found, or Optional.empty()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void printMenu(String indent) {
        // TODO: Print this menu's label with "+" prefix (indicates submenu)
        // TODO: Then print each child with increased indentation
        // Format: indent + "+ " + label
        // Children: use indent + "  " (two spaces)
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Adds an action item to this submenu.
     *
     * @param item the action item to add
     */
    public void addItem(ActionItem item) {
        // TODO: Add item to children
        // TODO: Set item's parent to this menu
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Adds a submenu to this menu.
     *
     * @param submenu the submenu to add
     */
    public void addSubmenu(SubMenu submenu) {
        // TODO: Add submenu to children
        // TODO: Set submenu's parent to this menu
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Creates and adds a new action item.
     *
     * @param label the action label
     * @param action the action to execute
     * @return the created action item
     */
    public ActionItem createItem(String label, Runnable action) {
        // TODO: Create new ActionItem
        // TODO: Add it to this menu
        // TODO: Return the new item
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Creates and adds a new submenu.
     *
     * @param label the submenu label
     * @return the created submenu
     */
    public SubMenu createSubmenu(String label) {
        // TODO: Create new SubMenu
        // TODO: Add it to this menu
        // TODO: Return the new submenu
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Sets a callback that runs when this submenu is executed/displayed.
     *
     * @param onDisplay the callback
     */
    public void setOnDisplay(Runnable onDisplay) {
        this.onDisplay = onDisplay;
    }

    /**
     * Returns the number of items in this menu (not recursive).
     *
     * @return direct child count
     */
    public int getItemCount() {
        // TODO: Return children.size()
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns the total number of items in this menu and all submenus.
     *
     * @return total item count (recursive)
     */
    public int getTotalItemCount() {
        // TODO: Count all items recursively
        // For each child: add 1, and if it's a submenu, add its getTotalItemCount()
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Sets the parent menu item. Package-private for use by addSubmenu.
     */
    void setParent(MenuItem parent) {
        this.parent = parent;
    }
}
