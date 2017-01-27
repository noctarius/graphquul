package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.FragmentSpread;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableFragmentSpread
        extends AbstractNode
        implements FragmentSpread {

    private final String name;

    MutableFragmentSpread(Source source, String name) {
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
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "FragmentSpread{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableFragmentSpread)) {
            return false;
        }

        MutableFragmentSpread that = (MutableFragmentSpread) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
