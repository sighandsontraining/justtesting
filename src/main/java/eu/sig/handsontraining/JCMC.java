package eu.sig.handsontraining;

import java.io.File;
import java.io.IOException;

import eu.sig.handsontraining.filetree.FileTree;
import eu.sig.handsontraining.measurement.LinesOfCode;

public class JCMC {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("usage: JCMC <directory>");
            System.exit(1);
        }
        File rootDirectory = new File(args[0]);
        if (!rootDirectory.isDirectory()) {
            System.out.println(rootDirectory.getAbsolutePath() + " is not a directory!");
        }
        FileTree fileTree = new FileTree(rootDirectory);
        System.out.println(ReportGenerator.createJavaMeasurementReport(fileTree, new LinesOfCode()));
    }

}