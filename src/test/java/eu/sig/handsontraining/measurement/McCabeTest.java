package eu.sig.handsontraining.measurement;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import antlr.Token;
import eu.sig.handsontraining.tokenizer.JavaTokenizer;

public class McCabeTest {

    private List<Token> tokenize(String source) {
        return JavaTokenizer.INSTANCE.tokenize(source, true);
    }

    @Test
    public void testNoBranch() {
        Measurement measurement = new McCabe();

        assertEquals(1, measurement.measure(tokenize("System.out.prinln(\"Hello world!\";")));
    }

    @Test
    public void testSimpleIfElse() {
        Measurement measurement = new McCabe();
        String code = "";
        code += "if (foo) {\n";
        code += "} else {\n";
        code += "}\n";

        assertEquals(2, measurement.measure(tokenize(code)));
    }

    @Test
    public void testSimpleLoop() {
        Measurement measurement = new McCabe();
        String code = "";
        code += "for (foo: Foo.getFoos()) {\n";
        code += "}\n";

        assertEquals(2, measurement.measure(tokenize(code)));
    }

    @Test
    public void testDoNotCountGenericsQuestionmark() throws Exception {
        Measurement measurement = new McCabe();

        assertEquals(1, measurement.measure(tokenize("Class<?> clazz")));
    }

}