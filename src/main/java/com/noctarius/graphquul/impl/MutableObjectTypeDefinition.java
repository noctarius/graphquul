package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectTypeDefinition;
import com.noctarius.graphquul.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableObjectTypeDefinition
        extends AbstractNode
        implements ObjectTypeDefinition, DirectiveAddable, FieldDefinitionAddable, ImplementsInterfaceAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<FieldDefinition> fieldDefinitions = new ArrayList<>();
    private final List<ImplementsInterface> implementsInterfaces = new ArrayList<>();

    MutableObjectTypeDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public Stream<FieldDefinition> fieldDefinitions() {
        return fieldDefinitions.stream();
    }

    @Override
    public Stream<ImplementsInterface> implementsInterfaces() {
        return implementsInterfaces.stream();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, fieldDefinitions, implementsInterfaces);
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
    public void addImplementsInterface(ImplementsInterface implementsInterface) {
        implementsInterfaces.add(implementsInterface);
    }

    @Override
    public String toString() {
        return "ObjectTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", fieldDefinitions="
                + fieldDefinitions + ", implementsInterfaces=" + implementsInterfaces + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableObjectTypeDefinition)) {
            return false;
        }

        MutableObjectTypeDefinition that = (MutableObjectTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        if (fieldDefinitions != null ? !fieldDefinitions.equals(that.fieldDefinitions) : that.fieldDefinitions != null) {
            return false;
        }
        return implementsInterfaces != null ? implementsInterfaces.equals(that.implementsInterfaces) :
                that.implementsInterfaces == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (fieldDefinitions != null ? fieldDefinitions.hashCode() : 0);
        result = 31 * result + (implementsInterfaces != null ? implementsInterfaces.hashCode() : 0);
        return result;
    }
}
