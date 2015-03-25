package eu.sig.handsontraining.measurement;

import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import antlr.Token;
import eu.sig.handsontraining.filetree.FileTree;
import eu.sig.handsontraining.filetree.FileTreeNode;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class MeasurementRunner {
    public static final String SYSTEM_LEVEL = "SYSTEM";
    private final FileFilter fileFilter;
    private final FileTreeNode rootNode;

    public MeasurementRunner(FileTree fileTree, FileFilter fileFilter) {
        this.rootNode = fileTree.getRoot();
        this.fileFilter = fileFilter;
    }

    public Map<String, Integer> runMeasurement(Measurement measurement)
        throws IOException {
        Map<String, Integer> result = new HashMap<String, Integer>();
        int totalLinesOfCode = traverse(rootNode, measurement, result);
        result.put(SYSTEM_LEVEL, totalLinesOfCode);
        return result;
    }

    private int traverse(FileTreeNode node, Measurement analysis, Map<String, Integer> measurementMap)
        throws IOException {
        int result = 0;
        if (node.isDirectory()) {
            List<FileTreeNode> children = node.getChildren();
            for (FileTreeNode fileTreeNode : children) {
                result += traverse(fileTreeNode, analysis, measurementMap);
            }
            measurementMap.put(node.getFile().getAbsolutePath(), result);
        } else {
            result = visitFile(node, analysis, measurementMap);
        }
        return result;
    }

    private int visitFile(FileTreeNode node, Measurement analysis, Map<String, Integer> measurementMap)
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

}
