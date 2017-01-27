package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserArgumentException;
import com.noctarius.graphquul.ast.FloatLiteral;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;

import java.util.stream.Stream;

final class MutableFloat
        extends AbstractNode
        implements FloatLiteral {

    private final double value;

    MutableFloat(Source source, String value) {
        super(source);
        this.value = initValue(value);
    }

    private double initValue(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            String message = "Cannot convert '" + value + "' to a float";
            throw buildParserException(message, e, IllegalParserArgumentException::new);
        }
    }

    @Override
    public double value() {
        return value;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "Float{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableFloat)) {
            return false;
        }

        MutableFloat that = (MutableFloat) o;

        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
