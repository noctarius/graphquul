package com.noctarius.graphquul.ast;

public interface Document
        extends Node {

    Iterable<Node> children();
}
