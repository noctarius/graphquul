package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Field;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.Selection;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableField
        extends AbstractNode
        implements Field, ArgumentAddable, DirectiveAddable, SelectionAddable {

    private final String name;

    @Optional
    private final String alias;

    @ZeroOrMore
    private final List<Argument> arguments = new ArrayList<>();

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();

    @ZeroOrMore
    private final List<Selection> selections = new ArrayList<>();

    MutableField(Source source, String name) {
        this(source, name, null);
    }

    MutableField(Source source, String name, String alias) {
        super(source);
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String alias() {
        return alias;
    }

    @Override
    public Stream<Argument> arguments() {
        return arguments.stream();
    }

    @Override
    public boolean hasArguments() {
        return arguments.size() > 0;
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
        return asChildren(arguments, directives, selections);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addArgument(Argument argument) {
        arguments.add(argument);
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
        return "Field{" + "name='" + name + '\'' + ", alias='" + alias + '\'' + ", arguments=" + arguments
                + ", directives=" + directives + ", selections=" + selections + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableField)) {
            return false;
        }

        MutableField that = (MutableField) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (alias != null ? !alias.equals(that.alias) : that.alias != null) {
            return false;
        }
        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) {
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
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (selections != null ? selections.hashCode() : 0);
        return result;
    }
}
