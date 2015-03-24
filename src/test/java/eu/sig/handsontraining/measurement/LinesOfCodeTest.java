package eu.sig.handsontraining.measurement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.sig.handsontraining.measurement.Measurement;

public class LinesOfCodeTest {

    @Test
    public void testCountLinesOfCodeEmpty() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure(""));
    }

    @Test
    public void testCountLinesOfCodeCommentOnly() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure("/* Hello world */"));
    }

    @Test
    public void testCountLinesOfCodeSingleLineCommentOnly() throws Exception {
        Measurement analysis = new LinesOfCode();

        assertEquals(0, analysis.measure("// Hello world"));
    }

    @Test
    public void testCountLinesOfCode() throws Exception {
        Measurement analysis = new LinesOfCode();
        String code = "";
        code += "package foo;\n";
        code += "class Bar {\n";
        code += "}\n";

        assertEquals(3, analysis.measure(code));
    }

}