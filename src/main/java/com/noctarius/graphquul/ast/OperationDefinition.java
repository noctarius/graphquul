package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface OperationDefinition
        extends NamedType, OperationTyped, Definition, Directives, Selections {

    Stream<VariableDefinition> variableDefinitions();

    boolean hasVariableDefinitions();
}
