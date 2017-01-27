package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Value;

import java.util.stream.Stream;

final class MutableDefaultValue
        extends AbstractNode
        implements DefaultValue, ValueAddable {

    private Value value;

    MutableDefaultValue(Source source) {
        super(source);
    }

    @Override
    public Value value() {
        return value;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(value);
    }

    @Override
    public void addValue(Value value) {
        if (this.value != null) {
            throw buildParserException("Value already set", IllegalParserStateException::new);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "DefaultValue{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableDefaultValue)) {
            return false;
        }

        MutableDefaultValue that = (MutableDefaultValue) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
