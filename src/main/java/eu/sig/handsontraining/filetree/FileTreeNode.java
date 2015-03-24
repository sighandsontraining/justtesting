package eu.sig.handsontraining.filetree;

import java.util.List;

public interface FileTreeNode {
    String getPath();

    boolean isDirectory();

    List<FileTreeNode> getChildren();
}