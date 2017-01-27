package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.InterfaceTypeDefinition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableInterfaceTypeDefinition
        extends AbstractNode
        implements InterfaceTypeDefinition, DirectiveAddable, FieldDefinitionAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<FieldDefinition> fieldDefinitions = new ArrayList<>();

    MutableInterfaceTypeDefinition(Source source, String name) {
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
    public boolean hasDirectives() {
        return directives.size() > 0;
    }

    @Override
    public Stream<FieldDefinition> fieldDefinitions() {
        return fieldDefinitions.stream();
    }

    @Override
    public boolean hasFieldDefinitions() {
        return fieldDefinitions.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, fieldDefinitions);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public void addFieldDefinition(FieldDefinition fieldDefinition) {
        fieldDefinitions.add(fieldDefinition);
    }

    @Override
    public String toString() {
        return "InterfaceTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", fieldDefinitions="
                + fieldDefinitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableInterfaceTypeDefinition)) {
            return false;
        }

        MutableInterfaceTypeDefinition that = (MutableInterfaceTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return fieldDefinitions != null ? fieldDefinitions.equals(that.fieldDefinitions) : that.fieldDefinitions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (fieldDefinitions != null ? fieldDefinitions.hashCode() : 0);
        return result;
    }
}
