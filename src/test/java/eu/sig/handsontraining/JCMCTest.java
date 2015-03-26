package eu.sig.handsontraining;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.google.common.io.Files;

public class JCMCTest {

    private void createTempFile(File parent, String name, String contents) throws IOException {
        File tmpFile = new File(parent, name);
        tmpFile.deleteOnExit();
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(contents);
        writer.close();
    }

    @Test
    public void testSmallCodebase() throws Exception {
        File root = Files.createTempDir();
        File srcDir = new File(root, "src");
        srcDir.mkdir();
        createTempFile(srcDir, "Bar.java", "public class Bar { }");
        createTempFile(srcDir, "Foo.java", "public class Foo { }");

        JCMC.main(new String[] {root.getAbsolutePath()});

        assertTrue(new File("metrics.csv").exists());
    }

    @Test
    public void testNonValidJavaCode() throws Exception {
        File root = Files.createTempDir();
        File srcDir = new File(root, "src");
        srcDir.mkdir();
        createTempFile(srcDir, "Bar.java", "publiccc ccclass Bar { }");
        createTempFile(srcDir, "Foo.java", "public class Foo {");

        JCMC.main(new String[] {root.getAbsolutePath()});

        assertTrue(new File("metrics.csv").exists());
    }

}