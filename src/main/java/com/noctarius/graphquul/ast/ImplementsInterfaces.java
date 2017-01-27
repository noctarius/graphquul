package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface ImplementsInterfaces
        extends Node {

    Stream<ImplementsInterface> implementsInterfaces();

    boolean hasImplementsInterfaces();
}
