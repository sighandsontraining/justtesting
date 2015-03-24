package eu.sig.handsontraining.filetree;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {
    public static final FileFilter INSTANCE = new JavaFileFilter();

    private JavaFileFilter() {}

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}