package dk.viprogram.week06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a directory in the file system (a branch node).
 *
 * Exercise 1b: Implement this class to pass all the FileSystemTest tests.
 *
 * A Directory can contain files and other directories.
 * Its size is the sum of all its contents (recursive).
 */
public class Directory implements FileSystemEntry {

    private String name;
    private Directory parent;
    private List<FileSystemEntry> contents;

    /**
     * Creates a new directory with the given name.
     *
     * @param name the directory name
     */
    public Directory(String name) {
        // TODO: Initialize name
        // TODO: Initialize contents as empty list
        // TODO: Parent starts as null
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getName() {
        // TODO: Return the directory name
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public long getSize() {
        // TODO: Return the total size of all contents
        // This is the sum of getSize() for each entry in contents
        // (This is recursive - directories sum their contents too)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isDirectory() {
        // TODO: Directories are directories, return true
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public FileSystemEntry getParent() {
        // TODO: Return the parent directory
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getPath() {
        // TODO: Build the full path from root to this directory
        // Example: "/home/user"
        // Hint: If parent is null, this is root - return "/"
        //       Otherwise, build path like File does, but handle root specially
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public Optional<FileSystemEntry> find(String name) {
        // TODO: Check if this directory's name matches
        // TODO: If not, search each entry in contents recursively
        // TODO: Return first match found, or Optional.empty()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void printTree(String indent) {
        // TODO: Print this directory name with trailing "/"
        // TODO: Then print each entry with increased indentation
        // Format: indent + name + "/"
        // Children: use indent + "  " (two spaces)
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns an unmodifiable list of this directory's contents.
     *
     * @return the contents
     */
    public List<FileSystemEntry> getContents() {
        // TODO: Return unmodifiable view of contents
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Adds a file to this directory.
     *
     * @param file the file to add
     */
    public void addFile(File file) {
        // TODO: Add the file to contents
        // TODO: Set the file's parent to this directory
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Adds a subdirectory to this directory.
     *
     * @param directory the directory to add
     */
    public void addDirectory(Directory directory) {
        // TODO: Add the directory to contents
        // TODO: Set the directory's parent to this directory
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Creates and adds a new file with the given name and size.
     *
     * @param name the file name
     * @param size the file size
     * @return the newly created file
     */
    public File createFile(String name, long size) {
        // TODO: Create a new File with name and size
        // TODO: Add it to this directory
        // TODO: Return the new file
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Creates and adds a new subdirectory with the given name.
     *
     * @param name the directory name
     * @return the newly created directory
     */
    public Directory createDirectory(String name) {
        // TODO: Create a new Directory with name
        // TODO: Add it to this directory
        // TODO: Return the new directory
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns true if this directory is empty.
     *
     * @return true if no contents
     */
    public boolean isEmpty() {
        // TODO: Return true if contents is empty
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Counts the total number of files in this directory and all subdirectories.
     *
     * @return total file count
     */
    public int countFiles() {
        // TODO: Count files recursively
        // For each entry: if file, add 1; if directory, add directory.countFiles()
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Counts the total number of directories in this directory and all subdirectories.
     * Does not count this directory itself.
     *
     * @return total subdirectory count
     */
    public int countDirectories() {
        // TODO: Count directories recursively
        // For each entry: if directory, add 1 + directory.countDirectories()
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Sets the parent directory. Package-private for use by addDirectory.
     */
    void setParent(Directory parent) {
        this.parent = parent;
    }
}
