package dk.viprogram.week06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SimpleTreeNode implementation.
 *
 * These tests verify that your SimpleTreeNode correctly implements
 * the TreeNode interface for hierarchical data structures.
 */
class TreeNodeTest {

    @Nested
    @DisplayName("Exercise 2a: Basic Node Operations")
    class BasicOperations {

        @Test
        @DisplayName("New node should store its value")
        void newNodeStoresValue() {
            TreeNode<String> node = new SimpleTreeNode<>("Hello");
            assertEquals("Hello", node.getValue());
        }

        @Test
        @DisplayName("setValue should update the value")
        void setValueUpdatesValue() {
            TreeNode<String> node = new SimpleTreeNode<>("Hello");
            node.setValue("World");
            assertEquals("World", node.getValue());
        }

        @Test
        @DisplayName("New node should be a root (no parent)")
        void newNodeIsRoot() {
            TreeNode<String> node = new SimpleTreeNode<>("Root");
            assertTrue(node.isRoot());
            assertNull(node.getParent());
        }

        @Test
        @DisplayName("New node should be a leaf (no children)")
        void newNodeIsLeaf() {
            TreeNode<String> node = new SimpleTreeNode<>("Leaf");
            assertTrue(node.isLeaf());
            assertTrue(node.getChildren().isEmpty());
        }

        @Test
        @DisplayName("Node should handle null value")
        void handlesNullValue() {
            TreeNode<String> node = new SimpleTreeNode<>(null);
            assertNull(node.getValue());
        }
    }

    @Nested
    @DisplayName("Exercise 2b: Parent-Child Relationships")
    class ParentChildRelationships {

        @Test
        @DisplayName("addChild should create and return new child")
        void addChildCreatesChild() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> child = parent.addChild("Child");

            assertNotNull(child);
            assertEquals("Child", child.getValue());
        }

        @Test
        @DisplayName("addChild should set parent reference")
        void addChildSetsParent() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> child = parent.addChild("Child");

