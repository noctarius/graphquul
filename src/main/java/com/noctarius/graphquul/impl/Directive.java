package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Source;

public final class Directive
        extends AbstractNode {

    public Directive(Source source) {
        super(source);
    }

    @Override
    public Iterable<Node> children() {
        return null;
    }
}
