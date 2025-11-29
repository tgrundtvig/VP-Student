package dk.viprogram.week06;

import java.util.List;
import java.util.Optional;

/**
 * Represents an entry in a file system - either a file or a directory.
 *
 * This is an example of the Composite pattern: both File and Directory
 * implement the same interface, allowing uniform treatment.
 *
 * Exercise 1: This interface is already complete. Study it to understand
 * how the Composite pattern works for file systems.
 */
public interface FileSystemEntry {

    /**
     * Returns the name of this entry (file name or directory name).
     *
     * @return the name
     */
    String getName();

    /**
     * Returns the size of this entry in bytes.
     * For files: the file size
     * For directories: the total size of all contents (recursive)
     *
     * @return the size in bytes
     */
    long getSize();

    /**
     * Returns true if this entry is a directory.
     *
     * @return true for directories, false for files
     */
    boolean isDirectory();

    /**
     * Returns the parent directory of this entry, or null for root.
     *
     * @return the parent directory, or null
     */
    FileSystemEntry getParent();

    /**
     * Returns the full path from root to this entry.
     * Example: "/home/user/documents/file.txt"
     *
     * @return the full path
     */
    String getPath();

    /**
     * Searches for an entry with the given name in this entry and its descendants.
     * For files, only checks if the name matches this file.
     * For directories, searches recursively.
     *
     * @param name the name to search for
     * @return Optional containing the found entry, or empty if not found
     */
    Optional<FileSystemEntry> find(String name);

    /**
     * Prints this entry and all its descendants in a tree-like format.
     * Example output:
     * /
     *   home/
     *     user/
     *       file.txt (100 bytes)
     *
     * @param indent the current indentation string
     */
    void printTree(String indent);
}
