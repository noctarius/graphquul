package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface FieldDefinitions
        extends Node {

    Stream<FieldDefinition> fieldDefinitions();

    boolean hasFieldDefinitions();
}
