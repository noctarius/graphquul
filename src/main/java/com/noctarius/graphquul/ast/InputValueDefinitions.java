package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface InputValueDefinitions
        extends Node {

    Stream<InputValueDefinition> inputValueDefinitions();

    boolean hasInputValueDefinitions();
}
