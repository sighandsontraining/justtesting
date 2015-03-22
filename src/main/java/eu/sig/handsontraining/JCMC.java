package eu.sig.handsontraining;

import java.io.StringReader;

import antlr.Token;
import antlr.TokenStreamException;

import com.puppycrawl.tools.checkstyle.grammars.GeneratedJavaLexer;

public class JCMC {

    public int countLinesOfCode(String pieceOfCode) throws TokenStreamException {
        GeneratedJavaLexer lexer = new GeneratedJavaLexer(new StringReader(pieceOfCode));
        // Hint: ANTLR needs a comment listener! 
        Token nextToken = lexer.nextToken();

        while (nextToken != null && nextToken.getType() != antlr.Token.EOF_TYPE) {
            //TODO: Count lines of code
            nextToken = lexer.nextToken();
        }
        //TODO: Return actual lines of code
        return 0;
    }

}