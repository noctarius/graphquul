package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.DirectiveDefinition;
import com.noctarius.graphquul.ast.DirectiveLocation;
import com.noctarius.graphquul.ast.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class MutableDirectiveDefinition
        extends AbstractNode
        implements DirectiveDefinition, ArgumentAddable, DirectiveLocationAddable {

    private final String name;

    private final List<Argument> arguments = new ArrayList<>();
    private final List<DirectiveLocation> directiveLocations = new ArrayList<>();

    MutableDirectiveDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Argument> arguments() {
        return arguments.stream();
    }

    @Override
    public Stream<DirectiveLocation> directiveLocations() {
        return directiveLocations.stream();
    }

    @Override
    public Stream<Node> children() {
        return asChildren(arguments, directiveLocations);
    }

    @Override
    public void addArgument(Argument argument) {
        arguments.add(argument);
    }

    @Override
    public void addDirectiveLocation(DirectiveLocation directiveLocation) {
        directiveLocations.add(directiveLocation);
    }

    @Override
    public String toString() {
        return "DirectiveDefinition{" + "name='" + name + '\'' + ", arguments=" + arguments + ", directiveLocations="
                + directiveLocations + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableDirectiveDefinition)) {
            return false;
        }

        MutableDirectiveDefinition that = (MutableDirectiveDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) {
            return false;
        }
        return directiveLocations != null ? directiveLocations.equals(that.directiveLocations) : that.directiveLocations == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        result = 31 * result + (directiveLocations != null ? directiveLocations.hashCode() : 0);
        return result;
    }
}
