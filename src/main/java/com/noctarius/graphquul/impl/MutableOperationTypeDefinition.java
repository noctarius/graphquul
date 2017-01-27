package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.OperationTypeDefinition;

import java.util.stream.Stream;

final class MutableOperationTypeDefinition
        extends AbstractNode
        implements OperationTypeDefinition {

    private final OperationType operationType;
    private final String typeName;

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
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "OperationTypeDefinition{" + "operationType=" + operationType + ", typeName='" + typeName + '\'' + '}';
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
        return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;
    }

    @Override
    public int hashCode() {
        int result = operationType != null ? operationType.hashCode() : 0;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}
