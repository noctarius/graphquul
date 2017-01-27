package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.UnionMember;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableUnionMember
        extends AbstractNode
        implements UnionMember {

    private final String name;

    MutableUnionMember(Source source, String name) {
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
        return "UnionMember{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableUnionMember)) {
            return false;
        }

        MutableUnionMember that = (MutableUnionMember) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
