package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Directives;
import com.noctarius.graphquul.ast.FragmentSpread;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableFragmentSpread
        extends AbstractNode
        implements FragmentSpread, Directives, DirectiveAddable {

    private final String name;

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();

    MutableFragmentSpread(Source source, String name) {
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
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "MutableFragmentSpread{" + "name='" + name + '\'' + ", directives=" + directives + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableFragmentSpread)) {
            return false;
        }

        MutableFragmentSpread that = (MutableFragmentSpread) o;

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
