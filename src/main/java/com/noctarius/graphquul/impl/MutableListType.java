package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.ListType;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

public class MutableListType
        extends AbstractNode
        implements ListType, TypeAddable {

    private Type componentType;
    private boolean nullable;

    MutableListType(Source source) {
        super(source);
    }

    @Override
    public String name() {
        return "ListType";
    }

    @Override
    public Type componentType() {
        return componentType;
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
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public void addType(Type componentType) {
        if (this.componentType != null) {
            throw buildParserException("Component type already set", IllegalParserStateException::new);
        }
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "ListType{" + "componentType=" + componentType + ", nullable=" + nullable + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableListType)) {
            return false;
        }

        MutableListType that = (MutableListType) o;

        if (nullable != that.nullable) {
            return false;
        }
        return componentType != null ? componentType.equals(that.componentType) : that.componentType == null;
    }

    @Override
    public int hashCode() {
        int result = componentType != null ? componentType.hashCode() : 0;
        result = 31 * result + (nullable ? 1 : 0);
        return result;
    }
}
