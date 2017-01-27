package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface TypeExtensionDefinition
        extends TypeSystemDefinition {

    Stream<ObjectTypeDefinition> objectTypeDefinitions();
}
