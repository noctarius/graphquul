package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.EnumOrNameLiteral;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.visitor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class MutableEnumValueDefinition
        extends AbstractNode
        implements EnumValueDefinition, EnumOrNameLiteralAddable, DirectiveAddable {

    private EnumOrNameLiteral enumValue;

    @ZeroOrMore
    private final List<Directive> directives = new ArrayList<>();

    MutableEnumValueDefinition(Source source) {
        super(source);
    }

    @Override
    public EnumOrNameLiteral enumValue() {
        return enumValue;
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
    public Stream<Node> children() {
        return asChildren(enumValue, directives);
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
    public void addEnumValue(EnumOrNameLiteral enumValue) {
        if (this.enumValue != null) {
            throw buildParserException("Enum value already set", IllegalParserStateException::new);
        }
        this.enumValue = enumValue;
    }

    @Override
    public String toString() {
        return "EnumValueDefinition{" + "enumValue=" + enumValue + ", directives=" + directives + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MutableEnumValueDefinition)) {
            return false;
        }

        MutableEnumValueDefinition that = (MutableEnumValueDefinition) o;

        if (enumValue != null ? !enumValue.equals(that.enumValue) : that.enumValue != null) {
            return false;
        }
        return directives != null ? directives.equals(that.directives) : that.directives == null;
    }

    @Override
    public int hashCode() {
        int result = enumValue != null ? enumValue.hashCode() : 0;
        result = 31 * result + (directives != null ? directives.hashCode() : 0);
        return result;
    }
}
