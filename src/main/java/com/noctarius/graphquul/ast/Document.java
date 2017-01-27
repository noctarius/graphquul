package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface Document
        extends Node {

    Stream<Definition> definitions();

    boolean hasDefinitions();
}
