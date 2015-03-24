package eu.sig.handsontraining.filetree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryNode implements FileTreeNode {
    private final File path;
    private final List<FileTreeNode> children = new ArrayList<FileTreeNode>();

    DirectoryNode(File path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path.getAbsolutePath();
    }

    void addChild(FileTreeNode node) {
        children.add(node);
    }

    @Override
    public List<FileTreeNode> getChildren() {
        return children;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

}