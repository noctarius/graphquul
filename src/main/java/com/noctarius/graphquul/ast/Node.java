package com.noctarius.graphquul.ast;

import com.noctarius.graphquul.visitor.Visitor;

public interface Node {

    Source location();

    Iterable<Node> children();

    void acceptVisitor(Visitor visitor);

}
