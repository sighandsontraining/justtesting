package eu.sig.handsontraining.filetree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryNode implements FileTreeNode {
    private final File file;
    private final List<FileTreeNode> children = new ArrayList<FileTreeNode>();

    DirectoryNode(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
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