package eu.sig.handsontraining.reporting;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReportGenerator {
    private Map<String, Map<String, Integer>> metricsMap = new HashMap<String, Map<String, Integer>>();
    private Set<String> metricKeys = new HashSet<String>();

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

    public void generateReports() throws IOException {
        ReportGeneratorHelper.generateCsvFile(metricsMap);
        for (String metric : metricKeys) {
            ReportGeneratorHelper.displayTop10(metricsMap, metric);
        }
        ReportGeneratorHelper.displayCodebaseValues(metricsMap);
    }
}