package eu.sig.handsontraining.filetree;

import java.io.File;

public class FileTree {
    private final DirectoryNode root;

    public FileTree(File rootDirectory) {
        root = new DirectoryNode(rootDirectory);
        populateDirectoryStructure(root);
    }

    public DirectoryNode getRoot() {
        return root;
    }

    private void populateDirectoryStructure(DirectoryNode node) {
        File[] files = node.getFile().listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                DirectoryNode subDirectoryNode = new DirectoryNode(file);
                node.addChild(subDirectoryNode);
                populateDirectoryStructure(subDirectoryNode);
            } else {
                FileNode fileNode = new FileNode(file);
                node.addChild(fileNode);
            }
        }
    }

}