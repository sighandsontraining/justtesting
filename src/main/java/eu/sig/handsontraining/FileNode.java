package eu.sig.handsontraining;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNode implements FileTreeNode {
    private final File path;

    public FileNode(File path) {
        this.path = path;
    }

    public File getPath() {
        return path;
    }

    @Override
    public List<FileTreeNode> getChildren() {
        return new ArrayList<FileTreeNode>();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}