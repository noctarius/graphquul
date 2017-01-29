package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Directives;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.FieldDefinitions;
import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.ImplementsInterfaces;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableOperationTypeDefinition
        extends AbstractNode
        implements OperationTypeDefinition, ImplementsInterfaces, Directives, FieldDefinitions, //
                   ImplementsInterfaceAddable, DirectiveAddable, FieldDefinitionAddable {

    private final OperationType operationType;
    private final String typeName;

    @ZeroOrMore
    private final List<ImplementsInterface> implementsInterfaces = new ArrayList<>();

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();
    private final List<FieldDefinition> fieldDefinitions = new ArrayList<>();

    MutableOperationTypeDefinition(Source source, OperationType operationType, String typeName) {
        super(source);
        this.operationType = operationType;
        this.typeName = typeName;
    }

    @Override
    public OperationType operationType() {
        return operationType;
    }

    @Override
    public String name() {
        return typeName;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(implementsInterfaces, directives, fieldDefinitions);
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
    public Stream<ImplementsInterface> implementsInterfaces() {
        return implementsInterfaces.stream();
    }

    @Override
    public boolean hasImplementsInterfaces() {
        return implementsInterfaces.size() > 0;
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
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "MutableOperationTypeDefinition{" + "operationType=" + operationType + ", typeName='" + typeName + '\''
                + ", implementsInterfaces=" + implementsInterfaces + ", directives=" + directives + ", fieldDefinitions="
                + fieldDefinitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableOperationTypeDefinition)) {
            return false;
        }

        MutableOperationTypeDefinition that = (MutableOperationTypeDefinition) o;

        if (operationType != that.operationType) {
            return false;
        }
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) {
            return false;
        }
        if (implementsInterfaces != null ? !implementsInterfaces.equals(that.implementsInterfaces) :
                that.implementsInterfaces != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return fieldDefinitions != null ? fieldDefinitions.equals(that.fieldDefinitions) : that.fieldDefinitions == null;
    }

    @Override
    public int hashCode() {
        int result = operationType != null ? operationType.hashCode() : 0;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (implementsInterfaces != null ? implementsInterfaces.hashCode() : 0);
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (fieldDefinitions != null ? fieldDefinitions.hashCode() : 0);
        return result;
    }
}
