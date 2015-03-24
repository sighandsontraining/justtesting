package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

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
    public String getSource() throws IOException {
        throw new NotImplementedException("A directory has no source!");
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

}