package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserArgumentException;
import com.noctarius.graphquul.ast.BooleanLiteral;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;

import java.util.stream.Stream;

final class MutableBoolean
        extends AbstractNode
        implements BooleanLiteral {

    private final boolean value;

    MutableBoolean(Source source, String value) {
        super(source);
        this.value = initValue(value);
    }

    private boolean initValue(String value) {
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            String message = "Cannot convert '" + value + "' to a boolean";
            throw buildParserException(message, e, IllegalParserArgumentException::new);
        }
    }

    @Override
    public boolean value() {
        return value;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "Boolean{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableBoolean)) {
            return false;
        }

        MutableBoolean that = (MutableBoolean) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
