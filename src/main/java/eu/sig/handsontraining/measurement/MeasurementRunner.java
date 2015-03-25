package eu.sig.handsontraining.measurement;

import java.io.FileFilter;
import java.io.IOException;
import java.util.Map;

import eu.sig.handsontraining.filetree.FileTree;
import eu.sig.handsontraining.filetree.FileTreeNode;

public class MeasurementRunner {
    private final FileFilter fileFilter;
    private final FileTreeNode rootNode;

    public MeasurementRunner(FileTree fileTree, FileFilter fileFilter) {
        this.rootNode = fileTree.getRoot();
        this.fileFilter = fileFilter;
    }

    public Map<String, Integer> runMeasurement(Measurement measurement) throws IOException {
        return MeasurementRunnerHelper.traverse(rootNode, fileFilter, measurement);
    }

}