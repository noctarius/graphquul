package com.noctarius.graphquul.ast;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.stream.Stream;

public interface Node {

    Source location();

    Stream<Node> children();

    void acceptVisitor(ASTVisitor visitor);
}
