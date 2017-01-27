package com.noctarius.graphquul.ast;

public interface FieldDefinition
        extends NamedType, Directives, InputValueDefinitions {

    Type type();
}
