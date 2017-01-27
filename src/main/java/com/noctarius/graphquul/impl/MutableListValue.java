package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.ListValue;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Value;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableListValue
        extends AbstractNode
        implements ListValue, ValueAddable {

    private final List<Value> values = new ArrayList<>();

    MutableListValue(Source source) {
        super(source);
    }

    @Override
    public Stream<Value> values() {
        return values.stream();
    }

    @Override
    public boolean hasValues() {
        return values.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(values);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addValue(Value value) {
        values.add(value);
    }

    @Override
    public String toString() {
        return "ListValue{" + "values=" + values + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableListValue)) {
            return false;
        }

        MutableListValue that = (MutableListValue) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
