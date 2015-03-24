package eu.sig.handsontraining.tokenizer;

import java.util.List;

import antlr.Token;

public interface Tokenizer {

    List<Token> tokenize(String pieceOfCode, boolean filterComments);

}