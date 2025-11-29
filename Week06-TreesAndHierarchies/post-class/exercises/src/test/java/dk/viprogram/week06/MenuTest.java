package dk.viprogram.week06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ActionItem and SubMenu implementations.
 *
 * These tests verify that your menu system correctly models
 * hierarchical menus using the Composite pattern.
 */
class MenuTest {

    @Nested
    @DisplayName("Exercise 2a: ActionItem Implementation")
    class ActionItemTests {

        @Test
        @DisplayName("ActionItem should store label")
        void actionItemStoresLabel() {
            ActionItem item = new ActionItem("New Game", () -> {});

            assertEquals("New Game", item.getLabel());
        }

        @Test
        @DisplayName("ActionItem should not be submenu")
        void actionItemIsNotSubmenu() {
            ActionItem item = new ActionItem("Exit", () -> {});

            assertFalse(item.isSubmenu());
        }

        @Test
        @DisplayName("ActionItem should have no children")
        void actionItemHasNoChildren() {
            ActionItem item = new ActionItem("Save", () -> {});

            assertTrue(item.getChildren().isEmpty());
        }

        @Test
        @DisplayName("ActionItem execute should run the action")
        void actionItemExecuteRunsAction() {
            List<String> executed = new ArrayList<>();
            ActionItem item = new ActionItem("Run", () -> executed.add("ran"));

            item.execute();

            assertEquals(1, executed.size());
            assertEquals("ran", executed.get(0));
        }

        @Test
        @DisplayName("ActionItem find should return itself if label matches")
        void actionItemFindReturnsItself() {
            ActionItem item = new ActionItem("Target", () -> {});

            Optional<MenuItem> found = item.find("Target");

            assertTrue(found.isPresent());
            assertEquals(item, found.get());
        }

