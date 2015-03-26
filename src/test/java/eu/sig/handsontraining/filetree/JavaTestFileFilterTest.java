package eu.sig.handsontraining.filetree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;


public class JavaTestFileFilterTest {

    @Test
    public void testJavaTestFile() throws Exception {
        assertTrue(JavaTestFileFilter.INSTANCE.accept(new File("MainTest.java")));
        assertTrue(JavaTestFileFilter.INSTANCE.accept(new File("src/test/java/Main.java")));
    }

    @Test
    public void testJavaFile() throws Exception {
        assertFalse(JavaTestFileFilter.INSTANCE.accept(new File("Main.java")));
        assertFalse(JavaTestFileFilter.INSTANCE.accept(new File("src/main/java/Main.java")));
    }

}
