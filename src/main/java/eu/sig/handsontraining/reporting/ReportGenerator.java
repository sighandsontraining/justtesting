package eu.sig.handsontraining.reporting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private Map<String, Map<String, Integer>> metricsMap = new HashMap<String, Map<String, Integer>>();
    private List<String> metricKeys = new ArrayList<String>();

    public void addMeasurements(Map<String, Integer> measurements, String metricKey) {
        for (String key : measurements.keySet()) {
            if (metricsMap.containsKey(key)) {
                metricsMap.get(key).put(metricKey, measurements.get(key));
            } else {
                HashMap<String, Integer> keyValueMap = new HashMap<String, Integer>();
                keyValueMap.put(metricKey, measurements.get(key));
                metricsMap.put(key, keyValueMap);
            }
        }
        metricKeys.add(metricKey);
    }

    public void generateReports(File outputFile) throws IOException {
        ReportGeneratorHelper.generateCsvFile(metricsMap, metricKeys, outputFile);
        for (String metric : metricKeys) {
            ReportGeneratorHelper.displayTop10(metricsMap, metric);
        }
        ReportGeneratorHelper.displayCodebaseValues(metricsMap);
    }
}