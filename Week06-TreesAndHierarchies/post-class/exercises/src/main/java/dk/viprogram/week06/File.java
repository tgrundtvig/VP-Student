package dk.viprogram.week06;

import java.util.Optional;

/**
 * Represents a file in the file system (a leaf node).
 *
 * Exercise 1a: Implement this class to pass all the FileSystemTest tests.
 *
 * A File is a leaf node - it has no children.
 * It stores a name, size, and reference to its parent directory.
 */
public class File implements FileSystemEntry {

    private String name;
    private long size;
    private Directory parent;

    /**
     * Creates a new file with the given name and size.
     *
     * @param name the file name
     * @param size the file size in bytes
     */
    public File(String name, long size) {
        // TODO: Initialize name and size
        // TODO: Parent starts as null (set when added to directory)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getName() {
        // TODO: Return the file name
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public long getSize() {
        // TODO: Return the file size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isDirectory() {
        // TODO: Files are not directories, return false
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public FileSystemEntry getParent() {
        // TODO: Return the parent directory
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public String getPath() {
        // TODO: Build the full path from root to this file
        // Example: "/home/user/file.txt"
        // Hint: If parent is null, return "/" + name
        //       Otherwise, return parent.getPath() + "/" + name
        //       Special case: if parent is root ("/"), don't add extra "/"
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public Optional<FileSystemEntry> find(String name) {
        // TODO: If this file's name equals the search name, return this
        // TODO: Otherwise return Optional.empty()
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void printTree(String indent) {
        // TODO: Print the file with indentation
        // Format: indent + name + " (" + size + " bytes)"
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Sets the parent directory. Package-private for use by Directory.
     */
    void setParent(Directory parent) {
        this.parent = parent;
    }
}
