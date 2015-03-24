package eu.sig.handsontraining.filetree;

public interface FileTreeVisitor {

    void visitDirectory(DirectoryNode directory);

    void visitFile(FileNode file);
}
