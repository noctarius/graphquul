package com.noctarius.graphquul.ast;

import java.util.stream.Stream;

public interface ObjectValue
        extends Value {

    Stream<ObjectField> objectFields();

    boolean hasObjectFields();
}
