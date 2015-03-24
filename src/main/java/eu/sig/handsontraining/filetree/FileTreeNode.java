package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileTreeNode {
    boolean isDirectory();

    File getFile();

    String getSource() throws IOException;

    List<FileTreeNode> getChildren();
}