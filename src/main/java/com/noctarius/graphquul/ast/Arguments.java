package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface Arguments
        extends Node {

    Stream<Argument> arguments();

    boolean hasArguments();
}
