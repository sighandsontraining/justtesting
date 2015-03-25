package eu.sig.handsontraining.reporting;

import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import antlr.Token;
import eu.sig.handsontraining.filetree.FileTreeNode;
import eu.sig.handsontraining.measurement.Measurement;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class ReportGenerator {
    private final FileFilter fileFilter;

    public ReportGenerator(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    /**
     * Returns a measurement report that contains measurements on the file and the directory 
     * level for the given file tree.
     */
    public String createMeasurementReport(FileTreeNode node, Measurement measurement) throws IOException {
        StringBuffer report = new StringBuffer();
        List<Pair<String, Integer>> measurements = measure(node, measurement);
        for (Pair<String, Integer> entry : measurements) {
            report.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return report.toString();
    }

    private List<Pair<String, Integer>> measure(FileTreeNode node, Measurement measurement) throws IOException {
        List<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        int totalLinesOfCode = traverse(node, measurement, result);
        result.add(Pair.of("SYSTEM", totalLinesOfCode));
        return result;
    }

    private int traverse(FileTreeNode node, Measurement analysis, List<Pair<String, Integer>> measurementsList)
        throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            List<FileTreeNode> children = node.getChildren();
            for (FileTreeNode fileTreeNode : children) {
                result += traverse(fileTreeNode, analysis, measurementsList);
            }
            measurementsList.add(Pair.of(node.getFile().getAbsolutePath(), result));
        } else {
            result = visitFile(node, analysis, measurementsList);
        }
        return result;
    }

    private int visitFile(FileTreeNode node, Measurement analysis, List<Pair<String, Integer>> measurementsList)
        throws IOException {
        int result = 0;
        if (fileFilter.accept(node.getFile())) {
            String source = node.getSource();
            List<Token> tokens = JavaTokenizer.INSTANCE.tokenize(source, true);
            result = analysis.measure(tokens);
            measurementsList.add(Pair.of(node.getFile().getAbsolutePath(), result));
        }
        return result;
    }

}
