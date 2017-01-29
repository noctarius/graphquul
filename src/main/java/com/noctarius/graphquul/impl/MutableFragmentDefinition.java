package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.FragmentDefinition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Selection;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableFragmentDefinition
        extends AbstractNode
        implements FragmentDefinition, DirectiveAddable, SelectionAddable {

    private final String name;
    private final String typeCondition;

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();
    private final List<Selection> selections = new ArrayList<>();

    MutableFragmentDefinition(Source source, String name, String typeCondition) {
        super(source);
        this.name = name;
        this.typeCondition = typeCondition;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String typeCondition() {
        return typeCondition;
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
    public Stream<Selection> selections() {
        return selections.stream();
    }

    @Override
    public boolean hasSelections() {
        return selections.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, selections);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public void addSelection(Selection selection) {
        selections.add(selection);
    }

    @Override
    public String toString() {
        return "FragmentDefinition{" + "name='" + name + '\'' + ", typeCondition='" + typeCondition + '\''
                + ", directives=" + directives + ", selections=" + selections + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableFragmentDefinition)) {
            return false;
        }

        MutableFragmentDefinition that = (MutableFragmentDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (typeCondition != null ? !typeCondition.equals(that.typeCondition) : that.typeCondition != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return selections != null ? selections.equals(that.selections) : that.selections == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (typeCondition != null ? typeCondition.hashCode() : 0);
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (selections != null ? selections.hashCode() : 0);
        return result;
    }
}
