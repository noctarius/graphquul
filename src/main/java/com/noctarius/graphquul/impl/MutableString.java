package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.StringLiteral;

import java.util.stream.Stream;

final class MutableString
        extends AbstractNode
        implements StringLiteral {

    private final String value;

    MutableString(Source source, String value) {
        super(source);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "String{" + "value='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableString)) {
            return false;
        }

        MutableString that = (MutableString) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
