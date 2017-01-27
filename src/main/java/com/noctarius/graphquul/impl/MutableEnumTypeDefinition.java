package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.EnumTypeDefinition;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableEnumTypeDefinition
        extends AbstractNode
        implements EnumTypeDefinition, DirectiveAddable, EnumValueDefinitionAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<EnumValueDefinition> enumValueDefinitions = new ArrayList<>();

    MutableEnumTypeDefinition(Source source, String name) {
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
    public Stream<EnumValueDefinition> enumValueDefinitions() {
        return enumValueDefinitions.stream();
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, enumValueDefinitions);
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public void addEnumValueDefinition(EnumValueDefinition enumValueDefinition) {
        enumValueDefinitions.add(enumValueDefinition);
    }

    @Override
    public String toString() {
        return "EnumTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", enumValueDefinitions="
                + enumValueDefinitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableEnumTypeDefinition)) {
            return false;
        }

        MutableEnumTypeDefinition that = (MutableEnumTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return enumValueDefinitions != null ? enumValueDefinitions.equals(that.enumValueDefinitions) :
                that.enumValueDefinitions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (enumValueDefinitions != null ? enumValueDefinitions.hashCode() : 0);
        return result;
    }
}
