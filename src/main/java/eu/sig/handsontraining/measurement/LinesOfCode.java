package eu.sig.handsontraining.measurement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antlr.Token;

public class LinesOfCode implements Measurement {
    public static final String METRIC_KEY = "Lines of Code";

    @Override
    public int measure(List<Token> tokens) {
        Set<Integer> lines = new HashSet<Integer>();
        for (Token token : tokens) {
            lines.add(token.getLine());
        }
        return lines.size();
    }

}