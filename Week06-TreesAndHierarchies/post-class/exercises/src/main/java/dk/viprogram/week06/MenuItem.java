package dk.viprogram.week06;

import java.util.List;
import java.util.Optional;

/**
 * Represents an item in a hierarchical menu system.
 *
 * This is another example of the Composite pattern: both actionable items
 * (leaves) and submenus (branches) implement the same interface.
 *
 * Exercise 2: This interface is already complete. Study it to understand
 * how menus can be modeled as trees.
 */
public interface MenuItem {

    /**
     * Returns the display text for this menu item.
     *
     * @return the label
     */
    String getLabel();

    /**
     * Returns true if this item is a submenu (has children).
     *
     * @return true for submenus, false for action items
     */
    boolean isSubmenu();

    /**
     * Returns the parent menu item, or null for root menu.
     *
     * @return the parent, or null
     */
    MenuItem getParent();

    /**
     * Returns the children of this menu item (empty for non-submenus).
     *
     * @return list of children
     */
    List<MenuItem> getChildren();

    /**
     * Executes this menu item's action.
     * For submenus, this might display the submenu.
     * For action items, this executes the associated action.
     */
    void execute();

    /**
     * Returns the full path of this item in the menu hierarchy.
     * Example: "Main Menu > Options > Graphics"
     *
     * @return the full menu path
     */
    String getPath();

    /**
     * Searches for a menu item with the given label.
     *
     * @param label the label to search for
     * @return Optional containing the found item, or empty if not found
     */
    Optional<MenuItem> find(String label);

    /**
     * Prints the menu structure in a tree format.
     *
     * @param indent the current indentation
     */
    void printMenu(String indent);
}
