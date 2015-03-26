package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {
    public static final FileFilter INSTANCE = new JavaFileFilter();

    private JavaFileFilter() {}

    @Override
    public boolean accept(File pathname) {
        String filename = pathname.getAbsolutePath().toLowerCase();
        if (!filename.endsWith(".java")) {
            return false;
        }
        if (JavaTestFileFilter.isTestFile(pathname)) {
            return false;
        }
        return true;
    }
}