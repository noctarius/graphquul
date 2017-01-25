package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Definition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.Source;

import java.util.ArrayList;
import java.util.List;

public final class Operation
        extends AbstractNode
        implements Definition {

    private final String name;
    private final OperationType operationType;

    private final List<Variable> variables = new ArrayList<>();
    private final List<Directive> directives = new ArrayList<>();

    private final List<Selection> selections = new ArrayList<>();

    public Operation(Source source, String name, OperationType operationType) {
        super(source);
        this.name = name;
        this.operationType = operationType;
    }

    @Override
    public Iterable<Node> children() {
        return null;
    }
}
