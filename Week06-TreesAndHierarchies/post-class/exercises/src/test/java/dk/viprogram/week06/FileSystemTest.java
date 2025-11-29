package dk.viprogram.week06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the File and Directory implementations.
 *
 * These tests verify that your file system implementation correctly
 * uses the Composite pattern to model files and directories uniformly.
 */
class FileSystemTest {

    @Nested
    @DisplayName("Exercise 1a: File Implementation")
    class FileTests {

        @Test
        @DisplayName("File should store name and size")
        void fileStoresNameAndSize() {
            File file = new File("document.txt", 1024);

            assertEquals("document.txt", file.getName());
            assertEquals(1024, file.getSize());
        }

        @Test
        @DisplayName("File should not be a directory")
        void fileIsNotDirectory() {
            File file = new File("image.png", 2048);

            assertFalse(file.isDirectory());
        }

        @Test
        @DisplayName("New file should have no parent")
        void newFileHasNoParent() {
            File file = new File("orphan.txt", 100);

            assertNull(file.getParent());
        }

        @Test
        @DisplayName("File find should return itself if name matches")
        void fileFindReturnsItself() {
            File file = new File("target.txt", 500);

            Optional<FileSystemEntry> found = file.find("target.txt");

            assertTrue(found.isPresent());
            assertEquals(file, found.get());
        }

