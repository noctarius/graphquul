package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.UnionMember;
import com.noctarius.graphquul.ast.UnionTypeDefinition;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableUnionTypeDefinition
        extends AbstractNode
        implements UnionTypeDefinition, DirectiveAddable, UnionMemberAddable {

    private final String name;

    private final List<Directive> directives = new ArrayList<>();
    private final List<UnionMember> unionMembers = new ArrayList<>();

    MutableUnionTypeDefinition(Source source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
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
    public Stream<UnionMember> unionMembers() {
        return unionMembers.stream();
    }

    @Override
    public boolean hasUnionMembers() {
        return unionMembers.size() > 0;
    }

    @Override
    public Stream<Node> children() {
        return asChildren(directives, unionMembers);
    }

    @Override
    public void acceptVisitor(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addUnionMember(UnionMember unionMember) {
        unionMembers.add(unionMember);
    }

    @Override
    public void addDirective(Directive directive) {
        directives.add(directive);
    }

    @Override
    public String toString() {
        return "UnionTypeDefinition{" + "name='" + name + '\'' + ", directives=" + directives + ", unionMembers="
                + unionMembers + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableUnionTypeDefinition)) {
            return false;
        }

        MutableUnionTypeDefinition that = (MutableUnionTypeDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (directives != null ? !directives.equals(that.directives) : that.directives != null) {
            return false;
        }
        return unionMembers != null ? unionMembers.equals(that.unionMembers) : that.unionMembers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        result = 31 * result + (unionMembers != null ? unionMembers.hashCode() : 0);
        return result;
    }
}
