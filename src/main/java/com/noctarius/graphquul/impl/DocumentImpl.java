package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Definition;
import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class DocumentImpl
        extends AbstractNode
        implements Document {

    private final List<Definition> definitions = new ArrayList<>();

    DocumentImpl(Source source) {
        super(source);
    }

    @Override
    public Iterable<Node> children() {
        return Collections.unmodifiableCollection(definitions);
    }

    void addDefinition(Definition definition) {
        definitions.add(definition);
    }
}
