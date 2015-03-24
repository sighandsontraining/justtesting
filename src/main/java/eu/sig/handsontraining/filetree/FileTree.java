package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.FileFilter;
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

    public String createJavaMeasurementReport(Measurement analysis) throws IOException {
        StringBuffer report = new StringBuffer();
        int totalLinesOfCode = visitTree(root, analysis, report, new JavaFileFilter());
        report.append("\n");
        report.append("Total lines of Java code: " + totalLinesOfCode + "\n");
        return report.toString();
    }

    private int visitTree(FileTreeNode node, Measurement analysis, StringBuffer report, FileFilter fileFilter)
        throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            DirectoryNode directoryNode = ((DirectoryNode)node);
            result = visitDirectory(directoryNode, analysis, report, fileFilter);
        } else {
            FileNode fileNode = ((FileNode)node);
            result = visitFile(fileNode, analysis, report, fileFilter);
        }
        return result;
    }

    private int visitDirectory(DirectoryNode directoryNode, Measurement analysis, StringBuffer report,
        FileFilter fileFilter) throws IOException {
        int result = 0;
        List<FileTreeNode> children = directoryNode.getChildren();
        for (FileTreeNode child : children) {
            result += visitTree(child, analysis, report, fileFilter);
        }
        report.append(directoryNode.getPath() + ": " + result + "\n");
        return result;
    }

    private int visitFile(FileNode fileNode, Measurement analysis, StringBuffer report, FileFilter fileFilter)
        throws IOException {
        int result = 0;
        if (fileFilter.accept(fileNode.getFile())) {
            result = analysis.measure(fileNode.getSource());
            report.append(fileNode.getPath() + ": " + result + "\n");
        }
        return result;
    }

}