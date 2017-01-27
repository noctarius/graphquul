package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface Directives
        extends Node {

    Stream<Directive> directives();
}
