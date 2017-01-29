package com.noctarius.graphquul.impl;

import com.noctarius.graphquul.IllegalParserStateException;
import com.noctarius.graphquul.ast.Argument;
import com.noctarius.graphquul.ast.DefaultValue;
import com.noctarius.graphquul.ast.Directive;
import com.noctarius.graphquul.ast.DirectiveDefinition;
import com.noctarius.graphquul.ast.DirectiveLocation;
import com.noctarius.graphquul.ast.Document;
import com.noctarius.graphquul.ast.EnumTypeDefinition;
import com.noctarius.graphquul.ast.EnumValueDefinition;
import com.noctarius.graphquul.ast.Field;
import com.noctarius.graphquul.ast.FieldDefinition;
import com.noctarius.graphquul.ast.FragmentDefinition;
import com.noctarius.graphquul.ast.FragmentSpread;
import com.noctarius.graphquul.ast.ImplementsInterface;
import com.noctarius.graphquul.ast.InlineFragment;
import com.noctarius.graphquul.ast.InputObjectTypeDefinition;
import com.noctarius.graphquul.ast.InputValueDefinition;
import com.noctarius.graphquul.ast.InterfaceTypeDefinition;
import com.noctarius.graphquul.ast.ListType;
import com.noctarius.graphquul.ast.Node;
import com.noctarius.graphquul.ast.ObjectField;
import com.noctarius.graphquul.ast.ObjectTypeDefinition;
import com.noctarius.graphquul.ast.OperationDefinition;
import com.noctarius.graphquul.ast.OperationType;
import com.noctarius.graphquul.ast.OperationTypeDefinition;
import com.noctarius.graphquul.ast.ScalarTypeDefinition;
import com.noctarius.graphquul.ast.SchemaDefinition;
import com.noctarius.graphquul.ast.Type;
import com.noctarius.graphquul.ast.TypeExtensionDefinition;
import com.noctarius.graphquul.ast.UnionMember;
import com.noctarius.graphquul.ast.UnionTypeDefinition;
import com.noctarius.graphquul.ast.Value;
import com.noctarius.graphquul.ast.Variable;
import com.noctarius.graphquul.ast.VariableDefinition;
import com.noctarius.graphquul.visitor.StreamingVisitor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;

