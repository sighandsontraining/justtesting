package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

public class FileNode implements FileTreeNode {
    private final File file;

    FileNode(File file) {
        this.file = file;
    }

    @Override
    public String getPath() {
        return file.getAbsolutePath();
    }

    @Override
    public List<FileTreeNode> getChildren() {
        throw new NotImplementedException("A file has no children!");
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public String getSource() throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}