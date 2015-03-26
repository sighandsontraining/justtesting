package eu.sig.handsontraining.filetree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class JavaFileFilterTest {

    @Test
    public void testJavaFile() throws Exception {
        assertTrue(JavaFileFilter.INSTANCE.accept(new File("Main.java")));
        assertTrue(JavaFileFilter.INSTANCE.accept(new File("src/main/java/Main.java")));
    }

    @Test
    public void testJavaTestFile() throws Exception {
        assertFalse(JavaFileFilter.INSTANCE.accept(new File("MainTest.java")));
        assertFalse(JavaFileFilter.INSTANCE.accept(new File("src/test/java/Main.java")));
    }

}
