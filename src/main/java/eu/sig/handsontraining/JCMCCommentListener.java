package eu.sig.handsontraining;

import com.puppycrawl.tools.checkstyle.grammars.CommentListener;

public class JCMCCommentListener implements CommentListener {

    @Override
    public void reportSingleLineComment(String type, int startLineNo, int startColNo) {}

    @Override
    public void reportBlockComment(String type, int startLineNo, int startColNo, int endLineNo, int endColNo) {}

}