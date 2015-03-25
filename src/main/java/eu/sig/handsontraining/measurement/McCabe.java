package eu.sig.handsontraining.measurement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antlr.Token;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class McCabe implements Measurement {

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
                result++;
            }
        }
        return result;
    }

}