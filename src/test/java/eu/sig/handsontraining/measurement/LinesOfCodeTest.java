package eu.sig.handsontraining.measurement;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import antlr.Token;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class LinesOfCodeTest {

    private List<Token> tokenize(String pieceOfCode) {
        return JavaTokenizer.INSTANCE.tokenize(pieceOfCode, true);
    }

    @Test
    public void testCountLinesOfCodeEmpty() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure(tokenize("")));
    }

    @Test
    public void testCountLinesOfCodeCommentOnly() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure(tokenize("/* Hello world */")));
    }

    @Test
    public void testCountLinesOfCodeSingleLineCommentOnly() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure(tokenize("// Hello world")));
    }

    @Test
    public void testCountLinesOfCode() throws Exception {
        Measurement analysis = new LinesOfCode();
        String code = "";
        code += "package foo;\n";
        code += "class Bar {\n";
        code += "}\n";

        assertEquals(3, analysis.measure(tokenize(code)));
    }

}