package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserArgumentException;
import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.Source;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.InlineFragment;
import com.noctarius.graphquul.ast.ListType;
import com.noctarius.graphquul.ast.ListValue;
import com.noctarius.graphquul.ast.Literal;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectValue;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.ast.SchemaDefinition;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.ast.TypeExtensionDefinition;
import com.noctarius.graphquul.ast.VariableDefinition;

import java.util.EnumMap;
import java.util.Map;

import static com.noctarius.graphquul.visitor.StreamingVisitor.LiteralType;
import static com.noctarius.graphquul.visitor.StreamingVisitor.NamedElementType;

final class TypeFactory {

    private static final Map<NamedElementType, IFactory1<Node, String>> NAMED_ELEMENT_TYPE_FACTORIES;
    private static final Map<LiteralType, IFactory1<Literal, String>> LITERAL_TYPE_FACTORIES;

    static {
        NAMED_ELEMENT_TYPE_FACTORIES = new EnumMap<>(NamedElementType.class);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.Argument, MutableArgument::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.Directive, MutableDirective::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.DirectiveDefinition, MutableDirectiveDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.DirectiveLocation, MutableDirectiveLocation::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.EnumTypeDefinition, MutableEnumTypeDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.Field, MutableField::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.FieldDefinition, MutableFieldDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.FragmentSpread, MutableFragmentSpread::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.ImplementsInterface, MutableImplementsInterface::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.InputObjectTypeDefinition, MutableInputObjectTypeDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.InputValueDefinition, MutableInputValueDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.InterfaceTypeDefinition, MutableInterfaceTypeDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.ObjectField, MutableObjectField::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.ObjectTypeDefinition, MutableObjectTypeDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.ScalarTypeDefinition, MutableScalarTypeDefinition::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.UnionMember, MutableUnionMember::new);
        NAMED_ELEMENT_TYPE_FACTORIES.put(NamedElementType.UnionTypeDefinition, MutableUnionTypeDefinition::new);

        LITERAL_TYPE_FACTORIES = new EnumMap<>(LiteralType.class);
        LITERAL_TYPE_FACTORIES.put(LiteralType.Variable, MutableVariable::new);
        LITERAL_TYPE_FACTORIES.put(LiteralType.String, MutableString::new);
        LITERAL_TYPE_FACTORIES.put(LiteralType.Integer, MutableInteger::new);
        LITERAL_TYPE_FACTORIES.put(LiteralType.Float, MutableFloat::new);
        LITERAL_TYPE_FACTORIES.put(LiteralType.Boolean, MutableBoolean::new);
        LITERAL_TYPE_FACTORIES.put(LiteralType.EnumOrName, MutableEnumOrName::new);
    }

    MutableDocument newDocument(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableDocument::new);
    }

    MutableOperationDefinition newOperationDefinition(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableOperationDefinition::new);
    }

    MutableOperationDefinition newOperationDefinition(int line, int column, int tokenId, String name,
                                                      OperationType operationType) {

        return newInstance(line, column, tokenId, MutableOperationDefinition::new, name, operationType);
    }

    <N extends Node> N newNamedElement(int line, int column, int tokenId, NamedElementType namedElementType, String name) {
        IFactory1<Node, String> factory = NAMED_ELEMENT_TYPE_FACTORIES.get(namedElementType);
        if (factory != null) {
            return (N) newInstance(line, column, tokenId, factory, name);
        }

        String message = "Type '" + namedElementType + "' cannot be created with 1 parameter";
        Source source = new SourceImpl(line, column, tokenId);
        throw new IllegalParserArgumentException(source, message);
    }

    <N extends Node> N newNamedElement(int line, int column, int tokenId, //
                                       NamedElementType namedElementType, String name, String argument) {

        IFactory2<Node, String, String> factory;
        switch (namedElementType) {
            case Field:
                factory = MutableField::new;
                break;

            case FragmentDefinition:
                factory = MutableFragmentDefinition::new;
                break;

            default:
                String message = "Type '" + namedElementType + "' cannot be created with 2 parameters";
                Source source = new SourceImpl(line, column, tokenId);
                throw new IllegalParserArgumentException(source, message);
        }
        return (N) newInstance(line, column, tokenId, factory, name, argument);
    }

    Literal newLiteral(int line, int column, int tokenId, LiteralType literalType, String literal) {
        IFactory1<Literal, String> factory = LITERAL_TYPE_FACTORIES.get(literalType);
        if (factory != null) {
            return newInstance(line, column, tokenId, factory, literal);
        }

        Source source = new SourceImpl(line, column, tokenId);
        throw new IllegalParserStateException(source, "Unimplemented literal type found");
    }

    VariableDefinition newVariableDefinition(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableVariableDefinition::new);
    }

    DefaultValue newDefaultValue(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableDefaultValue::new);
    }

    InlineFragment newInlineFragment(int line, int column, int tokenId, String typeCondition) {
        return newInstance(line, column, tokenId, MutableInlineFragment::new, typeCondition);
    }

    ListValue newListValue(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableListValue::new);
    }

    ObjectValue newObjectValue(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableObjectValue::new);
    }

    Type newType(int line, int column, int tokenId, String typeName, boolean nullable) {
        return newInstance(line, column, tokenId, MutableType::new, typeName, nullable);
    }

    ListType newListType(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableListType::new);
    }

    SchemaDefinition newSchemaDefinition(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableSchemaDefinition::new);
    }

    OperationTypeDefinition newOperationTypeDefinition(int line, int column, int tokenId, //
                                                       OperationType operationType, String typeName) {

        return newInstance(line, column, tokenId, MutableOperationTypeDefinition::new, operationType, typeName);
    }

    EnumValueDefinition newEnumValueDefinition(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableEnumValueDefinition::new);
    }

    private <R> R newInstance(int line, int column, int tokenId, IFactory0<R> factory) {
        return factory.newInstance(newSource(line, column, tokenId));
    }

    private <R, A> R newInstance(int line, int column, int tokenId, IFactory1<R, A> factory, A arg1) {
        return factory.newInstance(newSource(line, column, tokenId), arg1);
    }

    private <R, A, B> R newInstance(int line, int column, int tokenId, IFactory2<R, A, B> factory, A arg1, B arg2) {
        return factory.newInstance(newSource(line, column, tokenId), arg1, arg2);
    }

    private Source newSource(int line, int column, int tokenId) {
        return new SourceImpl(line, column, tokenId);
    }

    TypeExtensionDefinition newTypeExtensionDefinition(int line, int column, int tokenId) {
        return newInstance(line, column, tokenId, MutableTypeExtensionDefinition::new);
    }

    private interface IFactory0<R> {
        R newInstance(Source source);
    }

    private interface IFactory1<R, A> {
        R newInstance(Source source, A arg1);
    }

    private interface IFactory2<R, A, B> {
        R newInstance(Source source, A arg1, B arg2);
    }

}
