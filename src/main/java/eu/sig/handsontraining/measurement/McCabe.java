package eu.sig.handsontraining.measurement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antlr.Token;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class McCabe implements Measurement {
    public static final String METRIC_KEY = "McCabe";

    private static final Set<Integer> MCCABE_TOKENS = new HashSet<Integer>();
    static {
        MCCABE_TOKENS.add(TokenTypes.LITERAL_IF);
        MCCABE_TOKENS.add(TokenTypes.QUESTION);
        MCCABE_TOKENS.add(TokenTypes.LITERAL_CASE);
        MCCABE_TOKENS.add(TokenTypes.LITERAL_WHILE);
        MCCABE_TOKENS.add(TokenTypes.LITERAL_FOR);
        MCCABE_TOKENS.add(TokenTypes.LITERAL_CATCH);
        MCCABE_TOKENS.add(TokenTypes.LAND);
        MCCABE_TOKENS.add(TokenTypes.LOR);
    }

    @Override
    public int measure(List<Token> tokens) {
        int result = 1;
        for (Token token : tokens) {
            if (MCCABE_TOKENS.contains(token.getType())) {
                if (token.getType() == TokenTypes.QUESTION && previousTokenIsLessThan(tokens, token)) {
                    continue;
                } else {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean previousTokenIsLessThan(List<Token> tokens, Token token) {
        int index = tokens.indexOf(token);
        if (index > 0 && tokens.get(index - 1).getType() == TokenTypes.LT) {
            return true;
        }
        return false;
    }

}