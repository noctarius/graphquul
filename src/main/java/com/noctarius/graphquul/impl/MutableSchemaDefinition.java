package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.ast.SchemaDefinition;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableSchemaDefinition
        extends AbstractNode
        implements SchemaDefinition, DirectiveAddable, OperationTypeDefinitionAddable {

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();
    private final List<OperationTypeDefinition> operationTypeDefinitions = new ArrayList<>();

    MutableSchemaDefinition(Source source) {
        super(source);
    }

    @Override
    public Stream<OperationTypeDefinition> operationTypeDefinitions() {
        return operationTypeDefinitions.stream();
    }

    @Override
    public boolean hasOperationTypeDefinitions() {
        return operationTypeDefinitions.size() > 0;
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
    public Stream<Node> children() {
        return asChildren(directives, operationTypeDefinitions);
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
    public void addOperationTypeDefinition(OperationTypeDefinition operationTypeDefinition) {
        operationTypeDefinitions.add(operationTypeDefinition);
    }

    @Override
    public String toString() {
        return "SchemaDefinition{" + "operationTypeDefinitions=" + operationTypeDefinitions + ", directives=" + directives
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableSchemaDefinition)) {
            return false;
        }

        MutableSchemaDefinition that = (MutableSchemaDefinition) o;

        if (operationTypeDefinitions != null ? !operationTypeDefinitions.equals(that.operationTypeDefinitions) :
                that.operationTypeDefinitions != null) {
            return false;
        }
        return directives != null ? directives.equals(that.directives) : that.directives == null;
    }

    @Override
    public int hashCode() {
        int result = operationTypeDefinitions != null ? operationTypeDefinitions.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        return result;
    }
}