        @Test
        @DisplayName("ActionItem find should return empty if label doesn't match")
        void actionItemFindReturnsEmpty() {
            ActionItem item = new ActionItem("Actual", () -> {});

            Optional<MenuItem> found = item.find("Different");

            assertTrue(found.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 2b: SubMenu Implementation")
    class SubMenuTests {

        @Test
        @DisplayName("SubMenu should store label")
        void subMenuStoresLabel() {
            SubMenu menu = new SubMenu("Options");

            assertEquals("Options", menu.getLabel());
        }

        @Test
        @DisplayName("SubMenu should be a submenu")
        void subMenuIsSubmenu() {
            SubMenu menu = new SubMenu("Settings");

            assertTrue(menu.isSubmenu());
        }

        @Test
        @DisplayName("New SubMenu should be empty")
        void newSubMenuIsEmpty() {
            SubMenu menu = new SubMenu("Empty");

            assertEquals(0, menu.getItemCount());
            assertTrue(menu.getChildren().isEmpty());
        }

        @Test
        @DisplayName("Adding item should update children")
        void addingItemUpdatesChildren() {
            SubMenu menu = new SubMenu("Main");
            ActionItem item = new ActionItem("Start", () -> {});

            menu.addItem(item);

            assertEquals(1, menu.getItemCount());
            assertTrue(menu.getChildren().contains(item));
        }

        @Test
        @DisplayName("Adding item should set item's parent")
        void addingItemSetsParent() {
            SubMenu menu = new SubMenu("Parent Menu");
            ActionItem item = new ActionItem("Child Item", () -> {});

            menu.addItem(item);

            assertEquals(menu, item.getParent());
        }

        @Test
        @DisplayName("Adding submenu should update children and parent")
        void addingSubmenuUpdatesChildrenAndParent() {
            SubMenu parent = new SubMenu("Main");
            SubMenu child = new SubMenu("Options");

            parent.addSubmenu(child);

            assertTrue(parent.getChildren().contains(child));
            assertEquals(parent, child.getParent());
        }

        @Test
        @DisplayName("createItem should add item and return it")
        void createItemAddsAndReturns() {
            SubMenu menu = new SubMenu("Menu");
            List<String> executed = new ArrayList<>();

            ActionItem created = menu.createItem("Test", () -> executed.add("test"));

            assertEquals("Test", created.getLabel());
            assertEquals(menu, created.getParent());
            assertTrue(menu.getChildren().contains(created));
        }

        @Test
        @DisplayName("createSubmenu should add submenu and return it")
        void createSubmenuAddsAndReturns() {
            SubMenu parent = new SubMenu("Main");

            SubMenu created = parent.createSubmenu("Options");

            assertEquals("Options", created.getLabel());
            assertEquals(parent, created.getParent());
            assertTrue(parent.getChildren().contains(created));
        }

        @Test
        @DisplayName("SubMenu execute should run onDisplay callback")
        void subMenuExecuteRunsOnDisplay() {
            SubMenu menu = new SubMenu("Menu");
            List<String> displayed = new ArrayList<>();
            menu.setOnDisplay(() -> displayed.add("displayed"));

            menu.execute();

            assertEquals(1, displayed.size());
        }
    }

    @Nested
    @DisplayName("Exercise 2c: Menu Hierarchy")
    class MenuHierarchyTests {

        @Test
        @DisplayName("Nested menus should maintain parent references")
        void nestedMenusHaveCorrectParents() {
            SubMenu main = new SubMenu("Main Menu");
            SubMenu options = main.createSubmenu("Options");
            SubMenu graphics = options.createSubmenu("Graphics");
            ActionItem resolution = graphics.createItem("Resolution", () -> {});

            assertNull(main.getParent());
            assertEquals(main, options.getParent());
            assertEquals(options, graphics.getParent());
            assertEquals(graphics, resolution.getParent());
        }

        @Test
        @DisplayName("find should search recursively through submenus")
        void findSearchesRecursively() {
            SubMenu main = new SubMenu("Main Menu");
            SubMenu options = main.createSubmenu("Options");
            SubMenu graphics = options.createSubmenu("Graphics");
            ActionItem target = graphics.createItem("Resolution", () -> {});

            Optional<MenuItem> found = main.find("Resolution");

            assertTrue(found.isPresent());
            assertEquals(target, found.get());
        }

        @Test
        @DisplayName("getTotalItemCount should count all items recursively")
        void getTotalItemCountIsRecursive() {
            SubMenu main = new SubMenu("Main");
            main.createItem("New Game", () -> {});
            main.createItem("Load Game", () -> {});

            SubMenu options = main.createSubmenu("Options");
            options.createItem("Sound", () -> {});
            options.createItem("Graphics", () -> {});
            options.createItem("Controls", () -> {});

            SubMenu extras = main.createSubmenu("Extras");
            extras.createItem("Credits", () -> {});

            // Main has:
            //   - New Game (1)
            //   - Load Game (1)
            //   - Options submenu (1) with 3 items inside
            //   - Extras submenu (1) with 1 item inside
            // Total = 2 + 1 + 3 + 1 + 1 = 8
            assertEquals(8, main.getTotalItemCount());
            assertEquals(3, options.getTotalItemCount());  // 3 items: Sound, Graphics, Controls
            assertEquals(1, extras.getTotalItemCount());   // 1 item: Credits
        }
    }

    @Nested
    @DisplayName("Exercise 2d: Menu Paths")
    class MenuPathTests {

        @Test
        @DisplayName("Root menu path should be just the label")
        void rootMenuPathIsLabel() {
            SubMenu root = new SubMenu("Main Menu");

            assertEquals("Main Menu", root.getPath());
        }

        @Test
        @DisplayName("Nested menu path should include parents")
        void nestedMenuPathIncludesParents() {
            SubMenu main = new SubMenu("Main Menu");
            SubMenu options = main.createSubmenu("Options");
            SubMenu graphics = options.createSubmenu("Graphics");

            assertEquals("Main Menu > Options", options.getPath());
            assertEquals("Main Menu > Options > Graphics", graphics.getPath());
        }

        @Test
        @DisplayName("Action item path should include all parent menus")
        void actionItemPathIncludesAllParents() {
            SubMenu main = new SubMenu("Main Menu");
            SubMenu options = main.createSubmenu("Options");
            ActionItem sound = options.createItem("Sound", () -> {});

            assertEquals("Main Menu > Options > Sound", sound.getPath());
        }
    }

    @Nested
    @DisplayName("Exercise 2e: Complete Game Menu Example")
    class CompleteGameMenuExample {

        @Test
        @DisplayName("Full game menu structure")
        void fullGameMenuStructure() {
            List<String> actions = new ArrayList<>();

            // Build menu structure
            SubMenu mainMenu = new SubMenu("Main Menu");

            mainMenu.createItem("New Game", () -> actions.add("newgame"));
            mainMenu.createItem("Continue", () -> actions.add("continue"));

            SubMenu options = mainMenu.createSubmenu("Options");

            SubMenu graphics = options.createSubmenu("Graphics");
            graphics.createItem("Resolution", () -> actions.add("resolution"));
            graphics.createItem("Quality", () -> actions.add("quality"));
            graphics.createItem("Fullscreen", () -> actions.add("fullscreen"));

            SubMenu audio = options.createSubmenu("Audio");
            audio.createItem("Master Volume", () -> actions.add("master"));
            audio.createItem("Music", () -> actions.add("music"));
            audio.createItem("Effects", () -> actions.add("effects"));

            options.createItem("Controls", () -> actions.add("controls"));

            SubMenu extras = mainMenu.createSubmenu("Extras");
            extras.createItem("Credits", () -> actions.add("credits"));
            extras.createItem("Achievements", () -> actions.add("achievements"));

            mainMenu.createItem("Exit", () -> actions.add("exit"));

            // Verify structure
            // Total items: 5 at top level (New Game, Continue, Options, Extras, Exit)
            //            + 3 in Options (Graphics, Audio, Controls)
            //            + 3 in Graphics (Resolution, Quality, Fullscreen)
            //            + 3 in Audio (Master, Music, Effects)
            //            + 2 in Extras (Credits, Achievements)
            //            = 16
            assertEquals(16, mainMenu.getTotalItemCount());

            // Find and execute some items
            Optional<MenuItem> resolution = mainMenu.find("Resolution");
            assertTrue(resolution.isPresent());
            assertEquals("Main Menu > Options > Graphics > Resolution", resolution.get().getPath());
            resolution.get().execute();
            assertEquals("resolution", actions.get(0));

            Optional<MenuItem> music = mainMenu.find("Music");
            assertTrue(music.isPresent());
            music.get().execute();
            assertEquals("music", actions.get(1));

            // Verify submenus
            assertTrue(mainMenu.find("Options").isPresent());
            assertTrue(mainMenu.find("Graphics").isPresent());
            assertTrue(mainMenu.find("Audio").isPresent());
            assertFalse(mainMenu.find("Missing").isPresent());
        }
    }
}
