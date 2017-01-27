package com.noctarius.graphquul.ast;

public interface InlineFragment
        extends Selection, Directives, Selections {

    String typeCondition();
}
