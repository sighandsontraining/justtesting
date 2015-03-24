package eu.sig.handsontraining.reporting;

import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import antlr.Token;
import eu.sig.handsontraining.filetree.FileTreeNode;
import eu.sig.handsontraining.filetree.JavaFileFilter;
import eu.sig.handsontraining.measurement.Measurement;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class ReportGenerator {

    /**
     * Returns a measurement report that contains measurements on the file and the directory 
     * level for the given file tree.
     */
    public static String createJavaMeasurementReport(FileTreeNode node, Measurement measurement) throws IOException {
        StringBuffer report = new StringBuffer();
        int totalLinesOfCode = traverse(node, measurement, report, JavaFileFilter.INSTANCE);
        report.append("\n");
        report.append("Total lines of Java code: " + totalLinesOfCode + "\n");
        return report.toString();
    }

    private static int traverse(FileTreeNode node, Measurement analysis, StringBuffer report, FileFilter fileFilter)
        throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            List<FileTreeNode> children = node.getChildren();
            for (FileTreeNode fileTreeNode : children) {
                result += traverse(fileTreeNode, analysis, report, fileFilter);
            }
        } else {
            result = visitFile(node, analysis, report, fileFilter);
        }
        return result;
    }

    private static int visitFile(FileTreeNode node, Measurement analysis, StringBuffer report, FileFilter fileFilter)
        throws IOException {
        int result = 0;
        if (fileFilter.accept(node.getFile())) {
            String source = node.getSource();
            List<Token> tokens = JavaTokenizer.INSTANCE.tokenize(source, true);
            result = analysis.measure(tokens);
            report.append(node.getFile().getAbsolutePath() + ": " + result + "\n");
        }
        return result;
    }

}