        @Test
        @DisplayName("File find should return empty if name doesn't match")
        void fileFindReturnsEmpty() {
            File file = new File("actual.txt", 500);

            Optional<FileSystemEntry> found = file.find("different.txt");

            assertTrue(found.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 1b: Directory Implementation")
    class DirectoryTests {

        @Test
        @DisplayName("Directory should store name")
        void directoryStoresName() {
            Directory dir = new Directory("documents");

            assertEquals("documents", dir.getName());
        }

        @Test
        @DisplayName("Directory should be a directory")
        void directoryIsDirectory() {
            Directory dir = new Directory("folder");

            assertTrue(dir.isDirectory());
        }

        @Test
        @DisplayName("New directory should be empty")
        void newDirectoryIsEmpty() {
            Directory dir = new Directory("empty");

            assertTrue(dir.isEmpty());
            assertEquals(0, dir.getContents().size());
        }

        @Test
        @DisplayName("Empty directory should have zero size")
        void emptyDirectoryHasZeroSize() {
            Directory dir = new Directory("empty");

            assertEquals(0, dir.getSize());
        }

        @Test
        @DisplayName("Directory should contain added files")
        void directoryContainsAddedFiles() {
            Directory dir = new Directory("folder");
            File file = new File("test.txt", 100);

            dir.addFile(file);

            assertFalse(dir.isEmpty());
            assertEquals(1, dir.getContents().size());
            assertTrue(dir.getContents().contains(file));
        }

        @Test
        @DisplayName("Adding file should set file's parent")
        void addingFileSetsParent() {
            Directory dir = new Directory("parent");
            File file = new File("child.txt", 100);

            dir.addFile(file);

            assertEquals(dir, file.getParent());
        }

        @Test
        @DisplayName("Directory size should be sum of contents")
        void directorySizeIsSumOfContents() {
            Directory dir = new Directory("folder");
            dir.addFile(new File("a.txt", 100));
            dir.addFile(new File("b.txt", 200));
            dir.addFile(new File("c.txt", 300));

            assertEquals(600, dir.getSize());
        }

        @Test
        @DisplayName("createFile should add file and return it")
        void createFileAddsAndReturns() {
            Directory dir = new Directory("folder");

            File created = dir.createFile("new.txt", 150);

            assertEquals("new.txt", created.getName());
            assertEquals(150, created.getSize());
            assertEquals(dir, created.getParent());
            assertTrue(dir.getContents().contains(created));
        }

        @Test
        @DisplayName("createDirectory should add directory and return it")
        void createDirectoryAddsAndReturns() {
            Directory dir = new Directory("parent");

            Directory created = dir.createDirectory("child");

            assertEquals("child", created.getName());
            assertEquals(dir, created.getParent());
            assertTrue(dir.getContents().contains(created));
        }
    }

    @Nested
    @DisplayName("Exercise 1c: Nested Structure")
    class NestedStructureTests {

        @Test
        @DisplayName("Nested directory size should be recursive")
        void nestedDirectorySizeIsRecursive() {
            Directory root = new Directory("root");
            Directory sub1 = root.createDirectory("sub1");
            Directory sub2 = root.createDirectory("sub2");

            root.createFile("root.txt", 100);
            sub1.createFile("sub1.txt", 200);
            sub2.createFile("sub2.txt", 300);

            Directory sub1sub = sub1.createDirectory("subsub");
            sub1sub.createFile("deep.txt", 400);

            // Total: 100 + 200 + 300 + 400 = 1000
            assertEquals(1000, root.getSize());
            assertEquals(600, sub1.getSize());  // 200 + 400
            assertEquals(300, sub2.getSize());
            assertEquals(400, sub1sub.getSize());
        }

        @Test
        @DisplayName("countFiles should count all files recursively")
        void countFilesIsRecursive() {
            Directory root = new Directory("root");
            root.createFile("file1.txt", 100);
            root.createFile("file2.txt", 100);

            Directory sub = root.createDirectory("sub");
            sub.createFile("file3.txt", 100);
            sub.createFile("file4.txt", 100);

            Directory subsub = sub.createDirectory("subsub");
            subsub.createFile("file5.txt", 100);

            assertEquals(5, root.countFiles());
            assertEquals(3, sub.countFiles());
            assertEquals(1, subsub.countFiles());
        }

        @Test
        @DisplayName("countDirectories should count all subdirectories recursively")
        void countDirectoriesIsRecursive() {
            Directory root = new Directory("root");
            Directory sub1 = root.createDirectory("sub1");
            Directory sub2 = root.createDirectory("sub2");
            sub1.createDirectory("sub1a");
            sub1.createDirectory("sub1b");
            sub2.createDirectory("sub2a");

            assertEquals(5, root.countDirectories());  // sub1, sub2, sub1a, sub1b, sub2a
            assertEquals(2, sub1.countDirectories());  // sub1a, sub1b
            assertEquals(1, sub2.countDirectories());  // sub2a
        }

        @Test
        @DisplayName("find should search recursively")
        void findSearchesRecursively() {
            Directory root = new Directory("root");
            Directory sub = root.createDirectory("subdir");
            File target = sub.createFile("target.txt", 100);

            Optional<FileSystemEntry> found = root.find("target.txt");

            assertTrue(found.isPresent());
            assertEquals(target, found.get());
        }
    }

    @Nested
    @DisplayName("Exercise 1d: Paths")
    class PathTests {

        @Test
        @DisplayName("Root directory path should be /")
        void rootPathIsSlash() {
            Directory root = new Directory("/");

            assertEquals("/", root.getPath());
        }

        @Test
        @DisplayName("Subdirectory path should include parent")
        void subdirectoryPathIncludesParent() {
            Directory root = new Directory("/");
            Directory home = root.createDirectory("home");
            Directory user = home.createDirectory("user");

            assertEquals("/home", home.getPath());
            assertEquals("/home/user", user.getPath());
        }

        @Test
        @DisplayName("File path should include full directory path")
        void filePathIncludesFullPath() {
            Directory root = new Directory("/");
            Directory home = root.createDirectory("home");
            Directory user = home.createDirectory("user");
            File file = user.createFile("document.txt", 100);

            assertEquals("/home/user/document.txt", file.getPath());
        }
    }

    @Nested
    @DisplayName("Exercise 1e: Real File System Example")
    class RealFileSystemExample {

        @Test
        @DisplayName("Unix-like file system structure")
        void unixLikeFileSystem() {
            // Build a realistic structure
            Directory root = new Directory("/");

            Directory home = root.createDirectory("home");
            Directory userDir = home.createDirectory("user");
            userDir.createFile("profile.txt", 100);
            Directory docs = userDir.createDirectory("documents");
            docs.createFile("report.pdf", 50000);
            docs.createFile("notes.txt", 1000);
            Directory pics = userDir.createDirectory("pictures");
            pics.createFile("photo.jpg", 2000000);

            Directory etc = root.createDirectory("etc");
            etc.createFile("config.conf", 500);

            Directory var = root.createDirectory("var");
            Directory log = var.createDirectory("log");
            log.createFile("system.log", 100000);

            // Verify structure
            assertEquals(2151600, root.getSize());
            assertEquals(6, root.countFiles());
            assertEquals(7, root.countDirectories());

            // Find files
            assertTrue(root.find("report.pdf").isPresent());
            assertTrue(root.find("pictures").isPresent());
            assertFalse(root.find("missing.txt").isPresent());

            // Verify paths
            Optional<FileSystemEntry> report = root.find("report.pdf");
            assertEquals("/home/user/documents/report.pdf", report.get().getPath());
        }
    }
}
