package eu.sig.handsontraining.reporting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import eu.sig.handsontraining.measurement.LinesOfCode;
import eu.sig.handsontraining.measurement.McCabe;
import eu.sig.handsontraining.measurement.MeasurementRunner;

public class ReportGeneratorHelper {

    private static class MetricListComparator implements Comparator<Pair<String, Integer>> {

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }

    }

    static void displayTop10(Map<String, Map<String, Integer>> metricsMap, String metricKey) {
        System.out.println(metricKey + " - Top 10");
        List<Pair<String, Integer>> metricList = getMetricsAsList(metricsMap, metricKey);
        for (int i = 0; i < (metricList.size() > 10 ? 10 : metricList.size()); i++) {
            Pair<String, Integer> entry = metricList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static List<Pair<String, Integer>> getMetricsAsList(Map<String, Map<String, Integer>> metricsMap,
        String metricKey) {
        List<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        Set<String> keySet = metricsMap.keySet();
        for (String key : keySet) {
            if (key.endsWith(".java") && metricsMap.get(key).containsKey(metricKey)) {
                result.add(Pair.of(key, metricsMap.get(key).get(metricKey)));
            }
        }
        Collections.sort(result, new MetricListComparator());
        return result;
    }

    static void displayCodebaseValues(Map<String, Map<String, Integer>> metricsMap) {
        Map<String, Integer> systemMetrics = metricsMap.get(MeasurementRunner.SYSTEM_LEVEL);
        Set<String> metricKeys = systemMetrics.keySet();
        System.out.println("Total codebase metrics:");
        for (String metricKey : metricKeys) {
            System.out.println(String.format("%15.15s", metricKey) + ": "
                + String.format("%7s", systemMetrics.get(metricKey)));
        }
    }

    static void generateCsvFile(Map<String, Map<String, Integer>> metricsMap) throws IOException {
        File outputFile = new File("metrics.csv");
        FileWriter fileWriter = new FileWriter(outputFile);
        Set<String> keys = metricsMap.keySet();
        for (String key : keys) {
            fileWriter.write(key + "," + metricsMap.get(key).get(LinesOfCode.METRIC_KEY) + ","
                + metricsMap.get(key).get(McCabe.METRIC_KEY) + "\n");
        }
        fileWriter.close();
    }

}