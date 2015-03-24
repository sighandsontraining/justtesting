package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.IOException;
import java.util.List;

import eu.sig.handsontraining.measurement.Measurement;

public class FileTree {
    private final DirectoryNode root;

    public FileTree(String path) {
        this(new File(path));
    }

    public FileTree(File rootDirectory) {
        root = new DirectoryNode(rootDirectory);
        populateDirectoryStructure(root);
    }

    public DirectoryNode getRoot() {
        return root;
    }

    private void populateDirectoryStructure(DirectoryNode node) {
        File[] files = new File(node.getPath()).listFiles();
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

    public String createMeasurementReport(Measurement analysis) throws IOException {
        StringBuffer report = new StringBuffer();
        visitTree(root, analysis, report);
        return report.toString();
    }

    private int visitTree(FileTreeNode node, Measurement analysis, StringBuffer report) throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            DirectoryNode directoryNode = ((DirectoryNode)node);
            List<FileTreeNode> children = directoryNode.getChildren();
            for (FileTreeNode child : children) {
                result += visitTree(child, analysis, report);
            }
        } else {
            result = analysis.measure(((FileNode)node).getSource());
        }
        report.append(node.getPath() + ": " + result + "\n");
        return result;
    }

}