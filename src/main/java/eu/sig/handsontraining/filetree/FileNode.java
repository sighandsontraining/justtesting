package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileNode implements FileTreeNode {
    private final File file;

    FileNode(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public List<FileTreeNode> getChildren() {
        return new ArrayList<FileTreeNode>();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public String getSource() throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

}