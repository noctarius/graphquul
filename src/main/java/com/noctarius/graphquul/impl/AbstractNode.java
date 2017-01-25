package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Source;
import com.noctarius.graphquul.visitor.Visitor;

abstract class AbstractNode
        implements Node {

    private final Source source;

    AbstractNode(Source source) {
        this.source = source;
    }

    @Override
    public Source location() {
        return source;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {

    }
}
