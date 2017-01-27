package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableImplementsInterface
        extends AbstractNode
        implements ImplementsInterface {

    private final String name;

    MutableImplementsInterface(Source source, String name) {
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
        return "ImplementsInterface{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableImplementsInterface)) {
            return false;
        }

        MutableImplementsInterface that = (MutableImplementsInterface) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
