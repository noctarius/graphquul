package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectTypeDefinition;
import com.noctarius.graphquul.ast.TypeExtensionDefinition;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableTypeExtensionDefinition
        extends AbstractNode
        implements TypeExtensionDefinition, ObjectTypeDefinitionAddable {

    private final List<ObjectTypeDefinition> objectTypeDefinitions = new ArrayList<>();

    MutableTypeExtensionDefinition(Source source) {
        super(source);
    }

    @Override
    public Stream<ObjectTypeDefinition> objectTypeDefinitions() {
        return objectTypeDefinitions.stream();
    }

    @Override
    public boolean hasObjectTypeDefinitions() {
        return objectTypeDefinitions.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(objectTypeDefinitions);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addObjectTypeDefinition(ObjectTypeDefinition objectTypeDefinition) {
        objectTypeDefinitions.add(objectTypeDefinition);
    }

    @Override
    public String toString() {
        return "TypeExtensionDefinition{" + "objectTypeDefinitions=" + objectTypeDefinitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableTypeExtensionDefinition)) {
            return false;
        }

        MutableTypeExtensionDefinition that = (MutableTypeExtensionDefinition) o;

        return objectTypeDefinitions != null ? objectTypeDefinitions.equals(that.objectTypeDefinitions) :
                that.objectTypeDefinitions == null;
    }

    @Override
    public int hashCode() {
        return objectTypeDefinitions != null ? objectTypeDefinitions.hashCode() : 0;
    }
}
