package eu.sig.handsontraining.measurement;

import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import antlr.Token;
import eu.sig.handsontraining.filetree.FileTreeNode;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class MeasurementRunnerHelper {

    static Map<String, Integer> traverse(FileTreeNode rootNode, FileFilter fileFilter, Measurement measurement)
        throws IOException {
        Map<String, Integer> result = new HashMap<String, Integer>();
        int totalLinesOfCode = traverse(rootNode, fileFilter, measurement, result);
        result.put(SYSTEM_LEVEL, totalLinesOfCode);
        return result;

    }

    private static int traverse(FileTreeNode node, FileFilter fileFilter, Measurement analysis,
        Map<String, Integer> measurementMap)
        throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            List<FileTreeNode> children = node.getChildren();
            for (FileTreeNode fileTreeNode : children) {
                result += traverse(fileTreeNode, fileFilter, analysis, measurementMap);
            }
            measurementMap.put(node.getFile().getAbsolutePath(), result);
        } else {
            result = visitFile(node, fileFilter, analysis, measurementMap);
        }
        return result;
    }

    private static int visitFile(FileTreeNode node, FileFilter fileFilter, Measurement analysis,
        Map<String, Integer> measurementMap)
        throws IOException {
        int result = 0;
        if (fileFilter.accept(node.getFile())) {
            String source = node.getSource();
            List<Token> tokens = JavaTokenizer.INSTANCE.tokenize(source, true);
            result = analysis.measure(tokens);
            measurementMap.put(node.getFile().getAbsolutePath(), result);
        }
        return result;
    }

    public static final String SYSTEM_LEVEL = "SYSTEM";

}