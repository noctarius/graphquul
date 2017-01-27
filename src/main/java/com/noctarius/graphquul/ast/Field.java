package com.noctarius.graphquul.ast;

public interface Field
        extends NamedType, Selection, Directives, Selections, Arguments {

    String alias();
}