            assertFalse(child.isRoot());
            assertEquals(parent, child.getParent());
        }

        @Test
        @DisplayName("addChild should add to children list")
        void addChildUpdatesChildrenList() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> child = parent.addChild("Child");

            assertFalse(parent.isLeaf());
            assertEquals(1, parent.getChildren().size());
            assertTrue(parent.getChildren().contains(child));
        }

        @Test
        @DisplayName("Multiple children should maintain order")
        void multipleChildrenMaintainOrder() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> child1 = parent.addChild("First");
            TreeNode<String> child2 = parent.addChild("Second");
            TreeNode<String> child3 = parent.addChild("Third");

            List<TreeNode<String>> children = parent.getChildren();
            assertEquals(3, children.size());
            assertEquals(child1, children.get(0));
            assertEquals(child2, children.get(1));
            assertEquals(child3, children.get(2));
        }

        @Test
        @DisplayName("getChildren should return unmodifiable list")
        void getChildrenReturnsUnmodifiable() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            parent.addChild("Child");

            List<TreeNode<String>> children = parent.getChildren();
            assertThrows(UnsupportedOperationException.class, () -> {
                children.add(new SimpleTreeNode<>("Hacker"));
            });
        }

        @Test
        @DisplayName("removeChild should remove and return true")
        void removeChildRemovesChild() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> child1 = parent.addChild("Child1");
            TreeNode<String> child2 = parent.addChild("Child2");

            boolean removed = parent.removeChild(child1);

            assertTrue(removed);
            assertEquals(1, parent.getChildren().size());
            assertFalse(parent.getChildren().contains(child1));
            assertTrue(parent.getChildren().contains(child2));
        }

        @Test
        @DisplayName("removeChild should return false if not found")
        void removeChildReturnsFalseIfNotFound() {
            TreeNode<String> parent = new SimpleTreeNode<>("Parent");
            TreeNode<String> notAChild = new SimpleTreeNode<>("Stranger");

            boolean removed = parent.removeChild(notAChild);

            assertFalse(removed);
        }
    }

    @Nested
    @DisplayName("Exercise 2c: Tree Traversal and Counting")
    class TraversalAndCounting {

        @Test
        @DisplayName("countAll for single node should be 1")
        void countAllSingleNode() {
            TreeNode<String> node = new SimpleTreeNode<>("Single");
            assertEquals(1, node.countAll());
        }

        @Test
        @DisplayName("countAll should count all descendants")
        void countAllCountsDescendants() {
            // Build tree:
            //       Root
            //      /    \
            //    A       B
            //   /|\      |
            //  C D E     F
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            TreeNode<String> a = root.addChild("A");
            TreeNode<String> b = root.addChild("B");
            a.addChild("C");
            a.addChild("D");
            a.addChild("E");
            b.addChild("F");

            assertEquals(7, root.countAll());
            assertEquals(4, a.countAll());
            assertEquals(2, b.countAll());
        }

        @Test
        @DisplayName("getDepth for root should be 0")
        void depthOfRootIsZero() {
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            assertEquals(0, root.getDepth());
        }

        @Test
        @DisplayName("getDepth should return correct depth")
        void depthIsCorrect() {
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            TreeNode<String> level1 = root.addChild("Level1");
            TreeNode<String> level2 = level1.addChild("Level2");
            TreeNode<String> level3 = level2.addChild("Level3");

            assertEquals(0, root.getDepth());
            assertEquals(1, level1.getDepth());
            assertEquals(2, level2.getDepth());
            assertEquals(3, level3.getDepth());
        }

        @Test
        @DisplayName("traverse should visit all nodes in pre-order")
        void traverseVisitsAllNodes() {
            TreeNode<String> root = new SimpleTreeNode<>("A");
            TreeNode<String> b = root.addChild("B");
            TreeNode<String> c = root.addChild("C");
            b.addChild("D");
            b.addChild("E");

            List<String> visited = new ArrayList<>();
            root.traverse(node -> visited.add(node.getValue()));

            // Pre-order: A, B, D, E, C
            assertEquals(List.of("A", "B", "D", "E", "C"), visited);
        }

        @Test
        @DisplayName("traverse on leaf should only visit that node")
        void traverseOnLeaf() {
            TreeNode<String> leaf = new SimpleTreeNode<>("Leaf");

            List<String> visited = new ArrayList<>();
            leaf.traverse(node -> visited.add(node.getValue()));

            assertEquals(List.of("Leaf"), visited);
        }
    }

    @Nested
    @DisplayName("Exercise 2d: Find Operation")
    class FindOperation {

        @Test
        @DisplayName("find should return this node if value matches")
        void findReturnsThisNode() {
            TreeNode<String> node = new SimpleTreeNode<>("Target");

            Optional<TreeNode<String>> found = node.find("Target");

            assertTrue(found.isPresent());
            assertEquals(node, found.get());
        }

        @Test
        @DisplayName("find should search descendants")
        void findSearchesDescendants() {
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            TreeNode<String> child = root.addChild("Child");
            TreeNode<String> grandchild = child.addChild("Grandchild");

            Optional<TreeNode<String>> found = root.find("Grandchild");

            assertTrue(found.isPresent());
            assertEquals(grandchild, found.get());
        }

        @Test
        @DisplayName("find should return empty if not found")
        void findReturnsEmptyIfNotFound() {
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            root.addChild("Child1");
            root.addChild("Child2");

            Optional<TreeNode<String>> found = root.find("NotHere");

            assertTrue(found.isEmpty());
        }

        @Test
        @DisplayName("find should handle null values")
        void findHandlesNull() {
            TreeNode<String> root = new SimpleTreeNode<>("Root");
            TreeNode<String> nullChild = root.addChild(null);

            Optional<TreeNode<String>> found = root.find(null);

            assertTrue(found.isPresent());
            assertEquals(nullChild, found.get());
        }

        @Test
        @DisplayName("find should work on deep trees")
        void findWorksOnDeepTrees() {
            // Build a deep tree
            TreeNode<Integer> root = new SimpleTreeNode<>(0);
            TreeNode<Integer> current = root;
            for (int i = 1; i <= 10; i++) {
                current = current.addChild(i);
            }

            // Find the deepest node
            Optional<TreeNode<Integer>> found = root.find(10);

            assertTrue(found.isPresent());
            assertEquals(10, found.get().getValue());
            assertEquals(10, found.get().getDepth());
        }
    }

    @Nested
    @DisplayName("Exercise 2e: Complex Tree Structures")
    class ComplexTreeStructures {

        @Test
        @DisplayName("Organization chart structure")
        void organizationChart() {
            TreeNode<String> company = new SimpleTreeNode<>("Company");

            TreeNode<String> engineering = company.addChild("Engineering");
            engineering.addChild("Frontend");
            engineering.addChild("Backend");
            engineering.addChild("DevOps");

            TreeNode<String> sales = company.addChild("Sales");
            sales.addChild("North");
            sales.addChild("South");

            assertEquals(8, company.countAll());
            assertEquals(4, engineering.countAll());
            assertEquals(3, sales.countAll());

            assertTrue(company.find("DevOps").isPresent());
            assertTrue(company.find("South").isPresent());
            assertFalse(company.find("Marketing").isPresent());
        }

        @Test
        @DisplayName("File system structure")
        void fileSystemStructure() {
            TreeNode<String> root = new SimpleTreeNode<>("/");

            TreeNode<String> home = root.addChild("home");
            TreeNode<String> user = home.addChild("user");
            user.addChild("documents");
            user.addChild("pictures");
            user.addChild("downloads");

            TreeNode<String> etc = root.addChild("etc");
            etc.addChild("config");

            TreeNode<String> var = root.addChild("var");
            var.addChild("log");

            assertEquals(10, root.countAll());

            // Find paths
            Optional<TreeNode<String>> docs = root.find("documents");
            assertTrue(docs.isPresent());
            assertEquals(3, docs.get().getDepth());  // /home/user/documents
        }

        @Test
        @DisplayName("Wide tree (many siblings)")
        void wideTree() {
            TreeNode<Integer> root = new SimpleTreeNode<>(0);

            // Add 20 children to root
            for (int i = 1; i <= 20; i++) {
                root.addChild(i);
            }

            assertEquals(21, root.countAll());
            assertEquals(20, root.getChildren().size());
            assertFalse(root.isLeaf());

            // All children are leaves
            for (TreeNode<Integer> child : root.getChildren()) {
                assertTrue(child.isLeaf());
                assertEquals(1, child.getDepth());
            }
        }
    }
}
