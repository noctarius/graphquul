package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.InlineFragment;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Selection;
import com.noctarius.graphquul.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableInlineFragment
        extends AbstractNode
        implements InlineFragment, DirectiveAddable, SelectionAddable {

    private final String typeCondition;

    private final List<Directive> directives = new ArrayList<>();
    private final List<Selection> selections = new ArrayList<>();

    MutableInlineFragment(Source source, String typeCondition) {
        super(source);
        this.typeCondition = typeCondition;
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
    public Stream<Selection> selections() {
        return selections.stream();
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, selections);
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
        return "InlineFragment{" + "typeCondition='" + typeCondition + '\'' + ", directives=" + directives
                + ", selections=" + selections + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableInlineFragment)) {
            return false;
        }

        MutableInlineFragment that = (MutableInlineFragment) o;

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
        int result = typeCondition != null ? typeCondition.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (selections != null ? selections.hashCode() : 0);
        return result;
    }
}
