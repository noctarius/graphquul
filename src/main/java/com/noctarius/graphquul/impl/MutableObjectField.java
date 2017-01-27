package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectField;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Value;

import java.util.stream.Stream;

final class MutableObjectField
        extends AbstractNode
        implements ObjectField, ValueAddable {

    private final String name;

    private Value value;

    MutableObjectField(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
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
        return "ObjectField{" + "name='" + name + '\'' + ", value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableObjectField)) {
            return false;
        }

        MutableObjectField that = (MutableObjectField) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
