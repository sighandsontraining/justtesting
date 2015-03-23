package eu.sig.handsontraining;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import antlr.Token;
import antlr.TokenStreamException;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.grammars.GeneratedJavaLexer;

public class JCMC {

    public int countLinesOfCode(String pieceOfCode) throws TokenStreamException {
        GeneratedJavaLexer lexer = new GeneratedJavaLexer(new StringReader(pieceOfCode));
        lexer.setCommentListener(new JCMCCommentListener());
        Token nextToken = lexer.nextToken();
        Set<Integer> lines = new HashSet<Integer>();
        while (nextToken != null && nextToken.getType() != antlr.Token.EOF_TYPE) {
            if (!isCommentToken(nextToken)) {
                lines.add(nextToken.getLine());
            }
            nextToken = lexer.nextToken();
        }
        return lines.size();
    }

    private boolean isCommentToken(Token nextToken) {
        return nextToken.getType() == TokenTypes.BLOCK_COMMENT_BEGIN
            || nextToken.getType() == TokenTypes.SINGLE_LINE_COMMENT;
    }

}