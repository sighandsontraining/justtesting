package eu.sig.handsontraining;

import java.io.File;

public class FileTree {
    private final DirectoryNode root;

    public FileTree(String path) {
        File rootDirectory = new File(path);
        root = new DirectoryNode(rootDirectory);
        walkDirectoryStructure(root);
    }

    public DirectoryNode getRoot() {
        return root;
    }

    private void walkDirectoryStructure(DirectoryNode node) {
        File[] files = node.getPath().listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                DirectoryNode subDirectoryNode = new DirectoryNode(file);
                node.addChild(subDirectoryNode);
                walkDirectoryStructure(subDirectoryNode);
            } else {
                FileNode fileNode = new FileNode(file);
                node.addChild(fileNode);
            }
        }
    }

}
