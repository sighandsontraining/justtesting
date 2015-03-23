package eu.sig.handsontraining;

import java.util.List;

public interface FileTreeNode {

    boolean isDirectory();

    List<FileTreeNode> getChildren();

}