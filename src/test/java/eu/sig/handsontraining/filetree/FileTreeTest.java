package eu.sig.handsontraining.filetree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.common.io.Files;

public class FileTreeTest {

    private void createTempFile(File parent, String filename) throws IOException {
        File tmpFile = new File(parent, filename);
        tmpFile.createNewFile();
        tmpFile.deleteOnExit();
    }

    @Test
    public void testEmptyDirectory() {
        File tempDir = Files.createTempDir();

        FileTree fileTree = new FileTree(tempDir);

        assertNotNull(fileTree);
        assertEquals(tempDir, fileTree.getRoot().getFile());
        assertEquals(0, fileTree.getRoot().getChildren().size());
    }

    @Test
    public void testDirectory() throws Exception {
        File tmpDir = Files.createTempDir();
        createTempFile(tmpDir, "Bar.java");
        createTempFile(tmpDir, "Foo.java");

        FileTree fileTree = new FileTree(tmpDir);

        List<FileTreeNode> children = fileTree.getRoot().getChildren();

        assertEquals(2, children.size());
        assertFalse(children.get(0).isDirectory());
        assertFalse(children.get(1).isDirectory());
    }

}