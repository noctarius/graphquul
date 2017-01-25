package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Source;
import com.noctarius.graphquul.impl.AbstractNode;

public final class Selection
        extends AbstractNode {

    protected Selection(Source source) {
        super(source);
    }

    @Override
    public Iterable<Node> children() {
        return null;
    }
}
