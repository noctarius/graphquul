package com.noctarius.graphquul.ast;

public interface InputValueDefinition
        extends NamedType, Directives {

    Type type();

    DefaultValue defaultValue();
}
