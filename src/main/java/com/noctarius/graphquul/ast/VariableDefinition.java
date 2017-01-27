package com.noctarius.graphquul.ast;

public interface VariableDefinition
        extends Node {

    Variable variable();

    Type type();

    DefaultValue defaultValue();

}
