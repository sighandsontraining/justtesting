package eu.sig.handsontraining.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import antlr.Token;

public class JavaTokenizerTest extends JavaTokenizer {

    @Test
    public void testEmtySource() {
        List<Token> tokens = new JavaTokenizer().tokenize("", false);

        assertTrue(tokens.isEmpty());
    }

    @Test
    public void testSingleToken() {
        List<Token> tokens = new JavaTokenizer().tokenize("class", false);

        assertEquals(1, tokens.size());
    }

}
