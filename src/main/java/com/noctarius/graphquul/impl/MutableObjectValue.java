package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectField;
import com.noctarius.graphquul.ast.ObjectValue;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableObjectValue
        extends AbstractNode
        implements ObjectValue, ObjectFieldAddable {

    @ZeroOrMore
    private final List<ObjectField> objectFields = new ArrayList<>();

    MutableObjectValue(Source source) {
        super(source);
    }

    @Override
    public Stream<ObjectField> objectFields() {
        return objectFields.stream();
    }

    @Override
    public boolean hasObjectFields() {
        return objectFields.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(objectFields);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addObjectField(ObjectField objectField) {
        objectFields.add(objectField);
    }

    @Override
    public String toString() {
        return "ObjectValue{" + "objectFields=" + objectFields + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableObjectValue)) {
            return false;
        }

        MutableObjectValue that = (MutableObjectValue) o;

        return objectFields != null ? objectFields.equals(that.objectFields) : that.objectFields == null;
    }

    @Override
    public int hashCode() {
        return objectFields != null ? objectFields.hashCode() : 0;
    }
}
