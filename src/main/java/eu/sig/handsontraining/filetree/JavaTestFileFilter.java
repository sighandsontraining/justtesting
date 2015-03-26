package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.FileFilter;

public class JavaTestFileFilter implements FileFilter {
    public static final FileFilter INSTANCE = new JavaTestFileFilter();

    private JavaTestFileFilter() {}

    @Override
    public boolean accept(File file) {
        String filename = file.getAbsolutePath().toLowerCase();
        if (!filename.endsWith(".java")) {
            return false;
        }
        return isTestFile(file);
    }

    public static boolean isTestFile(File file) {
        String filename = file.getAbsolutePath().toLowerCase();
        if (filename.contains(File.separator + "test" + File.separator) || filename.endsWith("test.java")) {
            return true;
        }
        return false;
    }
}