package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ScalarTypeDefinition;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableScalarTypeDefinition
        extends AbstractNode
        implements ScalarTypeDefinition, DirectiveAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();

    MutableScalarTypeDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Stream<Directive> directives() {
        return directives.stream();
    }

    @Override
    public boolean hasDirectives() {
        return directives.size() > 0;
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public String toString() {
        return "ScalarTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableScalarTypeDefinition)) {
            return false;
        }

        MutableScalarTypeDefinition that = (MutableScalarTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return directives != null ? directives.equals(that.directives) : that.directives == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        return result;
    }
}
