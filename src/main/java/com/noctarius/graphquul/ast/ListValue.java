package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface ListValue
        extends Value {

    Stream<Value> values();
}
