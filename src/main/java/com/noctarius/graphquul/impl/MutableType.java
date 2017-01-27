package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Type;

import java.util.stream.Stream;

final class MutableType
        extends AbstractNode
        implements Type {

    private final String name;
    private final boolean nullable;

    MutableType(Source source, String name, boolean nullable) {
        super(source);
        this.name = name;
        this.nullable = nullable;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean nullable() {
        return nullable;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "Type{" + "name='" + name + '\'' + ", nullable=" + nullable + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableType)) {
            return false;
        }

        MutableType that = (MutableType) o;

        if (nullable != that.nullable) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nullable ? 1 : 0);
        return result;
    }
}
