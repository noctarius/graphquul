package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableFieldDefinition
        extends AbstractNode
        implements FieldDefinition, DirectiveAddable, InputValueDefinitionAddable, TypeAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<InputValueDefinition> inputValueDefinitions = new ArrayList<>();

    private Type type;

    MutableFieldDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public boolean hasDirectives() {
        return directives.size() > 0;
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
    public Stream<InputValueDefinition> inputValueDefinitions() {
        return inputValueDefinitions.stream();
    }

    @Override
    public boolean hasInputValueDefinitions() {
        return inputValueDefinitions.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(type, directives, inputValueDefinitions);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addType(Type type) {
        if (this.type != null) {
            throw buildParserException("Type already set", IllegalParserStateException::new);
        }
        this.type = type;
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
        return "FieldDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", inputValueDefinitions="
                + inputValueDefinitions + ", type=" + type + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableFieldDefinition)) {
            return false;
        }

        MutableFieldDefinition that = (MutableFieldDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        if (inputValueDefinitions != null ? !inputValueDefinitions.equals(that.inputValueDefinitions) :
                that.inputValueDefinitions != null) {
            return false;
        }
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (inputValueDefinitions != null ? inputValueDefinitions.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
