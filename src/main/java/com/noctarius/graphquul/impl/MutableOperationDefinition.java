package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationDefinition;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.Selection;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.VariableDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableOperationDefinition
        extends AbstractNode
        implements OperationDefinition, SelectionAddable, VariableDefinitionAddable {

    private final String name;
    private final OperationType operationType;

    private final List<VariableDefinition> variableDefinitions = new ArrayList<>();
    private final List<Directive> directives = new ArrayList<>();

    private final List<Selection> selections = new ArrayList<>();

    MutableOperationDefinition(Source source) {
        super(source);
        this.name = "anonymous";
        this.operationType = OperationType.QUERY;
    }

    MutableOperationDefinition(Source source, String name, OperationType operationType) {
        super(source);
        this.name = name;
        this.operationType = operationType;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(variableDefinitions, directives, selections);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public OperationType operationType() {
        return operationType;
    }

    @Override
    public Stream<VariableDefinition> variableDefinitions() {
        return variableDefinitions.stream();
    }

    @Override
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public Stream<Selection> selections() {
        return selections.stream();
    }

    @Override
    public void addVariableDefinition(VariableDefinition variableDefinition) {
        variableDefinitions.add(variableDefinition);
    }

    void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public void addSelection(Selection selection) {
        selections.add(selection);
    }

    @Override
    public String toString() {
        return "OperationDefinition{" + "name='" + name + '\'' + ", operationType=" + operationType
                + ", variableDefinitions=" + variableDefinitions + ", directives=" + directives + ", selections=" + selections
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableOperationDefinition)) {
            return false;
        }

        MutableOperationDefinition that = (MutableOperationDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (operationType != that.operationType) {
            return false;
        }
        if (variableDefinitions != null ? !variableDefinitions.equals(that.variableDefinitions) :
                that.variableDefinitions != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return selections != null ? selections.equals(that.selections) : that.selections == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (variableDefinitions != null ? variableDefinitions.hashCode() : 0);
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (selections != null ? selections.hashCode() : 0);
        return result;
    }
}
