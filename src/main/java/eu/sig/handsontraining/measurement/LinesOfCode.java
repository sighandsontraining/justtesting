package eu.sig.handsontraining.measurement;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import antlr.Token;
import antlr.TokenStreamException;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.grammars.GeneratedJavaLexer;

import eu.sig.handsontraining.DoNothingCommentListener;

public class LinesOfCode implements Measurement {

    @Override
    public int measure(String pieceOfCode) {
        GeneratedJavaLexer lexer = new GeneratedJavaLexer(new StringReader(pieceOfCode));
        lexer.setCommentListener(new DoNothingCommentListener());
        Token nextToken = getNextToken(lexer);
        Set<Integer> lines = new HashSet<Integer>();
        while (nextToken != null && nextToken.getType() != antlr.Token.EOF_TYPE) {
            if (!isCommentToken(nextToken)) {
                lines.add(nextToken.getLine());
            }
            nextToken = getNextToken(lexer);
        }
        return lines.size();
    }

    private Token getNextToken(GeneratedJavaLexer lexer) {
        try {
            return lexer.nextToken();
        } catch (TokenStreamException e) {
            return null;
        }
    }

    private boolean isCommentToken(Token nextToken) {
        return nextToken.getType() == TokenTypes.BLOCK_COMMENT_BEGIN
            || nextToken.getType() == TokenTypes.SINGLE_LINE_COMMENT;
    }

}