package eu.sig.handsontraining;

import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import antlr.Token;
import eu.sig.handsontraining.filetree.DirectoryNode;
import eu.sig.handsontraining.filetree.FileNode;
import eu.sig.handsontraining.filetree.FileTree;
import eu.sig.handsontraining.filetree.FileTreeNode;
import eu.sig.handsontraining.filetree.JavaFileFilter;
import eu.sig.handsontraining.measurement.Measurement;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;
import eu.sig.handsontraining.tokenizer.Tokenizer;

public class ReportGenerator {
    private static Tokenizer tokenizer = new JavaTokenizer();

    /**
     * Returns a measurement report that contains measurements on the file and the directory 
     * level for the given file tree.
     */
    public static String createJavaMeasurementReport(FileTree fileTree, Measurement measurement) throws IOException {
        StringBuffer report = new StringBuffer();
        int totalLinesOfCode = visitTree(fileTree.getRoot(), measurement, report, new JavaFileFilter());
        report.append("\n");
        report.append("Total lines of Java code: " + totalLinesOfCode + "\n");
        return report.toString();
    }

    private static int visitTree(FileTreeNode node, Measurement analysis, StringBuffer report, FileFilter fileFilter)
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

    private static int visitDirectory(DirectoryNode directoryNode, Measurement analysis, StringBuffer report,
        FileFilter fileFilter) throws IOException {
        int result = 0;
        List<FileTreeNode> children = directoryNode.getChildren();
        for (FileTreeNode child : children) {
            result += visitTree(child, analysis, report, fileFilter);
        }
        report.append(directoryNode.getFile().getAbsolutePath() + ": " + result + "\n");
        return result;
    }

    private static int visitFile(FileNode fileNode, Measurement analysis, StringBuffer report, FileFilter fileFilter)
        throws IOException {
        int result = 0;
        if (fileFilter.accept(fileNode.getFile())) {
            String source = fileNode.getSource();
            List<Token> tokens = tokenizer.tokenize(source, true);
            result = analysis.measure(tokens);
            report.append(fileNode.getFile().getAbsolutePath() + ": " + result + "\n");
        }
        return result;
    }

}
