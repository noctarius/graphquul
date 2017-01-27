package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface SchemaDefinition
        extends TypeSystemDefinition, Directives {

    Stream<OperationTypeDefinition> operationTypeDefinitions();

    boolean hasOperationTypeDefinitions();
}
