package eu.sig.handsontraining.filetree;

import java.io.File;
import java.util.List;

public interface FileTreeNode {
    File getFile();

    boolean isDirectory();

    List<FileTreeNode> getChildren();
}