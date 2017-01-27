package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserArgumentException;
import com.noctarius.graphquul.ast.IntegerLiteral;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;

import java.util.stream.Stream;

final class MutableInteger
        extends AbstractNode
        implements IntegerLiteral {

    private final long value;

    MutableInteger(Source source, String value) {
        super(source);
        this.value = initValue(value);
    }

    private long initValue(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            String message = "Cannot convert '" + value + "' to an integer";
            throw buildParserException(message, e, IllegalParserArgumentException::new);
        }
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "Integer{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableInteger)) {
            return false;
        }

        MutableInteger that = (MutableInteger) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
