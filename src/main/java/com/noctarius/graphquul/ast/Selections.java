package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface Selections
        extends Node {

    Stream<Selection> selections();

    boolean hasSelections();
}
