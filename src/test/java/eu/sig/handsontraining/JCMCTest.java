package eu.sig.handsontraining;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JCMCTest {

    @Test
    public void testCountLinesOfCodeEmpty() throws Exception {
        JCMC jcmc = new JCMC();

        assertEquals(0, jcmc.countLinesOfCode(""));
    }

    @Test
    public void testCountLinesOfCodeCommentOnly() throws Exception {
        JCMC jcmc = new JCMC();

        // assertEquals(0, jcmc.countLinesOfCode("/* Hello world */"));
    }

    @Test
    public void testCountLinesOfCodeSingleLineCommentOnly() throws Exception {
        JCMC jcmc = new JCMC();

        // assertEquals(0, jcmc.countLinesOfCode("// Hello world"));
    }

    @Test
    public void testCountLinesOfCode() throws Exception {
        JCMC jcmc = new JCMC();
        String code = "";
        code += "package foo;\n";
        code += "class Bar {\n";
        code += "}\n";

        //        assertEquals(3, jcmc.countLinesOfCode(code));
    }

}