public class ASTBuilder
        implements StreamingVisitor {

    private static final String UNEXPECTED_TYPE_ON_STACK = "Expected element on stack is not of type '%s' but '%s'";

    private final TypeFactory typeFactory = new TypeFactory();

    private Deque<Node> stack = new ArrayDeque<>();

    private Document document;

    public Document getDocument() {
        return document;
    }

    @Override
    public void visitDocument(int line, int column, int tokenId) {
        push(typeFactory.newDocument(line, column, tokenId));
    }

    @Override
    public void endDocument(int tokenId) {
        Document document = pop(Document.class);
        validateDocument(document);
        this.document = document;
    }

    @Override
    public void visitOperation(int line, int column, int tokenId) {
        push(typeFactory.newOperationDefinition(line, column, tokenId));
    }

    @Override
    public void visitOperation(int line, int column, int tokenId, OperationType operationType, String name) {
        push(typeFactory.newOperationDefinition(line, column, tokenId, name, operationType));
    }

    @Override
    public void endOperation(int tokenId) {
        popAndAdd(DefinitionAddable.class, OperationDefinition.class, DefinitionAddable::addDefinition);
    }

    @Override
    public void visitMultiSubElement(int line, int column, int tokenId, MultiSubElementType multiSubElementType) {
        // Do nothing - just ignore for AST building
    }

    @Override
    public void endMultiSubElement(int tokenId, MultiSubElementType multiSubElementType) {
        // Do nothing - just ignore for AST building
    }

    @Override
    public void visitNamedElement(int line, int column, int tokenId, NamedElementType namedElementType, String name) {
        push(typeFactory.newNamedElement(line, column, tokenId, namedElementType, name));
    }

    @Override
    public void visitNamedElement(int line, int column, int tokenId, //
                                  NamedElementType namedElementType, String name, String argument) {

        push(typeFactory.newNamedElement(line, column, tokenId, namedElementType, name, argument));
    }

    @Override
    public void endNamedElement(int tokenId, NamedElementType namedElementType) {
        switch (namedElementType) { // TODO
            case Argument:
                popAndAdd(ArgumentAddable.class, Argument.class, ArgumentAddable::addArgument);
                return;

            case Directive:
                popAndAdd(DirectiveAddable.class, Directive.class, DirectiveAddable::addDirective);
                return;

            case DirectiveDefinition:
                popAndAdd(DefinitionAddable.class, DirectiveDefinition.class, DefinitionAddable::addDefinition);
                return;

            case DirectiveLocation:
                popAndAdd(DirectiveLocationAddable.class, DirectiveLocation.class,
                        DirectiveLocationAddable::addDirectiveLocation);
                return;

            case EnumTypeDefinition:
                popAndAdd(DefinitionAddable.class, EnumTypeDefinition.class, DefinitionAddable::addDefinition);
                return;

            case Field:
                popAndAdd(SelectionAddable.class, Field.class, SelectionAddable::addSelection);
                return;

            case FieldDefinition:
                popAndAdd(FieldDefinitionAddable.class, FieldDefinition.class, FieldDefinitionAddable::addFieldDefinition);
                return;

            case FragmentDefinition:
                popAndAdd(DefinitionAddable.class, FragmentDefinition.class, DefinitionAddable::addDefinition);
                return;

            case FragmentSpread:
                popAndAdd(SelectionAddable.class, FragmentSpread.class, SelectionAddable::addSelection);
                return;

            case ImplementsInterface:
                popAndAdd(ImplementsInterfaceAddable.class, ImplementsInterface.class,
                        ImplementsInterfaceAddable::addImplementsInterface);
                return;

            case InputObjectTypeDefinition:
                popAndAdd(DefinitionAddable.class, InputObjectTypeDefinition.class, DefinitionAddable::addDefinition);
                return;

            case InputValueDefinition:
                popAndAdd(InputValueDefinitionAddable.class, InputValueDefinition.class,
                        InputValueDefinitionAddable::addInputValueDefinition);
                return;

            case InterfaceTypeDefinition:
                popAndAdd(DefinitionAddable.class, InterfaceTypeDefinition.class, DefinitionAddable::addDefinition);
                return;

            case ObjectField:
                popAndAdd(ObjectFieldAddable.class, ObjectField.class, ObjectFieldAddable::addObjectField);
                return;

            case ObjectTypeDefinition:
                popAndAdd(DefinitionAddable.class, ObjectTypeDefinition.class, DefinitionAddable::addDefinition);
                return;

            case ScalarTypeDefinition:
                popAndAdd(DefinitionAddable.class, ScalarTypeDefinition.class, DefinitionAddable::addDefinition);
                return;

            case UnionMember:
                popAndAdd(UnionMemberAddable.class, UnionMember.class, UnionMemberAddable::addUnionMember);
                return;

            case UnionTypeDefinition:
                popAndAdd(DefinitionAddable.class, UnionTypeDefinition.class, DefinitionAddable::addDefinition);
        }
    }

    @Override
    public void visitInlineFragment(int line, int column, int tokenId, String typeCondition) {
        push(typeFactory.newInlineFragment(line, column, tokenId, typeCondition));
    }

    @Override
    public void endInlineFragment(int tokenId) {
        popAndAdd(SelectionAddable.class, InlineFragment.class, SelectionAddable::addSelection);
    }

    @Override
    public void visitLiteral(int line, int column, int tokenId, LiteralType literalType, String literal) {
        push(typeFactory.newLiteral(line, column, tokenId, literalType, literal));
    }

    @Override
    public void endLiteral(int tokenId, LiteralType literalType) {
        switch (literalType) {
            case Variable:
                putVariable();
                break;

            default:
                putValue();
        }
    }

    @Override
    public void visitListValue(int line, int column, int tokenId) {
        push(typeFactory.newListValue(line, column, tokenId));
    }

    @Override
    public void endListValue(int tokenId) {
        putValue();
    }

    @Override
    public void visitObjectValue(int line, int column, int tokenId) {
        push(typeFactory.newObjectValue(line, column, tokenId));
    }

    @Override
    public void endObjectValue(int tokenId) {
        putValue();
    }

    @Override
    public void visitVariableDefinition(int line, int column, int tokenId) {
        push(typeFactory.newVariableDefinition(line, column, tokenId));
    }

    @Override
    public void endVariableDefinition(int tokenId) {
        popAndAdd(VariableDefinitionAddable.class, VariableDefinition.class, VariableDefinitionAddable::addVariableDefinition);
    }

    @Override
    public void visitDefaultValue(int line, int column, int tokenId) {
        push(typeFactory.newDefaultValue(line, column, tokenId));
    }

    @Override
    public void endDefaultValue(int tokenId) {
        popAndAdd(DefaultValueAddable.class, DefaultValue.class, DefaultValueAddable::addDefaultValue);
    }

    @Override
    public void visitType(int line, int column, int tokenId, String typeName, boolean nullable) {
        push(typeFactory.newType(line, column, tokenId, typeName, nullable));
    }

    @Override
    public void endType(int tokenId) {
        popAndAdd(TypeAddable.class, Type.class, TypeAddable::addType);
    }

    @Override
    public void visitListType(int line, int column, int tokenId) {
        push(typeFactory.newListType(line, column, tokenId));
    }

    @Override
    public void endListType(int tokenId, boolean nullable) {
        MutableListType listType = peek(MutableListType.class);
        listType.setNullable(nullable);
        popAndAdd(TypeAddable.class, ListType.class, TypeAddable::addType);
    }

    @Override
    public void visitSchemaDefinition(int line, int column, int tokenId) {
        push(typeFactory.newSchemaDefinition(line, column, tokenId));
    }

    @Override
    public void endSchemaDefinition(int tokenId) {
        popAndAdd(DefinitionAddable.class, SchemaDefinition.class, DefinitionAddable::addDefinition);
    }

    @Override
    public void visitOperationTypeDefinition(int line, int column, int tokenId, OperationType operationType, String typeName) {
        push(typeFactory.newOperationTypeDefinition(line, column, tokenId, operationType, typeName));
    }

    @Override
    public void endOperationTypeDefinition(int tokenId) {
        popAndAdd(OperationTypeDefinitionAddable.class, OperationTypeDefinition.class,
                OperationTypeDefinitionAddable::addOperationTypeDefinition);
    }

    @Override
    public void visitEnumValueDefinition(int line, int column, int tokenId) {
        push(typeFactory.newEnumValueDefinition(line, column, tokenId));
    }

    @Override
    public void endEnumValueDefinition(int tokenId) {
        popAndAdd(EnumValueDefinitionAddable.class, EnumValueDefinition.class,
                EnumValueDefinitionAddable::addEnumValueDefinition);
    }

    @Override
    public void visitTypeExtensionDefinition(int line, int column, int tokenId) {
        push(typeFactory.newTypeExtensionDefinition(line, column, tokenId));
    }

    @Override
    public void endTypeExtensionDefinition(int tokenId) {
        popAndAdd(DefinitionAddable.class, TypeExtensionDefinition.class, DefinitionAddable::addDefinition);
    }

    private <C extends Node, P> void popAndAdd(Class<P> parentType, Class<C> childType, BiConsumer<P, C> consumer) {
        C child = pop(childType);
        P parent = peek(parentType);
        consumer.accept(parent, child);
    }

    private void putVariable() {
        Variable variable = pop(Variable.class);
        if (isOfType(VariableAddable.class)) {
            VariableAddable variableAddable = peek(VariableAddable.class);
            variableAddable.addVariable(variable);
        } else {
            putValue(variable);
        }
    }

    private void putValue() {
        Value value = pop(Value.class);
        putValue(value);
    }

    private void putValue(Value value) {
        ValueAddable valueAddable = peek(ValueAddable.class);
        valueAddable.addValue(value);
    }

    private void push(Node node) {
        if (node != null) {
            stack.push(node);
        }
    }

    private <N extends Node> N pop(Class<? super N> type) {
        checkType(type);
        return (N) stack.pop();
    }

    private <N> N peek(Class<? super N> type) {
        checkType(type);
        return (N) stack.peek();
    }

    private <N extends Node> boolean isOfType(Class<? super N> type) {
        Node node = stack.peek();
        return node != null && type.isAssignableFrom(node.getClass());
    }

    private <N extends Node> void checkType(Class<? super N> type) {
        Node node = stack.peek();
        if (node != null && !type.isAssignableFrom(node.getClass())) {
            String message = String.format(UNEXPECTED_TYPE_ON_STACK, type.getName(), node.getClass().getName());
            throw ((AbstractNode) node).buildParserException(message, IllegalParserStateException::new);
        }
    }

    private void validateDocument(Document document) {
        ((AbstractNode) document).validate();
    }
}
