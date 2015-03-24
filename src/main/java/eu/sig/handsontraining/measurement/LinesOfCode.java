package eu.sig.handsontraining.measurement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antlr.Token;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class LinesOfCode implements Measurement {

    @Override
    public int measure(String pieceOfCode) {
        List<Token> tokens = JavaTokenizer.tokenize(pieceOfCode, true);
        Set<Integer> lines = new HashSet<Integer>();
        for (Token token : tokens) {
            lines.add(token.getLine());
        }
        return lines.size();
    }

}