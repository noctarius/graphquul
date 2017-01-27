package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.InputObjectTypeDefinition;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableInputObjectTypeDefinition
        extends AbstractNode
        implements InputObjectTypeDefinition, DirectiveAddable, InputValueDefinitionAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<InputValueDefinition> inputValueDefinitions = new ArrayList<>();

    MutableInputObjectTypeDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public Stream<InputValueDefinition> inputValueDefinitions() {
        return inputValueDefinitions.stream();
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, inputValueDefinitions);
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public void addInputValueDefinition(InputValueDefinition inputValueDefinition) {
        inputValueDefinitions.add(inputValueDefinition);
    }

    @Override
    public String toString() {
        return "InputObjectTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives
                + ", inputValueDefinitions=" + inputValueDefinitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableInputObjectTypeDefinition)) {
            return false;
        }

        MutableInputObjectTypeDefinition that = (MutableInputObjectTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return inputValueDefinitions != null ? inputValueDefinitions.equals(that.inputValueDefinitions) :
                that.inputValueDefinitions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (inputValueDefinitions != null ? inputValueDefinitions.hashCode() : 0);
        return result;
    }
}
