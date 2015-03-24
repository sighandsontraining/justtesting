package eu.sig.handsontraining.measurement;

import java.util.List;

import antlr.Token;

public interface Measurement {

    int measure(List<Token> tokens);
    
}