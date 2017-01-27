package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Definition;
import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableDocument
        extends AbstractNode
        implements Document, DefinitionAddable {

    private final List<Definition> definitions = new ArrayList<>();

    MutableDocument(Source source) {
        super(source);
    }

    @Override
    public Stream<Node> children() {
        return asChildren(definitions);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Stream<Definition> definitions() {
        return definitions.stream();
    }

    @Override
    public boolean hasDefinitions() {
        return definitions.size() > 0;
    }

    @Override
    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    @Override
    public String toString() {
        return "Document{" + "definitions=" + definitions + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableDocument)) {
            return false;
        }

        MutableDocument document = (MutableDocument) o;

        return definitions != null ? definitions.equals(document.definitions) : document.definitions == null;
    }

    @Override
    public int hashCode() {
        return definitions != null ? definitions.hashCode() : 0;
    }
}
