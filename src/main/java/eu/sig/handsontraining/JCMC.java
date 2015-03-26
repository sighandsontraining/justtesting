package eu.sig.handsontraining;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import eu.sig.handsontraining.filetree.FileTree;
import eu.sig.handsontraining.filetree.JavaFileFilter;
import eu.sig.handsontraining.filetree.JavaTestFileFilter;
import eu.sig.handsontraining.measurement.LinesOfCode;
import eu.sig.handsontraining.measurement.McCabe;
import eu.sig.handsontraining.measurement.MeasurementRunner;
import eu.sig.handsontraining.reporting.ReportGenerator;

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
        generateProductionReport(fileTree);
        generateTestReport(fileTree);
    }

    private static void generateProductionReport(FileTree fileTree) throws IOException {
        System.out.println("Production code");
        MeasurementRunner measurementRunner = new MeasurementRunner(fileTree, JavaFileFilter.INSTANCE);
        Map<String, Integer> locMeasurements = measurementRunner.runMeasurement(new LinesOfCode());
        Map<String, Integer> mccabeMeasurements = measurementRunner.runMeasurement(new McCabe());
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.addMeasurements(locMeasurements, LinesOfCode.METRIC_KEY);
        reportGenerator.addMeasurements(mccabeMeasurements, McCabe.METRIC_KEY);
        reportGenerator.generateReports(new File("metrics.csv"));
    }

    private static void generateTestReport(FileTree fileTree) throws IOException {
        System.out.println("Test code");
        MeasurementRunner measurementRunner = new MeasurementRunner(fileTree, JavaTestFileFilter.INSTANCE);
        Map<String, Integer> locMeasurements = measurementRunner.runMeasurement(new LinesOfCode());
        Map<String, Integer> mccabeMeasurements = measurementRunner.runMeasurement(new McCabe());
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.addMeasurements(locMeasurements, LinesOfCode.METRIC_KEY);
        reportGenerator.addMeasurements(mccabeMeasurements, McCabe.METRIC_KEY);
        reportGenerator.generateReports(new File("test-metrics.csv"));
    }

}