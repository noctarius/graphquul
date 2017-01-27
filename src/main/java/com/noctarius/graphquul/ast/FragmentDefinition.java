package com.noctarius.graphquul.ast;

public interface FragmentDefinition
        extends NamedType, Definition, Directives, Selections {

    String typeCondition();
}
