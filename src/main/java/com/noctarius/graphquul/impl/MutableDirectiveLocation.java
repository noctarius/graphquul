package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.DirectiveLocation;
import com.noctarius.graphquul.ast.Node;

import java.util.stream.Stream;

final class MutableDirectiveLocation
        extends AbstractNode
        implements DirectiveLocation {

    private final String name;

    MutableDirectiveLocation(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Node> children() {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "DirectiveLocation{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableDirectiveLocation)) {
            return false;
        }

        MutableDirectiveLocation that = (MutableDirectiveLocation) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
