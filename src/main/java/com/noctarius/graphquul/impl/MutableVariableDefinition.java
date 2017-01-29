package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.ast.Variable;
import com.noctarius.graphquul.ast.VariableDefinition;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

final class MutableVariableDefinition
        extends AbstractNode
        implements VariableDefinition, VariableAddable, DefaultValueAddable, TypeAddable {

    private Variable variable;
    private Type type;

    @Optional
    private DefaultValue defaultValue;

    MutableVariableDefinition(Source source) {
        super(source);
    }

    @Override
    public Variable variable() {
        return variable;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public DefaultValue defaultValue() {
        return defaultValue;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(variable, type, defaultValue);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addDefaultValue(DefaultValue defaultValue) {
        if (this.defaultValue != null) {
            throw buildParserException("Default value already set", IllegalParserStateException::new);
        }
        this.defaultValue = defaultValue;
    }

    @Override
    public void addVariable(Variable variable) {
        if (this.variable != null) {
            throw buildParserException("Variable already set", IllegalParserStateException::new);
        }
        this.variable = variable;
    }

    @Override
    public void addType(Type type) {
        if (this.type != null) {
            throw buildParserException("Type already set", IllegalParserStateException::new);
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "VariableDefinition{" + "variable=" + variable + ", type=" + type + ", defaultValue=" + defaultValue + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableVariableDefinition)) {
            return false;
        }

        MutableVariableDefinition that = (MutableVariableDefinition) o;

        if (variable != null ? !variable.equals(that.variable) : that.variable != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        return defaultValue != null ? defaultValue.equals(that.defaultValue) : that.defaultValue == null;
    }

    @Override
    public int hashCode() {
        int result = variable != null ? variable.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
        return result;
    }
}
