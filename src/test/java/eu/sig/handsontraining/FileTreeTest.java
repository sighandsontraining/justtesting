package eu.sig.handsontraining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.google.common.io.Files;

public class FileTreeTest {

    @Test
    public void testEmptyDirectory() {
        File tempDir = Files.createTempDir();

        FileTree fileTree = new FileTree(tempDir.getAbsolutePath());

        assertNotNull(fileTree);
        assertEquals(tempDir, fileTree.getRoot().getPath());
        assertEquals(0, fileTree.getRoot().getChildren().size());
    }

}
