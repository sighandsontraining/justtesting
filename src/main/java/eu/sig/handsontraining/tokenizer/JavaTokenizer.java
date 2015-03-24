package eu.sig.handsontraining.tokenizer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import antlr.Token;
import antlr.TokenStreamException;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.grammars.GeneratedJavaLexer;

public class JavaTokenizer implements Tokenizer {
    public static final Tokenizer INSTANCE = new JavaTokenizer();

    private JavaTokenizer() {}

    public List<Token> tokenize(String pieceOfCode, boolean filterComments) {
        List<Token> result = new ArrayList<Token>();
        GeneratedJavaLexer lexer = new GeneratedJavaLexer(new StringReader(pieceOfCode));
        lexer.setCommentListener(new DoNothingCommentListener());
        Token nextToken = getNextToken(lexer);
        while (nextToken != null && nextToken.getType() != antlr.Token.EOF_TYPE) {
            if (filterComments) {
                if (!isCommentToken(nextToken)) {
                    result.add(nextToken);
                }
            } else {
                result.add(nextToken);
            }
            nextToken = getNextToken(lexer);
        }
        return result;
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