package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.EnumOrNameLiteral;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableEnumOrName
        extends AbstractNode
        implements EnumOrNameLiteral {

    private final String value;

    MutableEnumOrName(Source source, String value) {
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
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "EnumOrName{" + "value='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableEnumOrName)) {
            return false;
        }

        MutableEnumOrName that = (MutableEnumOrName) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
