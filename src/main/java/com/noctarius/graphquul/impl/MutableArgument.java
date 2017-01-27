package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Value;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableArgument
        extends AbstractNode
        implements Argument, ValueAddable {

    private final String name;

    private Value value;

    MutableArgument(Source source, String name) {
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
    public void addValue(Value value) {
        if (this.value != null) {
            throw buildParserException("Value already set", IllegalParserStateException::new);
        }
        this.value = value;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(value);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Argument{" + "name='" + name + '\'' + ", value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableArgument)) {
            return false;
        }

        MutableArgument that = (MutableArgument) o;

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
