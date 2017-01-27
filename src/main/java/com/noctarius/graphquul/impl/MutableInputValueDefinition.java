package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableInputValueDefinition
        extends AbstractNode
        implements InputValueDefinition, TypeAddable, DefaultValueAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();

    private Type type;
    private DefaultValue defaultValue;

    MutableInputValueDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
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
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public Stream<Node> children() {
        return asChildren(type, defaultValue, directives);
    }

    @Override
    public void addType(Type type) {
        if (this.type != null) {
            throw buildParserException("Type already set", IllegalParserStateException::new);
        }
        this.type = type;
    }

    @Override
    public void addDefaultValue(DefaultValue defaultValue) {
        if (this.defaultValue != null) {
            throw buildParserException("Default value already set", IllegalParserStateException::new);
        }
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "InputValueDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", type=" + type
                + ", defaultValue=" + defaultValue + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableInputValueDefinition)) {
            return false;
        }

        MutableInputValueDefinition that = (MutableInputValueDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        return defaultValue != null ? defaultValue.equals(that.defaultValue) : that.defaultValue == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
        return result;
    }
}
