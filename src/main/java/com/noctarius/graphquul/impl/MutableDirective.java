package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableDirective
        extends AbstractNode
        implements Directive, ArgumentAddable {

    private final String name;

    private final List<Argument> arguments = new ArrayList<>();

    MutableDirective(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Argument> arguments() {
        return null;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(arguments);
    }

    @Override
    public void addArgument(Argument argument) {
        arguments.add(argument);
    }

    @Override
    public String toString() {
        return "Directive{" + "name='" + name + '\'' + ", arguments=" + arguments + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableDirective)) {
            return false;
        }

        MutableDirective that = (MutableDirective) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return arguments != null ? arguments.equals(that.arguments) : that.arguments == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        return result;
    }
}
